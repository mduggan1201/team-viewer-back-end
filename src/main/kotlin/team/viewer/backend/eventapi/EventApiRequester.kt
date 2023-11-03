package team.viewer.backend.eventapi

import com.skybet.trading.tds.common.logging.TdsLogger
import com.skybet.trading.tds.common.util.SerializerDeserializer
import team.viewer.backend.config.EventApiConfig
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.http.path
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import org.springframework.core.io.ClassPathResource

private val logger = TdsLogger.create {}
@Component
class EventApiRequester (
    private val httpClient: io.ktor.client.HttpClient,
    private val serde: SerializerDeserializer,
    private val eventApiConfig: EventApiConfig
) {
    fun getEventApiResponse(
        teamName:String
    ): EventApiQueryResponse? {
        var deserializedResponse: EventApiQueryResponse?
        runBlocking {
            deserializedResponse = sendRequest(teamName)
        }
        return deserializedResponse
    }

    private suspend fun sendRequest(teamName: String): EventApiQueryResponse? {
        val query = ClassPathResource("event-api-events-query.txt").inputStream.reader().readText()

        val response =
            httpClient.post{
                url{
                    protocol = URLProtocol.HTTPS
                    host = eventApiConfig.host
                    port = 443
                    path(eventApiConfig.path)
                }
                contentType(ContentType.Application.Json)
                header("X-Consumer-ID","mduggan-learn/team-viewer-back-end")
                setBody(mapOf("query" to query.replace("\$teamName", "\"$teamName\"")))
            }
        if (response.status.isSuccess()) {
            return serde.deserialize(response.body<String>(), EventApiQueryResponse::class.java)
        } else {
            logger.info { "Error response from Event API: ${response.status}" }
        }
        return null

    }
}