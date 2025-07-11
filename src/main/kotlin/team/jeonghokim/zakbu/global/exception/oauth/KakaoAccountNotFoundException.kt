package team.jeonghokim.zakbu.global.exception.oauth

import team.jeonghokim.zakbu.global.error.exception.ErrorCode
import team.jeonghokim.zakbu.global.error.exception.ZakBuException

object KakaoAccountNotFoundException : ZakBuException(ErrorCode.OAUTH_KAKAO_ACCOUNT_NOT_FOUND)
