package team.jeonghokim.zakbu.global.error.excpetion

abstract class ZakBuException(
    val errorCode: ErrorCode,
) : RuntimeException()
