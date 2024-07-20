package hope.musinsa.domain.clothing.domain

import hope.musinsa.domain.clothing.application.dto.request.UpdateClothingRequest

data class Clothing(
    val id: Long = 0,
    var brand: String,
    var price: Int,
    var categoryId: Long
) {

    fun updateClothing(request: UpdateClothingRequest): Clothing {
        this.brand = request.brand ?: this.brand
        this.price = request.price ?: this.price
        this.categoryId = request.categoryId ?: this.categoryId
        return this
    }
}