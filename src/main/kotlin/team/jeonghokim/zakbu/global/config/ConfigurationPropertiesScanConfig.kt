package team.jeonghokim.zakbu.global.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(basePackages = ["team.jeonghokim.zakbu"])
class ConfigurationPropertiesScanConfig
