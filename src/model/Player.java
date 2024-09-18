package model;

import java.awt.*;
import java.util.Scanner;

public abstract class Player {

    private int id;
    private String name;
    private Symbol symbol;
    private PlayerType playerType;

    //one common constructor for all  attributes
    private Scanner sc=new Scanner(System.in);

    public Player(int id, String name, Symbol symbol, PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    //hey player class we are going to ask you to make the move, please be ready for that
    //player says once i will be making the move & will be returning the obj of the move
    public Move makeMove(){
        //so to take the input for the move , create a scanner in this class
        System.out.println("Please enter the Row where you want to place the Symbol");
        int r= sc.nextInt();
        System.out.println("Please enter the Row where you want to place the Symbol");
        int c= sc.nextInt();
    /* r & c might not be the right validated point over here-so should we validate here
    but if we validate here- i might need more data like what was the turn variable, how to get back to the same player turn, how to keep asking abt same move
    or other possibility is player has made a move the game class can decide -if it's valid or not */

        return new Move(new Cell(r,c), this);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
}
