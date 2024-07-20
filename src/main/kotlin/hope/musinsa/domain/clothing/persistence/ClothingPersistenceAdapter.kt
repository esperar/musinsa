package hope.musinsa.domain.clothing.persistence

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import hope.musinsa.domain.clothing.application.dto.response.BrandAndPriceClothingData
import hope.musinsa.domain.clothing.application.dto.response.ClothingDataWithBrand
import hope.musinsa.domain.clothing.application.dto.response.LowestCategoryClothingPriceResponse
import hope.musinsa.domain.clothing.domain.Clothing
import hope.musinsa.domain.clothing.exception.NotFoundCategoryException
import hope.musinsa.domain.clothing.exception.NotFoundClothingException
import hope.musinsa.domain.clothing.persistence.entity.ClothingCategory
import hope.musinsa.domain.clothing.persistence.entity.ClothingEntity
import hope.musinsa.domain.clothing.persistence.entity.QClothingEntity
import hope.musinsa.domain.clothing.persistence.mapper.ClothingMapper
import hope.musinsa.domain.clothing.persistence.port.ClothingPort
import hope.musinsa.domain.clothing.persistence.repository.CategoryRepository
import hope.musinsa.domain.clothing.persistence.repository.ClothingRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class ClothingPersistenceAdapter(
    private val clothingMapper: ClothingMapper,
    private val clothingRepository: ClothingRepository,
    private val categoryRepository: CategoryRepository,
    private val jdbcTemplate: JdbcTemplate,
    private val queryFactory: JPAQueryFactory
) : ClothingPort {

    override fun findById(id: Long): Clothing? {
        val clothingEntity = clothingRepository.findByIdOrNull(id)
        return clothingMapper.toDomain(clothingEntity)
    }

    override fun findMinCategory(): List<LowestCategoryClothingPriceResponse> {
        val sql = """
            SELECT c.category_id, c.brand, c.price
            FROM tbl_clothing c
            INNER JOIN (
                SELECT category_id, MIN(price) AS min_price
                FROM tbl_clothing
                GROUP BY category_id
            ) AS min_prices ON c.category_id = min_prices.category_id AND c.price = min_prices.min_price
        """

        return jdbcTemplate.query(sql) { rs, _ ->
            val category = categoryRepository.findByIdOrNull(rs.getLong("category_id"))
                ?: throw NotFoundCategoryException("Not Found Category")
            LowestCategoryClothingPriceResponse(
                category = category.category,
                brand = rs.getString("brand"),
                price = rs.getInt("price")
            )
        }
    }

    override fun findMinLowestTotalPriceClothing(): List<ClothingDataWithBrand> {
        val clothingEntity = QClothingEntity.clothingEntity

        val brand = queryFactory.select(clothingEntity.brand)
            .from(clothingEntity)
            .groupBy(clothingEntity.brand)
            .orderBy(clothingEntity.price.sum().asc())
            .fetchFirst()

        return queryFactory.select(
            Projections.constructor(
                ClothingDataWithBrand::class.java,
                clothingEntity.brand,
                clothingEntity.category.category,
                clothingEntity.price
            )
        )
            .from(clothingEntity)
            .where(clothingEntity.brand.eq(brand))
            .fetch()
    }

    override fun findMinAndMaxPriceClothing(category: ClothingCategory): List<BrandAndPriceClothingData> {
        val category = categoryRepository.findByCategory(category)
            ?: throw NotFoundCategoryException("Not Found Category Exception..")

        val minClothing = clothingRepository.findTopByCategoryOrderByPrice(category)
            ?: throw NotFoundClothingException("Not Found Min Price Clothing")

        val maxClothing = clothingRepository.findTopByCategoryOrderByPriceDesc(category)
            ?: throw NotFoundClothingException("Not Found Max Price Clothing")

        return mutableListOf(
            BrandAndPriceClothingData(
                brand = minClothing.brand,
                price = minClothing.price
            ),

            BrandAndPriceClothingData(
                brand = maxClothing.brand,
                price = maxClothing.price
            )
        )
    }

    override fun save(clothing: Clothing, isUpdate: Boolean): Clothing {

        // update
        if(isUpdate) {
            val clothingEntity = clothingMapper.toEntity(clothing)
            return clothingMapper.toDomain((clothingRepository.save(clothingEntity)))!!
        }

        // new
        return clothingMapper.toDomain(clothingRepository.save(
            ClothingEntity(
                brand = clothing.brand,
                price = clothing.price,
                category = categoryRepository.findByIdOrNull(clothing.categoryId)
                    ?: throw NotFoundCategoryException("Not Found Category")
            )
        ))!!
    }

    override fun delete(clothing: Clothing): Long {
        clothingRepository.delete(clothingMapper.toEntity(clothing))
        return clothing.id
    }

}

