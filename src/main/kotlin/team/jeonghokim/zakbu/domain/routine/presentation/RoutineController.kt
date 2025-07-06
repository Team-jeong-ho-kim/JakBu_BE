package team.jeonghokim.zakbu.domain.routine.presentation

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import team.jeonghokim.zakbu.domain.routine.presentation.dto.request.RoutineRequest
import team.jeonghokim.zakbu.domain.routine.presentation.dto.response.GetRoutinesResponse
import team.jeonghokim.zakbu.domain.routine.service.CreateRoutineService
import team.jeonghokim.zakbu.domain.routine.service.DeleteRoutineService
import team.jeonghokim.zakbu.domain.routine.service.DoneRoutineService
import team.jeonghokim.zakbu.domain.routine.service.GetAllRoutineService
import team.jeonghokim.zakbu.domain.routine.service.GetDailyRoutinesService
import team.jeonghokim.zakbu.domain.routine.service.ModifyRoutineService
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/routines")
class RoutineController(
    private val createRoutineService: CreateRoutineService,
    private val modifyRoutineService: ModifyRoutineService,
    private val deleteRoutineService: DeleteRoutineService,
    private val doneRoutineService: DoneRoutineService,
    private val getAllRoutineService: GetAllRoutineService,
    private val getDailyRoutinesService: GetDailyRoutinesService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRoutine(
        @RequestBody @Valid
        request: RoutineRequest
    ) {
        createRoutineService.execute(request)
    }

    @PatchMapping("/{routine-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun modifyRoutine(
        @PathVariable("routine-id") routineId: UUID,
        @RequestBody @Valid request: RoutineRequest
    ) {
        modifyRoutineService.execute(routineId, request)
    }

    @DeleteMapping("/{routine-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteRoutine(@PathVariable("routine-id") routineId: UUID) {
        deleteRoutineService.execute(routineId)
    }

    @PatchMapping("/{routine-id}/done")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun doneRoutine(@PathVariable("routine-id") routineId: UUID) {
        doneRoutineService.execute(routineId)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllRoutine(): GetRoutinesResponse = getAllRoutineService.execute()

    @GetMapping(params = ["date"])
    @ResponseStatus(HttpStatus.OK)
    fun getDailyRoutines(@RequestParam date: LocalDate): GetRoutinesResponse = getDailyRoutinesService.execute(date)
}
