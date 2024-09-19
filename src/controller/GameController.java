package controller;

import model.Game;
import model.GameState;
import model.Player;
import strategies.WinningStrategy;

import java.util.List;

public class GameController {

//we should take the Game input instead of fixing it- i.e-we are attaching game to the game controller(it will just take the
//    demands-he should not be attached to a single table) so send the game attribute in all methods of the class ex- checkState(Game game)
        //Game game;//send in all methods
//    public void startGame(){
//        game=new Game();
//    }
    //so the controller(waiter) should not be attached to a client(table in a hotel)- it should serve for every one
    //which game state you want to check
        public Game startGame(
                //these 3 things req from the client
                int dimension,
                List<Player> players,
                List<WinningStrategy>  winningStrategies
        ){
        /* before starting game/creating the game object we should validate first
        1) check the players count == dimension-1
        2) you can have only 1 bot in game
        3) every player should have a separate symbol
        since validation is required we can use Builder Pattern */

            //return new Game();-we will not be creating the game object here-instead
            return Game
                    .getBuilder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .setWinningStrategies(winningStrategies)
                    .build();
            //these validations happen in the builder class
        }

    public GameState checkState(Game game){
        return game.getGameState();
    }

    //which game you want to display
    public void display(Game game){
         game.displayBoard();

    }
    //in which game you want to make the move-there are multiple games running
    public void makeMove(Game game){
        //game controller say it can do the task-but when makeMove method can be called-but business logic is not my forte-
        // so it asks create a method in game class and i will call it
        game.makeMove();
    }

    //which game winner you want to check
    public Player getWinner(Game game){
        return game.getWinner();
    }

    public void undo(Game game){
        game.undo();

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