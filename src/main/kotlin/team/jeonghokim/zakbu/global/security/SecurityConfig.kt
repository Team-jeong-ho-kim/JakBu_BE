package team.jeonghokim.zakbu.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import team.jeonghokim.zakbu.domain.auth.service.CustomOauth2UserService
import team.jeonghokim.zakbu.global.error.GlobalExceptionFilter
import team.jeonghokim.zakbu.global.oauth.handler.Oauth2FailureHandler
import team.jeonghokim.zakbu.global.oauth.handler.Oauth2SuccessHandler
import team.jeonghokim.zakbu.global.security.jwt.JwtTokenFilter
import team.jeonghokim.zakbu.global.security.jwt.JwtTokenProvider

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val objectMapper: ObjectMapper
) {
    @Bean
    @Throws(Exception::class)
    fun filterChain(
        http: HttpSecurity,
        customOauth2UserService: CustomOauth2UserService,
        successHandler: Oauth2SuccessHandler,
        failureHandler: Oauth2FailureHandler
    ): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .cors { it.disable() }
            .formLogin { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .oauth2Login{
                it
                    .redirectionEndpoint{ it.baseUri("/oauth2/{registration-id}") }
                    .successHandler(successHandler)
                    .failureHandler(failureHandler)
                    .userInfoEndpoint { userInfo ->
                        userInfo.userService(customOauth2UserService)
                }
            }
            .authorizeHttpRequests{
                it
                    .requestMatchers("/oauth2/**").permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(
                JwtTokenFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .addFilterBefore(
                GlobalExceptionFilter(objectMapper),
                JwtTokenFilter::class.java
            )
            .build()
    }
}
