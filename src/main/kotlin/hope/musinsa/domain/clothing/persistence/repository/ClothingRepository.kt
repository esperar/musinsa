package hope.musinsa.domain.clothing.persistence.repository

import hope.musinsa.domain.clothing.persistence.entity.CategoryEntity
import hope.musinsa.domain.clothing.persistence.entity.ClothingEntity
import org.springframework.data.repository.CrudRepository

interface ClothingRepository : CrudRepository<ClothingEntity, Long> {
    fun findTopByCategoryOrderByPriceDesc(category: CategoryEntity): ClothingEntity? // max
    fun findTopByCategoryOrderByPrice(category: CategoryEntity): ClothingEntity? // min
}