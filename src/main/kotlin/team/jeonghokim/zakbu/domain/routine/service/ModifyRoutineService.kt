package team.jeonghokim.zakbu.domain.routine.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.period.domain.Period
import team.jeonghokim.zakbu.domain.routine.domain.repository.RoutineRepository
import team.jeonghokim.zakbu.domain.routine.facade.RoutineFacade
import team.jeonghokim.zakbu.domain.routine.presentation.dto.request.RoutineRequest
import java.util.*

@Service
class ModifyRoutineService(
    private val routineFacade: RoutineFacade,
    private val routineRepository: RoutineRepository
) {
    @Transactional
    fun execute(routineId: UUID, request: RoutineRequest) {
        val routine = routineFacade.getRoutineById(routineId)

        routine.modifyRoutine(
            routineName = request.routineName,
            goal = request.goal,
            iconUrl = request.iconUrl,
            importance = request.importance,
            status = request.status,
            startDate = request.startDate,
            completed = request.completed,
            tag = request.tag,
            period = request.period.run {
                Period(
                    repeat = repeat,
                    activeDay = activeDay
                )
            }
        )

        routineRepository.save(routine)
    }
}
