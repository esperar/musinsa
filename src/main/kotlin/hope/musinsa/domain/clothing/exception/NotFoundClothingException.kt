package hope.musinsa.domain.clothing.exception

import hope.musinsa.global.error.BasicException
import hope.musinsa.global.error.ErrorCode

class NotFoundClothingException(
    message: String
) : BasicException(message, ErrorCode.NOT_FOUND_CLOTHING.status.value()){
}