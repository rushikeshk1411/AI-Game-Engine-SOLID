package game;

public class Player {
    private User user;
    private int usedTimeForPlayerInMillis;
    public String playerSymbol;


    public Player(String symbol){
        this.playerSymbol = symbol;
        user = new User(Double.toString(Math.random()));
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

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }
}
