package hope.musinsa.domain.clothing.application.dto.request


data class UpdateClothingRequest(
    val categoryId: Long?,
    val brand: String?,
    val price: Int?
)
