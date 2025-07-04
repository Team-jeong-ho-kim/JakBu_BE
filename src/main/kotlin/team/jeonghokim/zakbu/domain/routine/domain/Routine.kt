package team.jeonghokim.zakbu.domain.routine.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import team.jeonghokim.zakbu.domain.BaseEntity
import team.jeonghokim.zakbu.domain.period.domain.Period
import team.jeonghokim.zakbu.domain.routine.domain.type.Importance
import team.jeonghokim.zakbu.domain.routine.domain.type.Status
import java.time.LocalDate

@Entity(name = "tbl_routine")
class Routine(
    @Column(name = "routine_name", nullable = false, columnDefinition = "VARCHAR(10)")
    var routineName: String,
    @Column(name = "goal", nullable = false, columnDefinition = "VARCHAR(20)")
    var goal: String,
    @Column(name = "icon_url", nullable = false, columnDefinition = "VARCHAR(255)")
    var iconUrl: String,
    @Column(nullable = false, columnDefinition = "VARCHAR(16)")
    @Enumerated(EnumType.STRING)
    var importance: Importance,
    @Column(nullable = false, columnDefinition = "VARCHAR(8)")
    @Enumerated(EnumType.STRING)
    var status: Status,
    @Column(name = "start_date", nullable = false)
    var startDate: LocalDate,
    @Column(name = "is_completed", nullable = false)
    var isCompleted: Boolean = false,
    @Column(name = "tag", nullable = false, columnDefinition = "VARCHAR(10)")
    val tag: String,
    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "period_id", nullable = false)
    val period: Period,
    // user entity와 연관관계 설정
) : BaseEntity()
