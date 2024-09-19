package strategies;


import model.Board;
import model.Move;

import java.util.HashMap;


//expalnation was for row winning strategy, copied same code and changed for column
public class ColWinningStrategy implements WinningStrategy {

    HashMap<Integer, HashMap<Character,Integer>> countMap=new HashMap<>() ;
    //we have taken hashmap of hashmap, outer hashmap is for each and every row,internal hashmap will be containing corresponding symbol and its count
    //"0"-> {"X"(symbol),Count}
    @Override
    public boolean checkWinner(Board board, Move move) {
        //here i need to know the count of particular symbol in a row or col
        //at first we want to update the particular row and symbol count so get it from the hashmap
        int col=move.getCell().getCol();
        Character sym=move.getCell().getSymbol().getSym();

        //if this count map have this particular row or not
        if(!countMap.containsKey(col)){//if row not available, then create a row and hashmap
            countMap.put(col,new HashMap<>());
        }

        HashMap<Character,Integer> countCol=countMap.get(col);
        //this is to check if the symbol is available in HashMap or not
        if(!countCol.containsKey(sym)){//if symbol not available, then create a symbol and count
            countCol.put(sym,0);
        }
        //if symbol is available, then increment the count
        countCol.put(sym,countCol.get(sym)+1);

        //check if winner has arrived
        if(countCol.get(sym)==board.getSize()){//if the count of the symbol is equal to the board size, then return true
            return true;
        }
        return false;
    }
    @Override
    public void handleUndo(Board board, Move move) {
        //if the move is undone, then we need to decrement the count of the symbol in the row
        int col=move.getCell().getCol();//get
        Character sym=move.getPlayer().getSymbol().getSym();

        //go to hashmap, for the row hashmap, get the symbol and decrement the count
        countMap.get(col).put(sym,countMap.get(col).get(sym)-1);
    }

}
