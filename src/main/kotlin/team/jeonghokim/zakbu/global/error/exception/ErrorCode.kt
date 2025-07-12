package team.jeonghokim.zakbu.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    // jwt
    EXPIRED_JWT(401, "Expired JWT"),
    INVALID_JWT(401, "Invalid JWT"),

    // oauth
    OAUTH_EMAIL_NOT_FOUND(401, "Email not found"),
    OAUTH_USERNAME_NOT_FOUND(401, "Username not found"),
    OAUTH_KAKAO_ACCOUNT_NOT_FOUND(401, "Kakao account not found"),
    OAUTH_KAKAO_PROFILE_NOT_FOUND(401, "Kakao profile not found"),
    OAUTH_UNSUPPORTED_PROVIDER(400, "Unsupported provider for authentication"),

    // user
    ALREADY_USER_EXISTS(409, "User already exists"),
    USER_NOT_FOUND(404, "User not found"),

    // routine
    ROUTINE_NOT_FOUND(404, "Routine not found")
}
