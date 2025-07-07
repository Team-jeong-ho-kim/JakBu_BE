package team.jeonghokim.zakbu.domain.routine.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.jeonghokim.zakbu.domain.routine.domain.Routine
import java.util.UUID

interface RoutineRepository : JpaRepository<Routine, UUID> {
    fun findRoutineById(routineId: UUID): Routine?
}
