package hope.musinsa.domain.clothing.application

import hope.musinsa.domain.clothing.application.dto.request.CreateClothingRequest
import hope.musinsa.domain.clothing.application.dto.request.UpdateClothingRequest
import hope.musinsa.domain.clothing.domain.Clothing
import hope.musinsa.domain.clothing.exception.NotFoundCategoryException
import hope.musinsa.domain.clothing.exception.NotFoundClothingException
import hope.musinsa.domain.clothing.persistence.port.CategoryPort
import hope.musinsa.domain.clothing.persistence.port.ClothingPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class CommandClothingServiceImpl(
    private val clothingPort: ClothingPort,
    private val categoryPort: CategoryPort
) : CommandClothingService {

    override fun createClothing(request: CreateClothingRequest) {
        val category = categoryPort.findById(request.categoryId)
            ?: throw NotFoundCategoryException("Not Found Category")

        clothingPort.save(
            clothing = Clothing(
                brand = request.brand,
                price = request.price,
                categoryId = category.id
            ),
            isUpdate = false
        )
    }

    override fun updateClothingById(id: Long, request: UpdateClothingRequest) {
        val clothing = clothingPort.findById(id)
            ?: throw NotFoundClothingException("Not Found Clothing")


        clothingPort.save(
            clothing = clothing.updateClothing(request),
            isUpdate = true
        )
    }

    override fun deleteClothingById(id: Long) {
        val clothing = clothingPort.findById(id)
            ?: throw NotFoundClothingException("Not Found Clothing")

        clothingPort.delete(clothing)
    }
}