package api;

import board.Board;
import game.*;
import placements.DefensivePlacement;
import placements.Placements;

import java.util.Optional;

abstract class State{
    TicTacToeBoard board;
    Player player;

    public abstract void placeCell();
    public abstract State next();
}

class opening extends State{

    @Override
    public void placeCell() {

    }

    @Override
    public State next() {
        return null;
    }
}

class midGame extends State{

    @Override
    public void placeCell() {

    }

    @Override
    public State next() {
        return null;
    }
}

class TimePressure extends State{

    @Override
    public void placeCell() {

    }

    @Override
    public State next() {
        return null;
    }
}

class NoPressure extends State{

    @Override
    public void placeCell() {
        
    }

    @Override
    public State next() {
        return null;
    }
}

public class AIPlayer {
    public Move suggestMove(Board board, Player player) {
        //Player aiPlayer = new Player("O");

        if (board instanceof TicTacToeBoard board1) {
            int threshold = 3;
            Cell suggestedCell = null;
            if(countMoves(board1) < threshold){
                suggestedCell = getBasicMove(board1);
            }else if(countMoves(board1) < threshold + 1) {
                suggestedCell = getSmartMove(player, board1);
            }else if(player.getUsedTimeInMillis() > 1000){
                suggestedCell = getBasicMove(board1);
            }else{
                suggestedCell = getOptimalMove(player, board1);
            }
            return new Move(player, suggestedCell);
        }
        throw new IllegalStateException();
    }

    private Cell getOptimalMove(Player player, TicTacToeBoard board) {

        // If corners are empty take this
        Placements placement = DefensivePlacement.get();

        while(placement.next() != null){
            Optional<Cell> cell = placement.place(board, player);
            if(cell.isPresent()){
                return cell.get();
            }
            placement = placement.next();
        }

        return getBasicMove(board);
    }

    public Cell getSmartMove(Player player, TicTacToeBoard board){

        RuleEngine ruleEngine = new RuleEngine();

        Cell best = null;
        best = offence(player, board, ruleEngine);
        if (best != null) return best;
        //Defensive
        best = defence(player, board, ruleEngine);
        if (best != null) return best;
        //next move
        return getBasicMove(board);

    }

    private static Cell defence(Player player, TicTacToeBoard board, RuleEngine ruleEngine) {
        for(int rowIndex=0; rowIndex<3; rowIndex++){
            for(int colIndex=0; colIndex<3; colIndex++) {
                if (board.getSymbol(rowIndex, colIndex) == null) {
                    Cell cell = new Cell(rowIndex, colIndex);
                    Move move = new Move(player.flip(), cell);
                    TicTacToeBoard boardCopy = board.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()){
                        return new Move(player, cell).getCell();
                    }
                }
            }
        }
        return null;
    }

    private static Cell offence(Player player, TicTacToeBoard board, RuleEngine ruleEngine) {
        //Victorious Move - if AI win
        for(int rowIndex=0; rowIndex<3; rowIndex++){
            for(int colIndex=0; colIndex<3; colIndex++){
                if(board.getSymbol(rowIndex, colIndex) == null) {
                    TicTacToeBoard boardcopy = board.copy();
                    Move move = new Move(player, new Cell(rowIndex, colIndex));
                    TicTacToeBoard boardCopy = board.move(move);

                    if (ruleEngine.getState(boardcopy).isOver()) {
                        return move.getCell();
                    }
                }
            }
        }
        return null;
    }

    public Cell getBasicMove(TicTacToeBoard board1){
        Move suggestedMove;
        for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
            for (int colIndex = 0; colIndex < 3; colIndex++) {
                if(board1.getCell(rowIndex, colIndex) == null){
                    Cell cell = new Cell(rowIndex, colIndex);
                    return cell;
                }
            }
        }
        return null;
    }

    public int countMoves(TicTacToeBoard board){
        int count = 0;

        for(int rowIndex=0; rowIndex<3; rowIndex++){
            for(int colIndex=0; colIndex<3; colIndex++){
                if(board.getCell(rowIndex, colIndex) != null) count++;
            }
        }

        return count;
    }
}
