package placements;

import game.Cell;
import game.Player;
import board.TicTacToeBoard;
import utils.Util;

import java.util.Optional;

public class CornerPlacement implements Placements  {

    public static CornerPlacement cornerPlacement;
    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        final int[][] corners = new int[][]{{0, 0}, {0, 2}, {2, 0}, {2, 2} };

        for(int i=0; i<4; i++){
            if(board.getSymbol(corners[i][0], corners[i][1]) == null){
                return Optional.of(new Cell(corners[i][0], corners[i][1]));
            }
        }
        return Optional.empty();
    }

    @Override
    public Placements next() {
        return null;
    }

    public static synchronized CornerPlacement get(){
        return (CornerPlacement) Util.getIfNull(cornerPlacement, CornerPlacement::new);
    }
}
