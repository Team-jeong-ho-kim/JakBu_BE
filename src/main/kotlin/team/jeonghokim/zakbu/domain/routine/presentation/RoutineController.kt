package team.jeonghokim.zakbu.domain.routine.presentation

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.jeonghokim.zakbu.domain.routine.presentation.dto.request.CreateRoutineRequest
import team.jeonghokim.zakbu.domain.routine.service.CreateRoutineService

@RestController
@RequestMapping("/routines")
class RoutineController(
    private val createRoutineService: CreateRoutineService
) {
    @PostMapping
    fun createRoutine(request: CreateRoutineRequest) {
        createRoutineService.execute(request)
    }
}