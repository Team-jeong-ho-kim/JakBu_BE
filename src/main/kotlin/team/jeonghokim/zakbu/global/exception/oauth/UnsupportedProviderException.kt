package team.jeonghokim.zakbu.global.exception.oauth

import team.jeonghokim.zakbu.global.error.exception.ErrorCode
import team.jeonghokim.zakbu.global.error.exception.ZakBuException

object UnsupportedProviderException : ZakBuException(ErrorCode.OAUTH_UNSUPPORTED_PROVIDER)
