package game;

import board.CellBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TicTacToeBoard implements CellBoard {
    String[][] cells = new String[3][3];

    History history = new History();

    public String getCell(int rowIndex, int colIndex) {
        return cells[rowIndex][colIndex];
    }

    public void setCell(String symbol, Cell cell) {
        if(cells[cell.rowIndex][cell.colIndex] == null)
            cells[cell.rowIndex][cell.colIndex] = symbol;
        else throw new IllegalStateException();
    }
    public String getSymbol(int rowIndex, int colIndex){
        return cells[rowIndex][colIndex];
    }

    public static RuleSet getRules(){
        RuleSet ruleSet = new RuleSet();
        ruleSet.add(new Rule(board -> outerTraverse(board::getSymbol)));
        ruleSet.add(new Rule(board -> outerTraverse((j, i) -> board.getSymbol(i, j))));
        ruleSet.add(new Rule(board -> traverse(i -> board.getSymbol(i, i))));
        ruleSet.add(new Rule(board -> traverse(i -> board.getSymbol(i, 3-i-1))));
        ruleSet.add(new Rule(board -> {
            int countOfFilledCells = 0;

            for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
                for (int colIndex = 0; colIndex < 3; colIndex++) {
                    if (board.getSymbol(rowIndex, colIndex) != null) {
                        countOfFilledCells++;
                    }
                }
            }

            if (countOfFilledCells == 9) return new GameState(true, "-");
            else return new GameState(false, "-");
        }));

        return ruleSet;
    }

    private static GameState outerTraverse(BiFunction<Integer, Integer, String> next){
        GameState gameState = new GameState(false, "-");
        boolean isPossibleStreak;
        for (int i = 0; i < 3; i++) {
            isPossibleStreak = true;
            final int ii = i;
            Function<Integer, String> traversal = j -> next.apply(ii, j);
            gameState = traverse(traversal);
            if(gameState.isOver()){
                gameState = gameState;
                break;
            }
        }
        return gameState;
    }

    // Now I am able to see the things
    private static GameState traverse(Function<Integer, String> next){
        GameState gameState = new GameState(false, "-");
        boolean isPossibleStreak = true;
        for (int j = 0; j < 3; j++) {
            if ((next.apply(j) == null) || (next.apply(0) != null && !next.apply(0).equals(next.apply(j)))) {
                isPossibleStreak = false;
                break;
            }
        }
        if(isPossibleStreak) gameState = new GameState(true, next.apply(0));
        return gameState;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int rowIndex=0; rowIndex<3; rowIndex++){
            for(int colIndex=0; colIndex<3; colIndex++){
                if(cells[rowIndex][colIndex] != null)
                    sb.append(cells[rowIndex][colIndex] + " ");
                else
                    sb.append("- ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public TicTacToeBoard move(Move move) {
        TicTacToeBoard board = copy();
        history.add(getRepresentation(board));
        board.setCell(move.getPlayer().playerSymbol, move.getCell());
        return board;
    }

    @Override
    public TicTacToeBoard copy(){
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();

        for(int rowIndex=0; rowIndex<3; rowIndex++){
            System.arraycopy(cells[rowIndex], 0,ticTacToeBoard.cells[rowIndex], 0, 3 );
        }

        ticTacToeBoard.history = this.history;
        return ticTacToeBoard;

    }

    private Representation getRepresentation(TicTacToeBoard board){
        return new Representation(board);
    }
}

class History{
    public List<Representation> boards = new ArrayList<>();

    public void add(Representation board) {
        boards.add(boards.size()+ 1, board);
    }

    public Representation getBoardAtMove(int moveIndex){
        for(int i = 0; i< boards.size()- moveIndex + 1; i++){
            boards.remove(boards.size()-1);
        }
        return boards.get(moveIndex);
    }

    public Representation undo(){
        if(boards.isEmpty()){
            throw new IllegalStateException();
        }
        return boards.get(boards.size()-1);
    }
}

class Representation {
    String reprentation;

    public Representation(TicTacToeBoard board){
        reprentation = board.toString();
    }


}
