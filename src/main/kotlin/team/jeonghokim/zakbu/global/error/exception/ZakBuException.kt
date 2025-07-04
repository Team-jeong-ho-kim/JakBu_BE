package team.jeonghokim.zakbu.global.error.exception

abstract class ZakBuException(
    val errorCode: ErrorCode,
) : RuntimeException()
