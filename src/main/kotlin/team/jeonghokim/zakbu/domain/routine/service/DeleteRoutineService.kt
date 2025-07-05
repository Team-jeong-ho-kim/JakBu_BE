package team.jeonghokim.zakbu.domain.routine.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.routine.domain.repository.RoutineRepository
import team.jeonghokim.zakbu.domain.routine.facade.RoutineFacade
import java.util.*

@Service
class DeleteRoutineService(
    private val routineFacade: RoutineFacade,
    private val routineRepository: RoutineRepository
) {
    @Transactional
    fun execute(routineId: UUID) {
        val routine = routineFacade.getRoutineById(routineId)
        routineRepository.delete(routine)
    }
}
