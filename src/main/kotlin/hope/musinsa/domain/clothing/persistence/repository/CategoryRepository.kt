package hope.musinsa.domain.clothing.persistence.repository

import hope.musinsa.domain.clothing.persistence.entity.CategoryEntity
import hope.musinsa.domain.clothing.persistence.entity.ClothingCategory
import org.springframework.data.repository.CrudRepository

interface CategoryRepository : CrudRepository<CategoryEntity, Long> {
    fun findByCategory(category: ClothingCategory): CategoryEntity?
}