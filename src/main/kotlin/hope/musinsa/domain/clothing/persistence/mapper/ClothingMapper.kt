package hope.musinsa.domain.clothing.persistence.mapper

import hope.musinsa.domain.clothing.domain.Clothing
import hope.musinsa.domain.clothing.exception.NotFoundCategoryException
import hope.musinsa.domain.clothing.persistence.repository.CategoryRepository
import hope.musinsa.domain.clothing.persistence.entity.ClothingEntity
import hope.musinsa.global.util.GenericMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ClothingMapper(
    private val categoryRepository: CategoryRepository
) : GenericMapper<Clothing, ClothingEntity> {

    override fun toDomain(entity: ClothingEntity?): Clothing? =
       entity?.let {
           Clothing(
               id = it.id,
               brand = it.brand,
               price = it.price,
               categoryId = entity.category.id
           )
       }


    override fun toEntity(domain: Clothing): ClothingEntity {
        val category = categoryRepository.findByIdOrNull(domain.categoryId)
            ?: throw NotFoundCategoryException("Not Found Category")

        return domain.let {
            ClothingEntity(
                id = it.id,
                brand = it.brand,
                price = it.price,
                category = category
            )
        }
    }

}
