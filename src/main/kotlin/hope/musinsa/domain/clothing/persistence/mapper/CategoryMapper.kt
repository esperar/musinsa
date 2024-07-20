package hope.musinsa.domain.clothing.persistence.mapper

import hope.musinsa.domain.clothing.domain.Category
import hope.musinsa.domain.clothing.persistence.entity.CategoryEntity
import hope.musinsa.global.util.GenericMapper
import org.springframework.stereotype.Component

@Component
class CategoryMapper : GenericMapper<Category, CategoryEntity> {

    override fun toDomain(entity: CategoryEntity?): Category? =
        entity?.let {
            Category(
                id = it.id,
                category = it.category
            )
        }

    override fun toEntity(domain: Category): CategoryEntity =
        domain.let {
            CategoryEntity(
                id = it.id,
                category = it.category
            )
        }
}