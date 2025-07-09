package team.jeonghokim.zakbu.global.exception.oauth

import team.jeonghokim.zakbu.global.error.exception.ErrorCode
import team.jeonghokim.zakbu.global.error.exception.ZakBuException

object EmailNotFoundException : ZakBuException(ErrorCode.OAUTH_EMAIL_NOT_FOUND)
