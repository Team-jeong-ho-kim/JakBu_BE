package team.jeonghokim.zakbu.domain.period.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import team.jeonghokim.zakbu.domain.BaseUUIDEntity
import team.jeonghokim.zakbu.domain.period.domain.type.Repeat
import java.time.DayOfWeek
import java.util.*

@Entity(name = "tbl_period")
class Period(
    id: UUID?,

    @Column(nullable = false, columnDefinition = "VARCHAR(6)")
    @Enumerated(EnumType.STRING)
    var repeat: Repeat = Repeat.DAILY,

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    @Enumerated(EnumType.ORDINAL)
    var activeDay: DayOfWeek
) : BaseUUIDEntity(id)