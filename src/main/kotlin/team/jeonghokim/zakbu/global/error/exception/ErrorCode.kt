package team.jeonghokim.zakbu.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    // jwt
    EXPIRED_JWT(401, "Expired JWT"),
    INVALID_JWT(401, "Invalid JWT"),

    // oauth
    OAUTH_EMAIL_NOT_FOUND(400, "Email not found"),
    OAUTH_USERNAME_NOT_FOUND(400, "Username not found"),
    OAUTH_KAKAO_ACCOUNT_NOT_FOUND(400, "Kakao account not found"),
    OAUTH_KAKAO_PROFILE_NOT_FOUND(400, "Kakao profile not found"),
    OAUTH_UNSUPPORTED_PROVIDER(400, "Unsupported provider for authentication"),

    // routine
    ROUTINE_NOT_FOUND(404, "Routine not found")
}
