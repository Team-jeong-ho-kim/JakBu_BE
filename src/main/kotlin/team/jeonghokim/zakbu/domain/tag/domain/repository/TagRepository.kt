package team.jeonghokim.zakbu.domain.tag.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.jeonghokim.zakbu.domain.tag.domain.Tag
import java.util.UUID

interface TagRepository : JpaRepository<Tag, UUID>