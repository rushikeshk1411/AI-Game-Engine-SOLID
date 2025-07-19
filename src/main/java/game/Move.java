package game;

public class Move {
    Player player;
    Cell cell;

    public Move(Player player, Cell cell){
        this.player = player;
        this.cell = cell;
    }
    public Cell getCell(){
        return cell;
    }


    public Player getPlayer(){
        return player;
    }


}


