package hope.musinsa.domain.clothing.application.dto.response

import hope.musinsa.domain.clothing.persistence.entity.ClothingCategory

data class ClothingData(
    val category: ClothingCategory,
    val price: Int
)

data class ClothingDataWithBrand(
    val brand: String,
    val category: ClothingCategory,
    val price: Int
)

