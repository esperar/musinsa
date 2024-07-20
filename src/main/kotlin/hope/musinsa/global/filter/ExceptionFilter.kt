package hope.musinsa.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import hope.musinsa.global.error.BasicException
import hope.musinsa.global.error.ErrorCode
import hope.musinsa.global.error.ErrorResponse
import hope.musinsa.global.exception.InternalServerErrorException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets


class ExceptionFilter: OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(this::class.simpleName)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        runCatching {
            filterChain.doFilter(request, response)
        }.onFailure { e ->
            when (e) {
                is BasicException -> {
                    log.warn("Exception Occurred - Message = {} | Status = {}",
                        e.message, e.status)
                    sendError(response, ErrorResponse.of(e))
                }
                else -> {
                    log.error("Internal Exception Occurred - Message = {} | Status = {}",
                        e.message, ErrorCode.INTERNAL_SERVER_ERROR.status.value())
                    sendError(response, ErrorResponse.of(InternalServerErrorException("Internal Server Error")))
                }
            }
        }
    }

    private fun sendError(response: HttpServletResponse, errorResponse: ErrorResponse) {
        val responseString = ObjectMapper().writeValueAsString(errorResponse)
        response.status = errorResponse.status
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.writer.write(responseString)
    }

}