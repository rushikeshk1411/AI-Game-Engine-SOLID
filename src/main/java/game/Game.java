package game;

import board.Board;

public class Game {
    public GameConfig gameConfig;

    private Board board;
    private Player winner;
    private int maxTimePerPlayer;
    private int maxTimePerMove;
    private int lasttimeStampInMili;

    public Game move(Move move, int timeStampInMili){
        int timeTakenSinceLastMove = timeStampInMili - lasttimeStampInMili;
        move.getPlayer().setTimeUsedForPlayerInMillis(timeTakenSinceLastMove);

        if(gameConfig.isTimed){
            moveForTimedGame(move, timeTakenSinceLastMove);
        }else{
            board.move(move);
        }

        return new Game();
    }

    private void moveForTimedGame(Move move, int timeTakenSinceLastMove) {
        if(gameConfig.timePerMove != null){
            if(moveMadeInTime(timeTakenSinceLastMove)){
                board.move(move);
            }else{
                winner = move.player.flip();
            }
        }else{
            if(moveMadeInTime(move.getPlayer())){
                board.move(move);
            }else{
                winner = move.player.flip();
            }
        }
    }

    private boolean moveMadeInTime(int time){
        return  maxTimePerMove >= time;
    }
    private boolean moveMadeInTime(Player player){
        return maxTimePerPlayer >= player.getUsedTimeInMillis();
    }

}
