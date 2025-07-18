package team.jeonghokim.zakbu.domain.routine.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.period.domain.Period
import team.jeonghokim.zakbu.domain.routine.domain.Routine
import team.jeonghokim.zakbu.domain.routine.domain.repository.RoutineRepository
import team.jeonghokim.zakbu.domain.routine.presentation.dto.request.RoutineRequest

@Service
class CreateRoutineService(
    private val routineRepository: RoutineRepository
) {
    @Transactional
    fun execute(request: RoutineRequest) {
        val period =
            request.period.run {
                Period(
                    repeat = repeat,
                    activeDay = activeDay
                )
            }

        val routine =
            request.run {
                Routine(
                    routineName = routineName,
                    goal = goal,
                    iconUrl = iconUrl,
                    importance = importance,
                    status = status,
                    startDate = startDate,
                    completed = completed,
                    tag = tag,
                    period = period
                )
            }

        routineRepository.save(routine)
    }
}
