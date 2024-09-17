package model;
public class BotPlayer extends Player {

    private BotDifficultyLevel botDifficultyLevel;

    public BotPlayer(int id, String name , PlayerType playerType, Symbol symbol, BotDifficultyLevel botDifficultyLevel){
        super(id , name , symbol,playerType);
        this.botDifficultyLevel = botDifficultyLevel;
    }
    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
}
