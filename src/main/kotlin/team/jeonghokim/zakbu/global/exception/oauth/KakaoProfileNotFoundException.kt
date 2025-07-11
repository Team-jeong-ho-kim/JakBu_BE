package team.jeonghokim.zakbu.global.exception.oauth

import team.jeonghokim.zakbu.global.error.exception.ErrorCode
import team.jeonghokim.zakbu.global.error.exception.ZakBuException

object KakaoProfileNotFoundException : ZakBuException(ErrorCode.OAUTH_KAKAO_PROFILE_NOT_FOUND)
