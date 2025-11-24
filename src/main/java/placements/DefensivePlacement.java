package placements;

import api.RuleEngine;
import game.*;
import utils.Util;

import java.util.Objects;
import java.util.Optional;

public class DefensivePlacement implements Placements {

    public static DefensivePlacement defensivePlacement;

    private DefensivePlacement(){

    }

    public static synchronized DefensivePlacement get(){
        return (DefensivePlacement) Util.getIfNull(defensivePlacement, DefensivePlacement::new);
    }
    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell best = null;
        best = defence(player, board);
        if (best != null) return Optional.of(best);
        return Optional.empty();
    }

    @Override
    public Placements next() {
        return ForkPlacement.get();
    }

    private static Cell defence(Player player, TicTacToeBoard board) {
        for(int rowIndex=0; rowIndex<3; rowIndex++){
            for(int colIndex=0; colIndex<3; colIndex++) {
                if (board.getSymbol(rowIndex, colIndex) == null) {
                    TicTacToeBoard boardCopy = board.copy();
                    Cell cell = new Cell(rowIndex, colIndex);
                    Move move = new Move(player.flip(), cell);
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()){
                        return new Move(player, cell).getCell();
                    }
                }
            }
        }
        return null;
    }
}
