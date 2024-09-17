package model;

import strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

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

    public static Builder getBuilder(){
        return new Builder();
    }

    //Builder design pattern

    //no need of mentioning all attributes in builder-only mention the one we need from the client
    public static class Builder{
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

            return new Game(this);
            //here make a game constructor for this-keyword

            /* validate
            1) check the players count== dimension-1
            2) you can have only 1 bot in game
            3) every player should have a separate symbol
            since validation is required we can use Builder Pattern */

        }

    }

}


