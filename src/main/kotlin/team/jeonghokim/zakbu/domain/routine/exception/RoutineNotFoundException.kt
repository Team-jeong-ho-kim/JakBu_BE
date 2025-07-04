package team.jeonghokim.zakbu.domain.routine.exception

import team.jeonghokim.zakbu.global.error.exception.ErrorCode
import team.jeonghokim.zakbu.global.error.exception.ZakBuException

object RoutineNotFoundException : ZakBuException(
    ErrorCode.ROUTINE_NOT_FOUND
)
