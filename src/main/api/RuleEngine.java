package api;

import game.Board;
import game.GameState;
import game.TicTacToeBoard;

public class RuleEngine {
    public GameState getState(Board board) {

        String firstPlayer = "-";
        String winner = "-";
        if (board instanceof TicTacToeBoard board1) {
            //if Row same
            boolean rowComplete = true;
            for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
                firstPlayer = board1.getCell(rowIndex, 0);
                rowComplete = firstPlayer != null;
                if(firstPlayer == null) continue;
                for (int colIndex = 1; colIndex < 3; colIndex++) {
                    if (!firstPlayer.equals(board1.getCell(rowIndex, colIndex))) {
                        rowComplete = false;
                        break;
                    }
                }
                if (rowComplete) return new GameState(true, firstPlayer);

            }

            //if Col same
            boolean colComplete = true;
            for (int colIndex = 0; colIndex < 3; colIndex++) {
                firstPlayer = board1.getCell(0, colIndex);
                colComplete = firstPlayer != null;
                if(firstPlayer == null) continue;
                for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
                    if (!firstPlayer.equals(board1.getCell(rowIndex, colIndex))) {
                        colComplete = false;
                        break;
                    }
                }
                if (colComplete) return new GameState(true, firstPlayer);

            }

            //if startDiagonal
            boolean isStartDiagonalComplete = true;
            firstPlayer = board1.getCell(0, 0);
            isStartDiagonalComplete = firstPlayer != null;
            if(firstPlayer != null) {
                for (int index = 0; index < 3; index++) {
                    if (!firstPlayer.equals(board1.getCell(index, index++))) {
                        isStartDiagonalComplete = false;
                        break;
                    }
                }
            }

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
