package hope.musinsa.domain.clothing.application

import hope.musinsa.domain.clothing.application.dto.response.LowestCategoryClothingPriceAndTotalResponse
import hope.musinsa.domain.clothing.application.dto.response.LowestTotalPriceCategoryClothingResponse
import hope.musinsa.domain.clothing.application.dto.response.MinAndMaxPriceClothingResponse
import hope.musinsa.domain.clothing.persistence.entity.ClothingCategory

interface QueryClothingService {
    fun queryLowestCategoryPricesClothing() : LowestCategoryClothingPriceAndTotalResponse
    fun queryLowestTotalPriceCategoryClothing(): LowestTotalPriceCategoryClothingResponse
    fun queryMinAndMaxPriceClothing(category: ClothingCategory): MinAndMaxPriceClothingResponse
}