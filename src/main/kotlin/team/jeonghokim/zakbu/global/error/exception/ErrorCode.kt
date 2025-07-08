package team.jeonghokim.zakbu.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    // jwt
    EXPIRED_JWT(401, "Expired JWT"),
    INVALID_JWT(401, "Invalid JWT"),

    // oauth
    EMAIL_NOT_FOUND(401, "Email not found"),

    // routine
    ROUTINE_NOT_FOUND(404, "Routine not found")
}
