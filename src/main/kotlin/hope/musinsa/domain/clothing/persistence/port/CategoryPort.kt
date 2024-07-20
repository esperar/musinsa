package hope.musinsa.domain.clothing.persistence.port

import hope.musinsa.domain.clothing.domain.Category
import hope.musinsa.domain.clothing.persistence.entity.ClothingCategory

interface CategoryPort {
    fun findById(id: Long): Category?
    fun findByCategory(category: ClothingCategory): Category?
}