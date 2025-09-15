package api;

import game.*;
import org.apache.tomcat.util.digester.Rule;

import java.util.Objects;

public class AIPlayer {
    public Move suggestMove(Board board, Player computer) {
        Player aiPlayer = new Player("O");
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            Move suggestedMove;
            if(isStarting(board1, 3)){
                suggestedMove = getBasicMove(aiPlayer, board1);
            }else{
                suggestedMove = getSmartMove(aiPlayer, board1);
            }
        }
        throw new IllegalStateException();
    }

    public Move getSmartMove(Player player, TicTacToeBoard board){

        RuleEngine ruleEngine = new RuleEngine();
        //Attaching - if AI win

        for(int rowIndex=0; rowIndex<3; rowIndex++){
            for(int colIndex=0; colIndex<3; colIndex++){
                if(board.getCell(rowIndex, colIndex) == null) {
                    TicTacToeBoard boardcopy = board.copy();
                    Move move = new Move(player, new Cell(rowIndex, colIndex));
                    boardcopy.move(move);

                    if (ruleEngine.getState(boardcopy).isOver() && Objects.equals(ruleEngine.getState(boardcopy).getWinner(), player.symbol())) {
                        return move;
                    }
                }
            }
        }
        //Defensive

        for(int rowIndex=0; rowIndex<3; rowIndex++){
            for(int colIndex=0; colIndex<3; colIndex++) {
                if (board.getCell(rowIndex, colIndex) == null) {
                    TicTacToeBoard boardCopy = board.copy();
                    Move move = new Move(player, new Cell(rowIndex, colIndex));
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver() && Objects.equals(ruleEngine.getState(boardCopy).getWinner(), "0")){
                        return move;
                    }
                }
            }
        }
        //next move
        return getBasicMove(player, board);

    }

    public Move getBasicMove(Player computer, TicTacToeBoard board1){
        Move suggestedMove;
        for (int colIndex = 0; colIndex < 3; colIndex++) {
            for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
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
