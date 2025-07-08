package team.jeonghokim.zakbu.domain.auth.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

@RedisHash
class RefreshToken(
    @Id
    private val email: String,

    @Indexed
    private val refreshToken: String,

    @TimeToLive
    private val expirationTime: Long
)
