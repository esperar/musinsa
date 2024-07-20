package hope.musinsa.domain.clothing.persistence.port

import hope.musinsa.domain.clothing.application.dto.response.BrandAndPriceClothingData
import hope.musinsa.domain.clothing.application.dto.response.ClothingDataWithBrand
import hope.musinsa.domain.clothing.application.dto.response.LowestCategoryClothingPriceResponse
import hope.musinsa.domain.clothing.domain.Clothing
import hope.musinsa.domain.clothing.persistence.entity.ClothingCategory

interface ClothingPort {
    fun findById(id: Long) : Clothing?
    fun findMinCategory() : List<LowestCategoryClothingPriceResponse>
    fun findMinLowestTotalPriceClothing(): List<ClothingDataWithBrand>
    fun findMinAndMaxPriceClothing(category: ClothingCategory): List<BrandAndPriceClothingData>
    fun save(clothing: Clothing, isUpdate: Boolean): Clothing
    fun delete(clothing: Clothing): Long
}