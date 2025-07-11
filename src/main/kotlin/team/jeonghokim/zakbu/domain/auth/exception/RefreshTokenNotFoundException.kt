package team.jeonghokim.zakbu.domain.auth.exception

import team.jeonghokim.zakbu.global.error.exception.ErrorCode
import team.jeonghokim.zakbu.global.error.exception.ZakBuException

object RefreshTokenNotFoundException : ZakBuException(ErrorCode.REFRESH_TOKEN_NOT_FOUND)
