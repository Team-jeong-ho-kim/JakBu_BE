package team.jeonghokim.zakbu.domain.routine.facade

import org.springframework.stereotype.Component
import team.jeonghokim.zakbu.domain.routine.domain.Routine
import team.jeonghokim.zakbu.domain.routine.domain.repository.RoutineRepository
import team.jeonghokim.zakbu.domain.routine.exception.RoutineNotFoundException
import java.util.*

@Component
class RoutineFacade(
    private val routineRepository: RoutineRepository
) {
    fun getRoutineById(routineId: UUID): Routine =
        routineRepository.findRoutineById(routineId) ?: throw RoutineNotFoundException
}
