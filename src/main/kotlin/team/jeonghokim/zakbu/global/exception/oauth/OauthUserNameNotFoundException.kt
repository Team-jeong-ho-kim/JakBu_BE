package team.jeonghokim.zakbu.global.exception.oauth

import team.jeonghokim.zakbu.global.error.exception.ErrorCode
import team.jeonghokim.zakbu.global.error.exception.ZakBuException

object OauthUserNameNotFoundException : ZakBuException(ErrorCode.OAUTH_USERNAME_NOT_FOUND)
