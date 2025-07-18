package team.jeonghokim.zakbu.domain.routine.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.routine.domain.repository.RoutineRepository
import team.jeonghokim.zakbu.domain.routine.presentation.dto.response.GetRoutinesResponse
import team.jeonghokim.zakbu.domain.routine.presentation.dto.response.RoutineResponse

@Service
class GetAllRoutineService(
    private val routineRepository: RoutineRepository
) {
    @Transactional(readOnly = true)
    fun execute(): GetRoutinesResponse {
        return GetRoutinesResponse(
            routineRepository.findAll().map { routine ->
                RoutineResponse(
                    routine.id,
                    routine.routineName,
                    routine.completed
                )
            }
        )
    }
}
