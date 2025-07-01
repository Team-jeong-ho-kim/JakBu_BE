package team.jeonghokim.zakbu.domain

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.UuidGenerator
import java.util.*

@MappedSuperclass
abstract class BaseEntity(
    @Id
    @GeneratedValue(generator = "uuid2")
    @UuidGenerator
    @Column(
        columnDefinition = "BINARY(16)",
        nullable = false
    )
    val id: UUID?
) : BaseTimeEntity()