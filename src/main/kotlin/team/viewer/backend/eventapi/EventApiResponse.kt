package team.viewer.backend.eventapi

data class EventApiQueryResponse (
    val data: Data
)
data class Data(
    val events: List<Event> = emptyList()
)
data class Event(
    val eventId: Int,
    val name: String
)