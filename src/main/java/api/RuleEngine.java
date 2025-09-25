package api;

import game.Board;
import game.GameState;
import game.TicTacToeBoard;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    private GameState getGameState(BiFunction<Integer, Integer, String> next){
        GameState gameState = new GameState(false, "-");
        boolean isPossibleStreak;
        for (int i = 0; i < 3; i++) {
            isPossibleStreak = true;
            for (int j = 0; j < 3; j++) {
                if (next.apply(i, j) != null || !next.apply(i, 0).equals(next.apply(i, j))) {
                    isPossibleStreak = false;
                    break;
                }
            }
            if (isPossibleStreak) gameState =  new GameState(true, next.apply(i, 0));
        }
        return gameState;
    }

    // Now I am able to see the things
    private GameState getDiagonalGameState(Function<Integer, String> next){
        GameState gameState = new GameState(false, "-");
        boolean strek = true;
            for (int i = 0; i < 3; i++) {
                if (!next.apply(0).equals(next.apply(i))) {
                    strek = false;
                    break;
                }
            }
            if(strek) gameState = new GameState(true, next.apply(0));
        return gameState;
    }

    public GameState getState(Board board) {

        String firstPlayer = "-";
        String winner = "-";
        if (board instanceof TicTacToeBoard board1) {

            BiFunction<Integer, Integer, String> getNextRow = (i, j) -> board1.getSymbol(i, j);
            BiFunction<Integer, Integer, String> getNextCol = (i, j) -> board1.getSymbol(j, i);

            //Row Win
            GameState isRowWin = getGameState(getNextRow);
            if(isRowWin.isOver()) return isRowWin;

            //Col Win
            GameState isColWin = getGameState(getNextCol);
            if(isColWin.isOver()) return isColWin;


            Function<Integer, String> getStartDiagonal = (i) -> board1.getSymbol(i, i);
            Function<Integer, String> getEndDiagonal = (i) -> board1.getSymbol(i,3-i-1);

            //if startDiagonal
            GameState firstDigonalState = getDiagonalGameState(getStartDiagonal);
            if(firstDigonalState.isOver()) return firstDigonalState;

            //if endDiagonal
            GameState lastDiagonalState = getDiagonalGameState(getEndDiagonal);
            if(lastDiagonalState.isOver()) return lastDiagonalState;

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
