package team.jeonghokim.zakbu.domain.user.exception

import team.jeonghokim.zakbu.global.error.exception.ErrorCode
import team.jeonghokim.zakbu.global.error.exception.ZakBuException

object AlreadyUserExistsException : ZakBuException(ErrorCode.ALREADY_USER_EXISTS)
