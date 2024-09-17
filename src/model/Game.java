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

    //private Game Constructor- will be expecting a builder object
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

    //----------Builder design pattern----------------------

    //no need of mentioning all attributes in builder-only mention the one we need from the client
    public static class Builder{
    //since validation is required we can use Builder Pattern
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
//Setters and Builders are ready- create the object of the builder
//so now create a method in the game class called getBuilder method as static

//Now Builder needs to have a build method- this will create a game object and return a game object
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


