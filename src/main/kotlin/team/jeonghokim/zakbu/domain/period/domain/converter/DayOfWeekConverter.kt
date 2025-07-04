package team.jeonghokim.zakbu.domain.period.domain.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.time.DayOfWeek

@Converter
internal class DayOfWeekConverter : AttributeConverter<DayOfWeek, Int> {
    override fun convertToDatabaseColumn(attribute: DayOfWeek): Int {
        return attribute.value
    }

    override fun convertToEntityAttribute(dbData: Int): DayOfWeek {
        return dbData.let { DayOfWeek.of(it) }
    }
}
