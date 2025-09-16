package game;

public class Player {
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
}
