package controller;

import model.Game;
import model.GameState;
import model.Player;

public class GameController {

//we should take the Game input instead of fixing it- i.e-we are attaching game to the game controller(it will just take the
//    demands-he should not be attached to a single table) so send the game attribute in all methods of the class ex- checkState(Game game)
        Game game;//send in all methods
//    public void startGame(){
//        game=new Game();
//    }
    //so the controller(waiter) should not be attached to a client(table in a hotel)- it should serve for every one
    //which game state you want to check
        public Game startGame(){
            return new Game();
        }

    public GameState checkState(Game game){
        return null;
    }

    //which game you want to display
    public void display(Game game){

    }
    //in which game you want to make the move-there are multiple games running
    public void makeMove(Game game){

    }

    //which game winner you want to check
    public Player getWinner(Game game){
        return null;
    }

    public void undo(Game game){

    }
}


// 1. start the game : create , set the attributes, taking the input
// while the gameState IN_PROGRESS
// 2. display the board
// 3. Make the move : input , make the move, check for the winner ,update the state
// 4. check the state
// if gameState is SUCCESS
// 5. get the winner and display
// else if gameState is DRAW
// 6. declare the draw