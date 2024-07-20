package hope.musinsa.domain.clothing.application

import hope.musinsa.annotation.MusinsaTest
import hope.musinsa.domain.clothing.application.dto.request.CreateClothingRequest
import hope.musinsa.domain.clothing.application.dto.request.UpdateClothingRequest
import hope.musinsa.domain.clothing.domain.Category
import hope.musinsa.domain.clothing.domain.Clothing
import hope.musinsa.domain.clothing.persistence.CategoryPersistenceAdapter
import hope.musinsa.domain.clothing.persistence.ClothingPersistenceAdapter
import hope.musinsa.domain.clothing.persistence.entity.CategoryEntity
import hope.musinsa.domain.clothing.persistence.entity.ClothingCategory
import hope.musinsa.domain.clothing.persistence.entity.ClothingEntity
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.given

@MusinsaTest
internal class CommandClothingServiceImplTest {

    @Mock
    private lateinit var clothingPort: ClothingPersistenceAdapter

    @Mock
    private lateinit var categoryPort: CategoryPersistenceAdapter

    private lateinit var commandClothingService: CommandClothingServiceImpl

    private val categoryStub by lazy {
        Category(
            id = 1,
            category = ClothingCategory.TOP
        )
    }

    private val clothingStub by lazy {
        Clothing(
            id = 1,
            brand = "A",
            price = 1000,
            categoryId = 1
        )
    }

    private val categoryEntityStub by lazy {
        CategoryEntity(
            1,
            ClothingCategory.TOP
        )
    }

    private val clothingEntityStub by lazy {
        ClothingEntity(
            id = 1,
            brand = "A",
            price = 1000,
            category = categoryEntityStub
        )
    }

    private val createClothingRequestStub by lazy {
        CreateClothingRequest(
            categoryId = 1,
            brand = "A",
            price = 1000
        )
    }

    private val updateClothingRequestStub by lazy {
        UpdateClothingRequest(
            categoryId = 1,
            brand = "B",
            price = 2000
        )
    }

    @BeforeEach
    fun setUp() {
        commandClothingService = CommandClothingServiceImpl(
            clothingPort, categoryPort
        )
    }

    @Test
    fun `success_create_clothing`() {
        given(categoryPort.findById(createClothingRequestStub.categoryId))
            .willReturn(categoryStub)

        given(clothingPort.save(any(), any()))
            .willReturn(clothingStub)

        assertDoesNotThrow {
            commandClothingService.createClothing(createClothingRequestStub)
        }
    }

    @Test
    fun `success_update_clothing`() {
        given(clothingPort.findById(1))
            .willReturn(clothingStub)

        given(clothingPort.save(any(), any()))
            .willReturn(clothingStub)

        assertDoesNotThrow {
            commandClothingService.updateClothingById(1, updateClothingRequestStub)
        }
    }

}