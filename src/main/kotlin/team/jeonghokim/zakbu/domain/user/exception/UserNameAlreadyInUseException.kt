package team.jeonghokim.zakbu.domain.user.exception

import team.jeonghokim.zakbu.global.error.exception.ErrorCode
import team.jeonghokim.zakbu.global.error.exception.ZakBuException

object UserNameAlreadyInUseException : ZakBuException(ErrorCode.USERNAME_ALREADY_IN_USE)
