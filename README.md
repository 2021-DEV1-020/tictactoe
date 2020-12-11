# Tic Tac Toe - API

The api exposes three endpoints

`GET  ==> /board, get all the positions selected from 1 to 9 `

`POST ==> /board/{position}, adds the position and type selected (X or 0)`

`DELETE ==> /board, reset the board to play another game`

`GET ==> /swagger-ui.html, to display the rest services developed in UI`

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
     The numbers below reprensents the positions of the X and O selection
     
                                 | 1 | 2 | 3 |
                                 | 4 | 5 | 6 |
                                 | 7 | 8 | 9 |
                                 

![alt text](https://github.com/[username]/[reponame]/blob/[branch]/swagger.jpg?raw=true)