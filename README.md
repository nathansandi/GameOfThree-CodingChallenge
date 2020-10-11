# Game of Three Coding Challenge

**Application properties:**

To configure the application edit the file: resources/application.properties

**Requirements:**

- java 8 
- maven 3

**Start the server:**

To start the game server, open a maven terminal and execute the follow command:
```
mvn exec:java -Dexec.mainClass=com.gameofthree.GameServer
```
The default port on the server is 9998


**Start the client:**

To start the game client, open a maven terminal and execute the follow command:
```
mvn exec:java -Dexec.mainClass=com.gameofthree.GameClient
```

**Game client Demo:**

After starts the game client, and input the game player name, the follow options will be avaliable:
```
Welcome to the Game of Three
Enter the player name:Test

 Now, chose one of options to start:
 [1] - BOT vs BOT
 [2] - Player vs BOT
 [3] - 2 Players (2 players must be connected)
 Or Type Quit to exit.
 
 
 Game mode 1 - Automatic game
 Game mode 2 - Player vs Bot Game
 Game mode 3 - Multi player Game
``` 
 Example: Game mode 1
 ```
  Now, chose one of options to start:
 [1] - BOT vs BOT
 [2] - Player vs BOT
 [3] - 2 Players (2 players must be connected)
 Or Type Quit to exit.1

BOT 2 vs BOT 3

Game Mode Bot vs Bot - Started, the number generated is: 36

 Machine: BOT 2 // played: 0 // result: 36 // winner status is: false

 Machine: BOT 3 // played: 0 // result: 12 // winner status is: false

 Machine: BOT 2 // played: -1 // result: 3 // winner status is: false

 Machine: BOT 3 //  result: 1 // winner status is: true

Game finised, type Quit to exit
Quit
```