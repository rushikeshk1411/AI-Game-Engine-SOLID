package api;

import game.*;
import org.apache.tomcat.util.digester.Rule;

import java.util.Objects;

public class AIPlayer {
    public Move suggestMove(Board board, Player computer) {
        //Player aiPlayer = new Player("O");
        if (board instanceof TicTacToeBoard board1) {
            Move suggestedMove;
            if(isStarting(board1, 3)){
                return suggestedMove = getBasicMove(computer, board1);
            }else{
                return suggestedMove = getSmartMove(computer, board1);
            }

        }
        throw new IllegalStateException();
    }

    public Move getSmartMove(Player player, TicTacToeBoard board){

        RuleEngine ruleEngine = new RuleEngine();
        //Victorious Move - if AI win

        for(int rowIndex=0; rowIndex<3; rowIndex++){
            for(int colIndex=0; colIndex<3; colIndex++){
                if(board.getSymbol(rowIndex, colIndex) == null) {
                    TicTacToeBoard boardcopy = board.copy();
                    Move move = new Move(player, new Cell(rowIndex, colIndex));
                    boardcopy.move(move);

                    if (ruleEngine.getState(boardcopy).isOver()) {
                        return move;
                    }
                }
            }
        }
        //Defensive
        for(int rowIndex=0; rowIndex<3; rowIndex++){
            for(int colIndex=0; colIndex<3; colIndex++) {
                if (board.getSymbol(rowIndex, colIndex) == null) {
                    TicTacToeBoard boardCopy = board.copy();
                    Cell cell = new Cell(rowIndex, colIndex);
                    Move move = new Move(player.flip(), cell);
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()){
                        return new Move(player, cell);
                    }
                }
            }
        }
        //next move
        return getBasicMove(player, board);

    }

    public Move getBasicMove(Player computer, TicTacToeBoard board1){
        Move suggestedMove;
        for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
            for (int colIndex = 0; colIndex < 3; colIndex++) {
                if(board1.getCell(rowIndex, colIndex) == null){
                    Cell cell = new Cell(rowIndex, colIndex);
                    suggestedMove =  new Move(computer, cell);
                    return suggestedMove;
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
