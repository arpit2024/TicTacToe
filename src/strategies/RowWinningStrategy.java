package strategies;

import model.Board;
import model.Move;
import strategies.WinningStrategy;

import java.util.HashMap;


    public class RowWinningStrategy implements WinningStrategy {

        HashMap<Integer, HashMap<Character,Integer>> countMap=new HashMap<>() ;
    //we have taken hashmap of hashmap, outer hashmap is for each and every row,internal hashmap will be containing corresponding symbol and its count
    //"0"-> {"X"(symbol),Count}
        @Override
        public boolean checkWinner(Board board, Move move) {
        //here i need to know the count of particular symbol in a row or col
        //at first we want to update the particular row and symbol count so get it from the hashmap
            int row=move.getCell().getRow();
            Character sym=move.getCell().getSymbol().getSym();

        //if this count map have this particular row or not
             if(!countMap.containsKey(row)){//if row not available, then create a row and hashmap
                     countMap.put(row,new HashMap<>());
             }

             HashMap<Character,Integer> countRow=countMap.get(row);
             //this is to check if the symbol is available in HashMap or not
             if(!countRow.containsKey(sym)){//if symbol not available, then create a symbol and count
                 countRow.put(sym,0);
             }
             //if symbol is available, then increment the count
             countRow.put(sym,countRow.get(sym)+1);

             //check if winner has arrived
             if(countRow.get(sym)==board.getSize()){//if the count of the symbol is equal to the board size, then return true
                 return true;
             }
                return false;
        }

}
