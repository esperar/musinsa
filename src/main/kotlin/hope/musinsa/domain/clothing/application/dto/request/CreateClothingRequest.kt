package hope.musinsa.domain.clothing.application.dto.request


data class CreateClothingRequest(
    val categoryId: Long,
    val brand: String,
    val price: Int
)
