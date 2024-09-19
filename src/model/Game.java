package model;

import strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

public class Game {

    private Board board;
    private List<Player> players;
    private Player winner;
    private int nextPlayerIndex;
    private List<Move> moves;
    private GameState gameState;
    //winning Strategies
    private List<WinningStrategy> winningStrategies;

    //private Game Constructor - will be expecting a builder object
    private Game(Builder builder){
        board=new Board(builder.dimension);
        players=builder.players;
        winningStrategies=builder.winningStrategies;
        winner=null;
        nextPlayerIndex=0;
        moves=new ArrayList<>();
        gameState=GameState.IN_PROGRESS;
    }

    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }
    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }
    public List<Move> getMoves(){
        return moves;
    }
    public void setMoves(List<Move> moves){
        this.moves=moves;
    }
    public GameState getGameState(){
        return gameState;
    }
    public void setGameState(GameState gameState){
        this.gameState=gameState;
    }


    //create the object of the builder
    public static Builder getBuilder(){
        return new Builder();
    }
    public void displayBoard() {
        board.display();
    }

    //we didn't have any makemove method -so let's create it and write the business logic here, and game-controller will call it
    public void makeMove(){
    //get the current player - this is from the list of players and index(term)
        Player currentPlayer=players.get(nextPlayerIndex);

    //once we got current player - you will tell this player that it's your turn, can you make the move
        System.out.println("It's "+ currentPlayer.getName() + " 's turn! Please make the move");

    //now is game supposes make the move or player should make the move-player should do
    //so let's have the remaining function in player class

    //let's do the validation here (validation can also be done in Player class - explanation given)
        Move move=currentPlayer.makeMove(board);
    //validate this move through below validateMove method
        if(!validateMove(move)){
            System.out.println("Invalid Move! please try again.");
            return;
        }
    //if the move is valid then update the actual cell as right now we are in temporary cell
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();

        Cell cellToChange= board.getGrid().get(row).get(col);
    //2 parameters we need to update when we are making the move- cellstate & Symbol
        cellToChange.setCellState(CellState.FILLED);
        cellToChange.setSymbol(currentPlayer.getSymbol());//cell has been updated, now update the current move

        move.setCell(cellToChange);
        move.setPlayer(currentPlayer);//we can make sure if the player added properly
        moves.add(move);//add this correct move to the moves list so that undo can work fine

   //from line 108-115 the cell is update in the sense board is updated\\- as board contain cell

    //After this we need to update nextPlayer index
        nextPlayerIndex++;
        nextPlayerIndex %= players.size();//we need to modulo here as when the last person turn is completed we need to come back to zero /0th player

    //we need to confirm if there is a winner/change in game state, after each move, so write a winner check function
        if(checkWinner(move)){//if winner is present then set the game state
            setWinner(currentPlayer);
            setGameState(GameState.SUCCESS);//set the game state
        }
    //if all the moves are done then set the game state as draw
        else if(moves.size() == board.getSize() * board.getSize()){
            setWinner(null);
            setGameState(GameState.DRAW);
        }
    //otherwise let's continue the game
    }

    public boolean checkWinner(Move move){
    //we might have multiple ways of winning - so check each way
        for(WinningStrategy strategy: winningStrategies){
            if(strategy.checkWinner(board,move)){
                return true;
            }
        }
        return false;
    }
    //what we need to validate-
    public boolean validateMove(Move move){

        int row=move.getCell().getRow();
        int col=move.getCell().getCol();
        // 1) check if row/col is not out of the board boundary-
        if(row<0 || row> board.getSize()-1 || col<0 || col> board.getSize()-1){
            return false;
        }
    /* 2) cell isn't occupied before the move
    from the board i have got grid of particular row and column-i will check if it is empty- if true then the move is valid
    if false then the grid was not empty before & the move is incorrect */
        return board.getGrid().get(row).get(col).getCellState().equals(CellState.EMPTY);

    }

    public void undo(){
    //whatever we did while making the move, we basically need to reverse that
        if(moves.isEmpty()){
            System.out.println("Nothing to undo!");
        }
    //if move present then remove the move from the list
        Move lastMove=moves.get(moves.size()-1);
        moves.remove(moves.size()-1);

    //once removed from the list also update the cell of the move
    //lastMove.setPlayer(null);
        lastMove.getCell().setCellState(CellState.EMPTY);
        lastMove.getCell().setSymbol(null);//since its set to null, in the rowWinningStrategy/columnWinningStrategy
    // class while implementing handleUndo fn- i have taken player symbol not the cell symbol
    //lastMove.setCell(null); as we cant get row value in rowwinningstrategy class as it is set to null

        //whenever we are doing negative- we need to take the modulo so for (a-b)%n =(a-b+n)%n
        nextPlayerIndex--;
        nextPlayerIndex=(nextPlayerIndex+players.size())%players.size();//to update the next player index

        //in the winning strategy we had updated the hashmap,so we also need to update the hashmap since the move is reversed
        for(WinningStrategy strategy: winningStrategies){
            strategy.handleUndo(board,lastMove);
        }

/*  After implenting above methods- we can successfull do undo- but here comes a issue
    while we run the game and suppose a player wins- it displays winner and asks for undo
    if the player gives Yes, it undo the step and still shows the same player winner-it's a bug */

//if a player has won , wither dont allow undo / reverse the winner and game state
        if(getWinner()!=null){
            setGameState(GameState.IN_PROGRESS);//set the game state to in progress as we have undone the move
            setWinner(null);
            return;
        }
//        //so we need to update the game state and winner as null
//        setGameState(GameState.IN_PROGRESS);//set the game state to in progress as we have undone the move
//        setWinner(null);

    }

    //----------Builder design pattern----------------------

    //no need of mentioning all attributes in builder-only mention the one we need from the client
    public static class Builder{
    //since validation is required, we can use Builder Pattern
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

    //Create only Setters
    //setters will also return the builder object-special thing in the setters to bring the chaining
        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }
        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }
        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }
    //Setters and Builders are ready - create the object of the builder
    //so now create a method in the game class called getBuilder method as static

    //Now Builder needs to have a build method - this will create a game object and return a game object
        public Game build(){
            validate();//just call the method
    //if all validation written here-it will be congested and also will brake SRP - so have a separate method

            return new Game(this);
            //here make a game constructor for - this-keyword in the above class

        }

        public void validate(){
        /*validate
        1) check the players count== dimension-1 */
            if(players.size()!=dimension-1){
                throw new RuntimeException("Invalid players count");
            }

        //2) you can have only 1 bot in game- check this by checking type count of eac player
            int botCount=0;
            for(Player player:players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount>1){
                throw new RuntimeException("More than one bot is not allowed");
            }

        //3) every player should have a separate symbol
            HashSet<Character> symbolSet=new HashSet<>();
            for(Player player:players){
                if(symbolSet.contains(player.getSymbol().getSym())){
                    throw new RuntimeException("Multiple Players have the same Symbol");
                }
                symbolSet.add(player.getSymbol().getSym());
            }

        }

    }

}


