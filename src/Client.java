
import controller.GameController;
import model.*;
import strategies.ColWinningStrategy;
import strategies.RowWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

    static Scanner scanner=new Scanner(System.in);
    /* when the person comes to user interface , the person will press the play Button
    i might ask some details for him-through a form, those details will come to me through an API
    i create a game object by a controller and send back the game object- Now the game starts
    Now the person can start the game button-the below code works-internally(check state process)  */

    public static void main(String[] args) {

        GameController gameController=new GameController();
    //*)gameController - it has no state/attribute - its making sure that the right functions are called
        //gameController.startGame();


/*  suppose if somebody else also has to play the game-can they play with the same controller?
        No-> we need to create another Game controller2
        i.e GameController gameController2=new GameController() -it will dictate everything about the same game

    But a question is- in a hotel do we generate/need a new waiter everytime for new client
    No- so should we have a new gamecontroller everytime - the whole issue is because we have composed the Game game in GameController class
    we should take the input instead of fixing it- i.e-we are attaching game to the game controller(it will just take the
    demands-he should not be attached to a single table)
    so send the game attribute in all methods of the class ex- checkState(Game game)      */


    //to start the game i need to certain attributes
        List<Player> players=new ArrayList<>();
        players.add(new HumanPlayer(1 , "Arpit" ,new Symbol('O'), PlayerType.HUMAN ));
        players.add(new BotPlayer(2 , "Botty", PlayerType.BOT, new Symbol('X') , BotDifficultyLevel.EASY));


        //Now no need to create a new game controller - just do as below
        Game game=gameController.startGame(
                3,
                players,
                List.of(new RowWinningStrategy(), new ColWinningStrategy())
        );

        gameController.display(game);
        //Game game2=gameController.startgame();-we can use this reference variable for the below particular functions for that particular game
        //After this change pass the game variable in all methods

        while(gameController.checkState(game).equals(GameState.IN_PROGRESS)){
            //gameController.makeMove();
            gameController.makeMove(game);//client says to gamecontroller hey i am passing you a game-object can you please make a move for this game object, now it goes to game controller class
            //gameController.display();
            gameController.display(game);

            //Ask if the player want to undo
            System.out.println("Do you want to undo the last move? [Y/N]");
            String undoAnswer=scanner.nextLine();//take undo answer
            if(undoAnswer.equals("y")){//if the person says Yes to Undo
                gameController.undo(game);//undo the game
                System.out.println("undo is Successfull !");
                gameController.display(game);//display the game again after undo
            }
        }
/* every time a person has to do a move - when a move is done, the data will come to me int the backend via API
After that we will validate the move (isValid)- if the state is fine -we will send the state back again-otherwise the game will keep on continue
as soon as the state will change we will come out and do the task
 */
        if(gameController.checkState(game).equals(GameState.SUCCESS)){
            System.out.println(gameController.getWinner(game).getName()+ " won the game");
        }
        else if(gameController.checkState(game).equals(GameState.DRAW)){
            System.out.println("Game results in a draw");
        }
    }
}



// 1. create your models
// 2. Get ready your controller and basic interaction with the client
// 3. Make sure that every model has a constructor
// 4. Implement the functionalities one by one
//      a. start the game
//      b. display
//      c. makeMove: game, player , winning Strategy , bot move
//      d. undo