package hope.musinsa.global.exception

import hope.musinsa.global.error.BasicException
import hope.musinsa.global.error.ErrorCode

class InternalServerErrorException(
    message: String
) : BasicException(message, ErrorCode.INTERNAL_SERVER_ERROR.status.value())