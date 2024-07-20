package hope.musinsa.domain.clothing.application.dto.response

import hope.musinsa.domain.clothing.persistence.entity.ClothingCategory
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

data class MinAndMaxPriceClothingResponse(
    @Enumerated(EnumType.STRING)
    val category: ClothingCategory,
    val maxAndMin: List<BrandAndPriceClothingData>,
)

data class BrandAndPriceClothingData(
    val brand: String,
    val price: Int
)
