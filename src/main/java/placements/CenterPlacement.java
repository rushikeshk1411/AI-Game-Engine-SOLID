package placements;

import game.Cell;
import game.Player;
import board.TicTacToeBoard;
import utils.Util;

import java.util.Optional;

public class CenterPlacement implements Placements{

    public static CenterPlacement centerPlacement;
    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        if(board.getSymbol(1, 1) == null) return Optional.of(new Cell(1, 1));
        return Optional.empty();
    }

    @Override
    public Placements next() {
        return CornerPlacement.get();
    }

    public static synchronized CenterPlacement get(){
        return (CenterPlacement) Util.getIfNull(centerPlacement, CenterPlacement::new);
    }
}
