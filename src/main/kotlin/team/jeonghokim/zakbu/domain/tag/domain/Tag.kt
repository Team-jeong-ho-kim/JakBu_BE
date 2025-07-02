package team.jeonghokim.zakbu.domain.tag.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import team.jeonghokim.zakbu.domain.BaseEntity
import java.util.UUID

@Entity(name = "tbl_tag")
class Tag(
    id: UUID?,

    @Column(name = "tag_name", nullable = false, columnDefinition = "VARCHAR(10)")
    var tagName: String
) : BaseEntity(id)