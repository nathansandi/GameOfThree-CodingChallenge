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
 ``` 
 
 - Game mode 1 - Automatic game
 - Game mode 2 - Player vs Bot Game
 - Game mode 3 - Multi player Game

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

Example: Game mode 2
```
Welcome to the Game of Three
Enter the player name:Test

 Now, chose one of options to start:
 [1] - BOT vs BOT
 [2] - Player vs BOT
 [3] - 2 Players (2 players must be connected)
 Or Type Quit to exit.2

Game Mode Bot vs Test - Started, the number generated is: 108
1

ERROR: Result is not divisible by 3
-1

ERROR: Result is not divisible by 3
0

 Player : Test // played: 0 // result: 108

 Player: BOT 2 //  received: 36 // winner status is: false

 Player : BOT 2 // played: 0 // result: 36

 Player: Test //  received: 12 // winner status is: false
1

ERROR: Result is not divisible by 3
0

 Player : Test // played: 0 // result: 12

 Player: BOT 2 //  received: 4 // winner status is: false

 Player : BOT 2 // played: -1 // result: 3

 Winner: Test //  result: 1 // winner status is: true

Game finised, type Quit to exit
Quit
```

Example: Game mode 3

**Terminal 1**

```
*** Connected to the game server ***
Welcome to the Game of Three
Enter the player name:Test 1

 Now, chose one of options to start:
 [1] - BOT vs BOT
 [2] - Player vs BOT
 [3] - 2 Players (2 players must be connected)
 Or Type Quit to exit.3

Waiting for the new player.

****NEW GAME STARTED***

Number Generated 329// player Test 1 starts
1

***Turn Results***

 Player : Test 2 // Number received: 37 // Last round output: 1// win: false
0

ERROR: Result is not divisible by 3
-1

***Turn Results***

 Player : Test 2 // Number received: 4 // Last round output: 0// win: false
1

ERROR: Result is not divisible by 3
-1

Game finised, type Quit to exit
```

**Terminal 2**
```
Welcome to the Game of Three
Enter the player name:Test 2

 Now, chose one of options to start:
 [1] - BOT vs BOT
 [2] - Player vs BOT
 [3] - 2 Players (2 players must be connected)
 Or Type Quit to exit.3

Waiting for the new player.

****NEW GAME STARTED***

Number Generated 329// player Test 1 starts
0

ERROR: Not your turn, wait...!

***Turn Results***

 Player : Test 1 // Number received: 110 // Last round output: 1// win: false
1

***Turn Results***

 Player : Test 1 // Number received: 12 // Last round output: -1// win: false
1

ERROR: Result is not divisible by 3
0

***Turn Results***

 Player : Test 1 // Number received: 1 // Last round output: -1// win: true


Game finished - You win!

Game finised, type Quit to exit

```

