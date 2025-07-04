package team.jeonghokim.zakbu.domain.routine.presentation

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import team.jeonghokim.zakbu.domain.routine.presentation.dto.request.CreateRoutineRequest
import team.jeonghokim.zakbu.domain.routine.service.CreateRoutineService

@RestController
@RequestMapping("/routines")
class RoutineController(
    private val createRoutineService: CreateRoutineService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRoutine(
        @RequestBody @Valid
        request: CreateRoutineRequest
    ) {
        createRoutineService.execute(request)
    }
}
