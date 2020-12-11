# Tic Tac Toe - API

The api exposes three endpoints

`GET  ==> /board`

`POST ==> /board/{position}`

`GET ==> /swagger-ui.html`

Requirements
For building and running the application you need:

    JDK 1.8
    Maven 3

How to install

    mvn clean install

There are many ways to run spring boot applications :

    run the main methode in the class com.bnppf.tictactoe.TicTacToeApplication
    run the jar generated in the target after compiling the applicaiton using : java -jar .\tictactoe-0.0.1-SNAPSHOT.jar
    run the following maven command mvn spring-boot:run

 Rules
 
 The rules are described below :
 
     X always goes first.
     Players cannot play on a played position.
     Players alternate placing X’s and O’s on the board until either:
     One player has three in a row, horizontally, vertically or diagonally
     All nine squares are filled.
     If a player is able to draw three X’s or three O’s in a row, that player wins.
     If all nine squares are filled and neither player has three in a row, the game is a draw.