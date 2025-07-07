package team.jeonghokim.zakbu.domain.routine.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.routine.domain.repository.RoutineRepository
import team.jeonghokim.zakbu.domain.routine.presentation.dto.response.GetAllRoutineResponse
import team.jeonghokim.zakbu.domain.routine.presentation.dto.response.RoutineResponse

@Service
class GetAllRoutineService(
    private val routineRepository: RoutineRepository
) {
    @Transactional(readOnly = true)
    fun execute(): GetAllRoutineResponse {
        return GetAllRoutineResponse(
            routineRepository.findAll().map { routine ->
                RoutineResponse(
                    routine.id,
                    routine.routineName,
                    routine.isCompleted
                )
            }
        )
    }
}
