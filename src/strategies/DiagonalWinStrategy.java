package strategies;


import model.Board;
import model.Cell;
import model.Move;

import java.util.HashMap;

public class DiagonalWinStrategy implements WinningStrategy{


    HashMap<Character,Integer> primaryDiagonalCount=new HashMap<>() ;
    HashMap<Character,Integer> secondaryDiagonalCount=new HashMap<>() ;

    @Override
    public boolean checkWinner(Board board, Move move) {


        //here i need to know the count of particular symbol in a row or col
        //at first we want to update the particular row and symbol count so get it from the hashmap

        //Cell cell=null;

        int row=move.getCell().getRow();//here we get the row of the move made by the player in the board object
        int col=move.getCell().getCol();
        Character sym = move.getCell().getSymbol().getSym();//here we get the symbol of the move made by the player in the board object
        int boardSize=board.getSize();

        if(row==col) {//if the row and column are equal, then it is a diagonal
            if(!primaryDiagonalCount.containsKey(sym)){
                primaryDiagonalCount.put(sym,0);
            }
            primaryDiagonalCount.put(sym,primaryDiagonalCount.get(sym)+1);

            if(primaryDiagonalCount.get(sym)==boardSize){
                return true;
            }
        }

        if(row+col==boardSize-1){
            if(!secondaryDiagonalCount.containsKey(sym)){
                secondaryDiagonalCount.put(sym,0);
            }
            secondaryDiagonalCount.put(sym,secondaryDiagonalCount.get(sym)+1);

            if (secondaryDiagonalCount.get(sym) == boardSize) {
                return true;
            }

        }

        return false;

    }

    @Override
    public void handleUndo(Board board, Move move) {
//        //if the move is undone, then we need to decrement the count of the symbol in the row
//        int row=move.getCell().getRow();//get
//        Character sym=move.getPlayer().getSymbol().getSym();
//
//        //go to hashmap, for the row hashmap, get the symbol and decrement the count
//        countMap.get(row).put(sym,countMap.get(row).get(sym)-1);
    }

}
