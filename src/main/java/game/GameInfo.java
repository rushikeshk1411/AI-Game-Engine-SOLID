package game;

public class GameInfo {
    boolean isOver;
    String winner;
    boolean hasFork;
    Player player;
    Cell forkCell;
    public GameInfo(boolean isOver, String winner, boolean hasFork, Player player, Cell forkCell ){
        this.isOver = isOver;
        this.winner = winner;
        this.hasFork = hasFork;
        this.player = player;
        this.forkCell = forkCell;
    }

    public boolean isOver() {
        return isOver;
    }

    public String getWinner(){
        return winner;
    }

    public boolean hasFork(){
        return hasFork;
    }

    public Player getForkPlayer(){
        return player;
    }

    public Cell getForkCell(){
        return forkCell;
    }
}
