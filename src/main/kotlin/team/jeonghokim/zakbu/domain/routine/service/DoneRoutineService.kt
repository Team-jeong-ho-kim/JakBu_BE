package team.jeonghokim.zakbu.domain.routine.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.routine.facade.RoutineFacade
import java.util.*

@Service
class DoneRoutineService(
    private val routineFacade: RoutineFacade
) {
    @Transactional
    fun execute(routineId: UUID) {
        val routine = routineFacade.getRoutineById(routineId)
        routine.doneRoutine()
    }
}
