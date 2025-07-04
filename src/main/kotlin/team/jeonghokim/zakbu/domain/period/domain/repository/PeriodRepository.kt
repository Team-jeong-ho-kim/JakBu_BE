package team.jeonghokim.zakbu.domain.period.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.jeonghokim.zakbu.domain.period.domain.Period
import java.util.UUID

interface PeriodRepository : JpaRepository<Period, UUID>
