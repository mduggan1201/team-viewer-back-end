# Team Viewer Back End

## Description
This application handles requests sent via [Team Viewer Front End](https://github.com/mduggan1201/team-viewer-front-end), creating and sending messages to price maintenance.

When a valid team name is recieved from the Front-End, a query is made to Event API to retrieve information about upcoming events that team is involved in.

## Reqiuirements
- Java 17
- Kotlin 1.16+


#To Do List

## Happy Path
- Receive a message from the front end of a given team name.
- Query Events API for the team name.
- Build a message to return to the front end.
- Send the message to the front end.

## Error Handling
- Check if the team name is valid within Events API
- Check if the team name occurs within the next 30 days
