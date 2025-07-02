package team.jeonghokim.zakbu.domain.auth.domain.exception

import team.jeonghokim.zakbu.global.error.excpetion.ErrorCode
import team.jeonghokim.zakbu.global.error.excpetion.ZakBuException

object ExpiredJwtException: ZakBuException(ErrorCode.EXPIRED_JWT)