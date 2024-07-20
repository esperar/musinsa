package hope.musinsa.global.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val message: String,
    val status: HttpStatus
) {

    // global
    BAD_REQUEST("Bad Request", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),

    // clothing
    NOT_FOUND_CLOTHING("Not Found Clothing", HttpStatus.NOT_FOUND),

    // category
    NOT_FOUND_CATEGORY("Not Found Category", HttpStatus.NOT_FOUND)
}