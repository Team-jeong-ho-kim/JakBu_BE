package team.jeonghokim.zakbu

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class ZakBuBeApplication

fun main(args: Array<String>) {
	runApplication<ZakBuBeApplication>(*args)
}
