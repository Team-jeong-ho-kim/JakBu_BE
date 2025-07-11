package team.jeonghokim.zakbu.domain.auth.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

@RedisHash
class RefreshToken(
    @Id
    val email: String,

    @Indexed
    val refreshToken: String,

    @TimeToLive
    val expirationTime: Long
)
