# worldcupscoreboard

Implementation of the Football World Cup Score Board as a simple library with in-memory store solution.

Football World Cup Score Board. The board supports the following operations:

Start a game. Our data partners will send us data for the games when they start, and these should capture (Initial score is 0 – 0).
a. Home team
b. Away team

Finish game. It will remove a match from the scoreboard.

Update score. Receiving the pair score; home team score and away team score updates a game score.

Get a summary of games by total score. Those games with the same total score will be returned ordered by the most recently added to our system.

This project working with JAVA + Spring Boot.

For build the project compile with maven:
mvn clean install

For testing the project, lunche the tests with maven:
mvn test
