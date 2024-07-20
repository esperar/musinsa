package hope.musinsa.domain.clothing.exception

import hope.musinsa.global.error.BasicException
import hope.musinsa.global.error.ErrorCode

class NotFoundCategoryException(
    message: String
) : BasicException(message, ErrorCode.NOT_FOUND_CATEGORY.status.value()){
}