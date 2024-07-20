package hope.musinsa.domain.clothing.application.dto.response

import hope.musinsa.domain.clothing.persistence.entity.ClothingCategory
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

data class LowestCategoryClothingPriceAndTotalResponse(
    val categoryClothing: List<LowestCategoryClothingPriceResponse>,
    val totalPrice: Int
)

data class LowestCategoryClothingPriceResponse(
    @Enumerated(EnumType.STRING)
    val category: ClothingCategory,
    val brand: String,
    val price: Int
)
