package team.jeonghokim.zakbu.domain.auth.domain.exception

import team.jeonghokim.zakbu.global.error.excpetion.ErrorCode
import team.jeonghokim.zakbu.global.error.excpetion.ZakBuException

object InvalidJwtException: ZakBuException(ErrorCode.INVALID_JWT)