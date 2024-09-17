package model;

import java.util.List;

public class Game {

    Board board;
    List<Player> players;
    Player winner;
    int nextPlayerIndex;
    List<Move> moves;
    GameState gameState;
    //winning Strategies


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

}


