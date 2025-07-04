package team.jeonghokim.zakbu.global.exception

import team.jeonghokim.zakbu.global.error.exception.ErrorCode
import team.jeonghokim.zakbu.global.error.exception.ZakBuException

object ExpiredJwtException: ZakBuException(ErrorCode.EXPIRED_JWT)