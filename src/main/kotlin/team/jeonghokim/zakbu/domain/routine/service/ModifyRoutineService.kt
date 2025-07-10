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
    private val routineFacade: RoutineFacade
) {
    @Transactional
    fun execute(routineId: UUID, request: RoutineRequest) {
        val routine = routineFacade.getRoutineById(routineId)

        request.run {
            routine.modifyRoutine(
                routineName = routineName,
                goal = goal,
                iconUrl = iconUrl,
                importance = importance,
                status = status,
                startDate = startDate,
                completed = completed,
                tag = tag,
                period = period.run {
                    Period(
                        repeat = repeat,
                        activeDay = activeDay
                    )
                }
            )
        }
    }
}
