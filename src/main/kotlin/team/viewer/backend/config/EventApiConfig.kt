package team.viewer.backend.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("eventapi")
@ConstructorBinding
data class EventApiConfig(
    val host: String,
    val path: String
)