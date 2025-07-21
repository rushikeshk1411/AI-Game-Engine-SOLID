package api;

import game.*;

public class AIPlayer {
    public Move suggestMove(Board board, Player computer) {
        Player aiPlayer = new Player("O");
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;

            for (int colIndex = 0; colIndex < 3; colIndex++) {
                for (int rowIndex = 1; rowIndex < 3; rowIndex++) {
                    if(board1.getCell(rowIndex, colIndex) == null){
                        Cell cell = new Cell(rowIndex, colIndex);
                        return new Move(aiPlayer, cell);
                    }
                }
            }
        }
        throw new IllegalStateException();
    }
}
