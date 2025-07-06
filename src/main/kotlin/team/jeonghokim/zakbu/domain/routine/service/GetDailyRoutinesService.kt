package team.jeonghokim.zakbu.domain.routine.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.routine.domain.repository.RoutineRepository
import team.jeonghokim.zakbu.domain.routine.exception.RoutineNotFoundException
import team.jeonghokim.zakbu.domain.routine.presentation.dto.response.GetRoutinesResponse
import team.jeonghokim.zakbu.domain.routine.presentation.dto.response.RoutineResponse
import java.time.LocalDate

@Service
class GetDailyRoutinesService(
    private val routineRepository: RoutineRepository
) {
    @Transactional(readOnly = true)
    fun execute(date: LocalDate): GetRoutinesResponse {
        val routines = routineRepository.findAllByCompletedFalse() ?: throw RoutineNotFoundException
        return GetRoutinesResponse(
            routines.filter { it.startDate <= date }
                .sortedBy { it.startDate }
                .map { routine ->
                    RoutineResponse(
                        routine.id,
                        routine.routineName,
                        routine.completed
                    )
                }
            )
    }
}
