Playing chess through a websocket

Modules 

Chess Server 
Controls the logic and state of the game

Terminal Client 
A small client to render the game in ascii 


### Because I keep forgetting 

Rank is Row 
file is Col 


Current theory is When connected to the socket we spin up a GameState.
GameState holds the Game which is the internal representation of the board 

The client sends a bit of Json with Who is making the move and the move in regular chess notation 
e.g 
```
{
    Player: "White",
    Move: "e4"
}
```

We can validate if we agree the player is the one we are expecting then  parse the notation 
We then find the square where the move ends and we step backwards if there is a valid piece that can make that move then it must be valid.
We update the board 
We respond with what internal game state in a bit of json that includes if the move was successful and the current board as a FEN string
e.g 
```
{
    Success: "True",
    board: "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"
}
```

Current unknowns.
The controller has the gameState I dont think thats correct as the controller is likely handling messages for multiple games
So we need to distribute the move to the appropriate game.
Mayhaps we need a Map of GameId to GameState?  