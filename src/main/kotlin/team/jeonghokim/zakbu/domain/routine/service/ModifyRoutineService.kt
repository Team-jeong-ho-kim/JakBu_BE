package team.jeonghokim.zakbu.domain.routine.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.period.domain.Period
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

        routine.modifyRoutine(
            routineName = request.routineName,
            goal = request.goal,
            iconUrl = request.iconUrl,
            importance = request.importance,
            status = request.status,
            startDate = request.startDate,
            isCompleted = request.isCompleted,
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
