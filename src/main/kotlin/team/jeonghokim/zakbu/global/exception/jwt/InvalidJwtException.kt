package team.jeonghokim.zakbu.global.exception.jwt

import team.jeonghokim.zakbu.global.error.exception.ErrorCode
import team.jeonghokim.zakbu.global.error.exception.ZakBuException

object InvalidJwtException : ZakBuException(ErrorCode.INVALID_JWT)
