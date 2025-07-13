package team.jeonghokim.zakbu.domain.user.exception

import team.jeonghokim.zakbu.global.error.exception.ErrorCode
import team.jeonghokim.zakbu.global.error.exception.ZakBuException

object EmailAlreadyInUseException : ZakBuException(ErrorCode.EMAIL_ALREADY_IN_USE)
