package team.jeonghokim.zakbu.domain.routine.presentation.dto.response

import java.util.*

data class RoutineResponse(
    val routineId: UUID,
    val routineName: String,
    val isCompleted: Boolean
)
