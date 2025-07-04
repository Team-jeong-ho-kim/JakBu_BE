package team.jeonghokim.zakbu.domain.routine.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.routine.domain.repository.RoutineRepository
import team.jeonghokim.zakbu.domain.routine.exception.RoutineNotFoundException
import java.util.*

@Service
class DeleteRoutineService(
    private val routineRepository: RoutineRepository
) {
    @Transactional
    fun execute(routineId: UUID) {
        val routine = routineRepository.findRoutineById(routineId)
            ?: throw RoutineNotFoundException

        routineRepository.delete(routine)
    }
}
