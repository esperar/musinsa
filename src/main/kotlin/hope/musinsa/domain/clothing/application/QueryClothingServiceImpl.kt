package hope.musinsa.domain.clothing.application

import hope.musinsa.domain.clothing.application.dto.response.*
import hope.musinsa.domain.clothing.exception.NotFoundClothingException
import hope.musinsa.domain.clothing.persistence.entity.ClothingCategory
import hope.musinsa.domain.clothing.persistence.port.ClothingPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryClothingServiceImpl(
    private val port: ClothingPort
) : QueryClothingService {

    /**
     * @author esperar
     *  카테고리 별 최저가격 브랜드와 상품 가격, 총액을 return하는 메서드
     * @return [ 카테고리 별 최저가격 브랜드와 상품 가격, 총액 ]
     *
     * | 카테고리가 8개로 고정되어있다는 점을 가정하여, total price를 query로 집계하게 되면 쿼리의 복잡성이 증가합니다.
     * | code level에서 이를 집계해도 부담이 없을 것 같아 sumOf를 통해서 total을 집계하도록 처리합니다.
     */
    override fun queryLowestCategoryPricesClothing(): LowestCategoryClothingPriceAndTotalResponse {
        val lowestCategoryClothingPriceResponses = port.findMinCategory()

        if(lowestCategoryClothingPriceResponses.isEmpty()) {
            throw NotFoundClothingException("Not Found Clothing Data.. So We can't find lowest category clothing price")
        }

        return LowestCategoryClothingPriceAndTotalResponse(
            categoryClothing = lowestCategoryClothingPriceResponses,
            totalPrice = lowestCategoryClothingPriceResponses.sumOf { it.price }
        )
    }

    /**
     * @author esperar
     *  단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 메서드
     * @return [ 카테고리 브랜드, 옷 데이터, 총액 ]
     */
    override fun queryLowestTotalPriceCategoryClothing(): LowestTotalPriceCategoryClothingResponse {
        val clothingData = port.findMinLowestTotalPriceClothing()

        if(clothingData.isEmpty()) {
            throw NotFoundClothingException("Not Found Clothing Data.. So We can't find lowest total price category clothing")
        }

        return LowestTotalPriceCategoryClothingResponse(
            brand = clothingData[0].brand,
            clothing = clothingData.toClothingData(),
            totalPrice = clothingData.sumOf { it.price }
        )
    }

    /**
     * @author esperar
     * @param category: ClothingCategory
     * 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 메서드
     * @return [ 카테고리, 최소,최대 가격 옷 데이터 (브랜드, 가격) ]
     */
    override fun queryMinAndMaxPriceClothing(category: ClothingCategory): MinAndMaxPriceClothingResponse =
        MinAndMaxPriceClothingResponse(
            category = category,
            maxAndMin = port.findMinAndMaxPriceClothing(category)
        )


    private fun List<ClothingDataWithBrand>.toClothingData(): List<ClothingData> {
        return this.map {
            ClothingData(category = it.category, price = it.price)
        }
    }

}
