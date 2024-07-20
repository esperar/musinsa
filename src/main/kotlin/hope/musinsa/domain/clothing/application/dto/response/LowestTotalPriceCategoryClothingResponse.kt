package hope.musinsa.domain.clothing.application.dto.response

data class LowestTotalPriceCategoryClothingResponse(
    val brand: String,
    val clothing: List<ClothingData>,
    val totalPrice: Int
)

