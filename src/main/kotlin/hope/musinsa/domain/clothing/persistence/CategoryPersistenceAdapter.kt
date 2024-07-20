package hope.musinsa.domain.clothing.persistence

import hope.musinsa.domain.clothing.domain.Category
import hope.musinsa.domain.clothing.exception.NotFoundCategoryException
import hope.musinsa.domain.clothing.persistence.entity.ClothingCategory
import hope.musinsa.domain.clothing.persistence.mapper.CategoryMapper
import hope.musinsa.domain.clothing.persistence.port.CategoryPort
import hope.musinsa.domain.clothing.persistence.repository.CategoryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CategoryPersistenceAdapter(
    private val categoryRepository: CategoryRepository,
    private val categoryMapper: CategoryMapper
) : CategoryPort {

    override fun findById(id: Long): Category? {
        val category = categoryRepository.findByIdOrNull(id)
            ?: throw NotFoundCategoryException("Not Found Category Exception");
        return categoryMapper.toDomain(category)
    }

    override fun findByCategory(category: ClothingCategory): Category? {
        val category = categoryRepository.findByCategory(category)
            ?: throw NotFoundCategoryException("Not Found Category Exception");
        return categoryMapper.toDomain(category)
    }
}