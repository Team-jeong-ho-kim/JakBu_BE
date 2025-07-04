package team.jeonghokim.zakbu.domain.routine.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.routine.domain.repository.RoutineRepository
import team.jeonghokim.zakbu.domain.routine.exception.RoutineNotFoundException
import team.jeonghokim.zakbu.domain.routine.presentation.dto.request.ModifyRoutineRequest
import java.util.*

@Service
class ModifyRoutineService(
    private val routineRepository: RoutineRepository
) {
    @Transactional
    fun execute(routineId: UUID, request: ModifyRoutineRequest) {
        val routine = routineRepository.findRoutineById(routineId)
            ?: throw RoutineNotFoundException

        routine.modifyRoutine(request)

        routineRepository.save(routine)
    }
}
