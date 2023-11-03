package team.viewer.backend.controller

data class IncomingRequestBody (
    val teamName: String
)

// Could add an extension function to check if the team name is valid