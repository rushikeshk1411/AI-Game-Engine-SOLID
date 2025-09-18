package api;

import game.Board;
import game.GameState;
import game.TicTacToeBoard;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    private GameState getGameState(BiFunction<Integer, Integer, String> next){
        boolean isPossibleStreak;
        for (int i = 0; i < 3; i++) {
            isPossibleStreak = true;
            for (int j = 0; j < 3; j++) {
                if (next.apply(i, j) != null || !next.apply(i, 0).equals(next.apply(i, j))) {
                    isPossibleStreak = false;
                    break;
                }
            }
            if (isPossibleStreak) return new GameState(true, next.apply(i, 0));

        }
        return null;
    }

    private GameState getDiagonalGameState(BiFunction<Integer, Integer, String> next){
        boolean strek = true;
            for (int index = 0; index < 3; index++) {
                if (!next.apply(0, 0).equals(next.apply(index, index))) {
                    strek = false;
                    break;
                }
            }

    }
    public GameState getState(Board board) {

        String firstPlayer = "-";
        String winner = "-";
        if (board instanceof TicTacToeBoard board1) {

            BiFunction<Integer, Integer, String> getNextRow = (i, j) -> board1.getSymbol(i, j);
            BiFunction<Integer, Integer, String> getNextCol = (i, j) -> board1.getSymbol(j, i);

            //Row Win
            GameState isRowWin = getGameState(getNextRow);
            if(isRowWin != null) return isRowWin;

            //Col Win
            GameState isColWin = getGameState(getNextCol);
            if(isColWin != null) return isColWin;


            BiFunction<Integer, Integer, String> getStartDiagonal = (i, j) -> board1.getSymbol(i, i);
            BiFunction<Integer, Integer, String> getEndDiagonal = (i, j) -> board1.getSymbol(i, 3-i-1);

            //if startDiagonal

            if (isStartDiagonalComplete) return new GameState(true, firstPlayer);

            //if endDiagonal
            boolean isEndDiagonal = true;
            firstPlayer = board1.getCell(0, 2);
            isEndDiagonal = firstPlayer != null;
            if(firstPlayer != null) {
                for (int index = 1; index < 3; index++) {
                    if (!firstPlayer.equals(board1.getCell(index, 3 - index - 1))) {
                        isEndDiagonal = false;
                        break;
                    }
                }
            }
            if (isEndDiagonal) return new GameState(isEndDiagonal, firstPlayer);

            int countOfFilledCells = 0;

            for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
                for (int colIndex = 0; colIndex < 3; colIndex++) {
                    if (board1.getCell(rowIndex, colIndex) != null) {
                        countOfFilledCells++;
                    }
                }
            }

            if (countOfFilledCells == 9) return new GameState(true, "-");

        } else {
            return new GameState(false, "Error");
        }
        return new GameState(false, "-");
    }
}
