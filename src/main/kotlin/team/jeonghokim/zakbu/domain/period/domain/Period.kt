package team.jeonghokim.zakbu.domain.period.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import team.jeonghokim.zakbu.domain.BaseUUIDEntity
import team.jeonghokim.zakbu.domain.period.domain.type.Repeat
import java.time.DayOfWeek

@Entity(name = "tbl_period")
class Period(
    @Column(name = "`repeat`", nullable = false, columnDefinition = "VARCHAR(7)")
    @Enumerated(EnumType.STRING)
    var repeat: Repeat = Repeat.DAILY,
    @Column(nullable = false, columnDefinition = "VARCHAR(9)")
    @Enumerated(EnumType.STRING)
    var activeDay: DayOfWeek,
) : BaseUUIDEntity()
