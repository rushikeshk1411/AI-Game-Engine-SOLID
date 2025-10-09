package game;

public class Player {
    private User id;
    private int usedTimeForPlayerInMillis;
    String playerSymbol;


    public Player(String symbol){
        this.playerSymbol = symbol;
    }

    public String symbol(){
        return playerSymbol;
    }

    public Player flip(){
        String flipPlayerSymbol = playerSymbol.equals("X") ? "0" : "X";
        return new Player(flipPlayerSymbol);
    }

    public int getUsedTimeInMillis() {
        return usedTimeForPlayerInMillis;
    }

    public void setTimeUsedForPlayerInMillis(int usedTimeForMove){
        this.usedTimeForPlayerInMillis += usedTimeForMove;
    }
}
