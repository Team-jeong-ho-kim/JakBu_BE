package team.jeonghokim.zakbu.global.entity

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.UuidGenerator
import java.util.UUID

@MappedSuperclass
abstract class BaseUUIDEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @UuidGenerator
    @Column(
        columnDefinition = "BINARY(16)",
        nullable = false
    )
    lateinit var id: UUID
}
