package model;

import strategies.BotPlayingStrategy;
import strategies.BotPlayingStrategyFactory;

public class BotPlayer extends Player {

    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public BotPlayer(int id, String name , PlayerType playerType, Symbol symbol, BotDifficultyLevel botDifficultyLevel){
        super(id , name , symbol,playerType);
        this.botPlayingStrategy= BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public Move makeMove(Board board){
        //now i need to have the object of strategy to call this particular method-so create a StrategyFactory for that
        return botPlayingStrategy.makeMove(board);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
}
