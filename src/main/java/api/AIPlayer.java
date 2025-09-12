package api;

import game.*;

public class AIPlayer {
    public Move suggestMove(Board board, Player computer) {
        Player aiPlayer = new Player("O");
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            Move suggestedMove;
            if(isStarting(board1, 3)){
                suggestedMove = getBasicMove(computer, board1);
            }else{
                suggestedMove = getSmartMove(board);
            }


        }
        throw new IllegalStateException();
    }

    public Move getSmartMove(Player player, TicTacToeBoard board){

        //Attaching - if AI win
        TicTacToeBoard boardcopy = board.copy();
        for(int rowIndex=0; rowIndex<3; rowIndex++){
            for(int colIndex=0; colIndex<3; colIndex++){
                if()
            }
        }
        //Defensive

        //next move

        //return new Move()
    }

    public Move getBasicMove(Player computer, TicTacToeBoard board1){
        Move suggestedMove;
        for (int colIndex = 0; colIndex < 3; colIndex++) {
            for (int rowIndex = 1; rowIndex < 3; rowIndex++) {
                if(board1.getCell(rowIndex, colIndex) == null){
                    Cell cell = new Cell(rowIndex, colIndex);
                    suggestedMove =  new Move(computer, cell);
                }
            }
        }
        return null;
    }

    public boolean isStarting(TicTacToeBoard board, int threshold){
        int count = 0;

        for(int rowIndex=0; rowIndex<3; rowIndex++){
            for(int colIndex=0; colIndex<3; colIndex++){
                if(board.getCell(rowIndex, colIndex) != null) count++;
            }
        }

        return count < threshold;
    }
}
