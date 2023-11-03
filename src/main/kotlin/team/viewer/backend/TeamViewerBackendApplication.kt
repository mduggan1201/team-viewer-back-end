package com.skybet.trading.tds.pm.tool.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class TeamViewerBackendApplication

fun main(args: Array<String>) {
    runApplication<TeamViewerBackendApplication>(*args)
}
