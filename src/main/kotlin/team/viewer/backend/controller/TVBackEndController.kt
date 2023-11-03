package team.viewer.backend.controller

import com.skybet.trading.tds.common.util.SerializerDeserializer
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController
import team.viewer.backend.eventapi.EventApiRequester
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

private const val EVENT_API_ERROR_CODE = 512

@CrossOrigin(origins = ["*"])
@RestController
class TVBackEndController (
    val serializerDeserializer: SerializerDeserializer,
    val eventApiRequester: EventApiRequester
) {
    @PostMapping("/team")
    fun post(
        @RequestBody incomingRequestBody: IncomingRequestBody
    ): ResponseEntity<String> {
        val eventApiResponse = eventApiRequester.getEventApiResponse(incomingRequestBody.teamName)
            ?: return ResponseEntity(
            "Event API Response was invalid for Team Name: ${incomingRequestBody.teamName}",
            null,
            EVENT_API_ERROR_CODE
        )
    return ResponseEntity(eventApiResponse.toString(), HttpStatus.OK)
    }
}