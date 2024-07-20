package hope.musinsa.domain.clothing.application

import hope.musinsa.domain.clothing.application.dto.request.CreateClothingRequest
import hope.musinsa.domain.clothing.application.dto.request.UpdateClothingRequest

interface CommandClothingService {
    fun createClothing(request: CreateClothingRequest)
    fun updateClothingById(id: Long, request: UpdateClothingRequest)
    fun deleteClothingById(id: Long)
}