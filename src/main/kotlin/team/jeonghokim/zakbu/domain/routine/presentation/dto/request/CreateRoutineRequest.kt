package team.jeonghokim.zakbu.domain.routine.presentation.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import team.jeonghokim.zakbu.domain.period.domain.type.Repeat
import team.jeonghokim.zakbu.domain.routine.domain.type.Importance
import team.jeonghokim.zakbu.domain.routine.domain.type.Status
import java.time.DayOfWeek
import java.time.LocalDate

data class CreateRoutineRequest(
    @field:NotBlank(message = "루틴 이름은 필수 입력 값입니다.")
    @field:Size(max = 10, message = "루틴 이름은 10자 이하로 입력해주세요.")
    @JsonProperty("routine_name")
    val routineName: String,
    
    @field:NotBlank(message = "목표는 필수 입력 값입니다.")
    @field:Size(max = 20, message = "목표는 20자 이하로 입력해주세요.")
    val goal: String,
    
    @field:NotBlank(message = "아이콘 URL은 필수 입력 값입니다.")
    @field:Pattern(
        regexp = "^https?://.*",
        message = "유효한 URL 형식이어야 합니다."
    )
    @JsonProperty("icon_url")
    val iconUrl: String,
    
    @field:NotNull(message = "중요도는 필수 선택 값입니다.")
    val importance: Importance,
    
    @field:NotNull(message = "상태는 필수 선택 값입니다.")
    val status: Status,
    
    @field:NotNull(message = "시작일은 필수 입력 값입니다.")
    @JsonProperty("start_date")
    val startDate: LocalDate,

    @JsonProperty("is_completed")
    val isCompleted: Boolean = false,
    
    @field:NotBlank(message = "태그는 필수 입력 값입니다.")
    val tag: String,
    
    @field:Valid
    @field:NotNull(message = "반복 주기 정보는 필수 입력 값입니다.")
    val period: PeriodElement
) {
    data class PeriodElement(
        @field:NotNull(message = "반복 유형은 필수 선택 값입니다.")
        val repeat: Repeat,
        
        @field:NotNull(message = "요일은 필수 선택 값입니다.")
        @JsonProperty("active_day")
        val activeDay: DayOfWeek
    )
}
