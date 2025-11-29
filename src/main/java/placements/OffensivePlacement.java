package placements;

import board.TicTacToeBoard;
import game.*;
import utils.Util;

import java.util.Optional;

public class OffensivePlacement implements Placements {

    public static OffensivePlacement offensivePlacement;

    private OffensivePlacement(){

    }

    public static synchronized OffensivePlacement get(){
        return (OffensivePlacement) Util.getIfNull(offensivePlacement, OffensivePlacement::new);
    }
    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell best = null;
        best = offence(player, board);
        if (best != null) return Optional.of(best);
        return Optional.empty();
    }

    @Override
    public Placements next() {
        return DefensivePlacement.get();
    }

    private static Cell offence(Player player, TicTacToeBoard board) {
        //Victorious Move - if AI win
        for(int rowIndex=0; rowIndex<3; rowIndex++){
            for(int colIndex=0; colIndex<3; colIndex++){
                if(board.getSymbol(rowIndex, colIndex) == null) {
                    Move move = new Move(player, new Cell(rowIndex, colIndex));
                    TicTacToeBoard boardcopy = board.move(move);

                    if (ruleEngine.getState(boardcopy).isOver()) {
                        return move.getCell();
                    }
                }
            }
        }
        return null;
    }
}
