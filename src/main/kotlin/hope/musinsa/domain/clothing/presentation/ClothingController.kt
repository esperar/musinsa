package hope.musinsa.domain.clothing.presentation

import hope.musinsa.domain.clothing.application.CommandClothingService
import hope.musinsa.domain.clothing.application.QueryClothingService
import hope.musinsa.domain.clothing.application.dto.request.CreateClothingRequest
import hope.musinsa.domain.clothing.application.dto.request.UpdateClothingRequest
import hope.musinsa.domain.clothing.application.dto.response.LowestCategoryClothingPriceAndTotalResponse
import hope.musinsa.domain.clothing.application.dto.response.LowestTotalPriceCategoryClothingResponse
import hope.musinsa.domain.clothing.application.dto.response.MinAndMaxPriceClothingResponse
import hope.musinsa.domain.clothing.persistence.entity.ClothingCategory
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/clothing")
class ClothingController(
    private val queryClothingService: QueryClothingService,
    private val commandClothingService: CommandClothingService
) {

    /**
     * 과제 가독성 편의상 구현 1 ~ 3의 endpoint와 함수 name을 api N 으로 작성했습니다.
     */
    @GetMapping("/api1")
    fun api1(): ResponseEntity<LowestCategoryClothingPriceAndTotalResponse> {
        val response = queryClothingService.queryLowestCategoryPricesClothing()
        return ResponseEntity.ok(response)
    }

    @GetMapping("/api2")
    fun api2(): ResponseEntity<LowestTotalPriceCategoryClothingResponse> {
        val response = queryClothingService.queryLowestTotalPriceCategoryClothing()
        return ResponseEntity.ok(response)
    }

    @GetMapping("/api3")
    fun api3(@RequestParam category: ClothingCategory): ResponseEntity<MinAndMaxPriceClothingResponse> {
        val response = queryClothingService.queryMinAndMaxPriceClothing(category)
        return ResponseEntity.ok(response)
    }

    /**
     * api 4 CUD
     */
    @PostMapping
    fun createClothing(@RequestBody request: CreateClothingRequest): ResponseEntity<Void> {
        commandClothingService.createClothing(request)
        return ResponseEntity.created(URI.create("/clothing")).build()
    }

    @PatchMapping("/{id}")
    fun updateClothing(@PathVariable id: Long, @RequestBody request: UpdateClothingRequest): ResponseEntity<Void> {
        commandClothingService.updateClothingById(id, request)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun deleteClothing(@PathVariable id: Long): ResponseEntity<Void> {
        commandClothingService.deleteClothingById(id)
        return ResponseEntity.noContent().build()
    }

}
