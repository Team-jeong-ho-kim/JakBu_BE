package team.jeonghokim.zakbu.domain.user.exception

import team.jeonghokim.zakbu.global.error.exception.ErrorCode
import team.jeonghokim.zakbu.global.error.exception.ZakBuException

object UserNotFoundException : ZakBuException(ErrorCode.USER_NOT_FOUND)
