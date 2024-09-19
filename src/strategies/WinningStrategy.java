package strategies;

import model.Board;
import model.Move;

public interface WinningStrategy {

    public boolean checkWinner(Board board, Move move);
    public void handleUndo(Board board, Move move);
}
