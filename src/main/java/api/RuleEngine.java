package api;

import game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

    HashMap<String, ArrayList<Rule<TicTacToeBoard>>>  ruleMap = new HashMap<>();
    public RuleEngine(){
        ruleMap.put(TicTacToeBoard.class.getName(), new ArrayList<>());
        ArrayList<Rule<TicTacToeBoard>> ruleList = ruleMap.get(TicTacToeBoard.class.getName());
        ruleList.add(new Rule<>(board -> outerTraverse(board::getSymbol)));
        ruleList.add(new Rule<>(board -> outerTraverse((j, i) -> board.getSymbol(i, j))));
        ruleList.add(new Rule<>(board -> traverse(i -> board.getSymbol(i, i))));
        ruleList.add(new Rule<>(board -> traverse(i -> board.getSymbol(i, 3-i-1))));
        ruleList.add(new Rule<>(board -> {
            int countOfFilledCells = 0;

            for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
                for (int colIndex = 0; colIndex < 3; colIndex++) {
                    if (board.getCell(rowIndex, colIndex) != null) {
                        countOfFilledCells++;
                    }
                }
            }

            if (countOfFilledCells == 9) return new GameState(true, "-");
            else return new GameState(false, "-");
        }));
    }
    private GameState outerTraverse(BiFunction<Integer, Integer, String> next){
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
    private GameState traverse(Function<Integer, String> next){
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

    public GameState getState(Board board) {

        String firstPlayer = "-";
        String winner = "-";
        if (board instanceof TicTacToeBoard board1) {

            String key  = TicTacToeBoard.class.getName();
            for(Rule<TicTacToeBoard> r : ruleMap.get(key)){
                GameState gameState = r.condition.apply(board1);
                if(gameState.isOver()) return gameState;
            }
        }
        return new GameState(false, "-");
    }

    public GameInfo getInfo(Board board){
        if(board instanceof TicTacToeBoard board1){
            GameState gameState = getState(board);
            String[] players = new String[]{"X", "0"};
            for(int playerIndex=0; playerIndex<2; playerIndex++){
                Player currPlayer = new Player(players[playerIndex]);

                for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
                    for (int colIndex = 0; colIndex < 3; colIndex++) {
                        Board boardCopy = board1.copy();
                        boolean canStillWin = false;
                        Cell cell = new Cell(rowIndex, colIndex);
                        board1.move(new Move(currPlayer, cell));
                        for(int i=0; i<3; i++){
                            for(int j=0; j<3; j++){
                                boardCopy.move(new Move(currPlayer.flip(), new Cell(i, j)));

                                if(getState(boardCopy).isOver() && getState(boardCopy).getWinner().equals(currPlayer.flip().symbol())){
                                    canStillWin = true;
                                    break;
                                }
                            }
                            if(canStillWin) break;
                        }
                        if(!canStillWin){
                            return new GameInfoBuilder()
                                    .isOver(getState((boardCopy)).isOver())
                                    .winner(getState(boardCopy).getWinner())
                                    .hasFork(true)
                                    .player(currPlayer.flip())
                                    .build();
                        }
                    }

                }

            }

            return new GameInfoBuilder()
                    .isOver(getState((board1)).isOver())
                    .winner(getState(board1).getWinner())
                    .hasFork(true)
                    .player(null)
                    .build();

        }else{
            throw new IllegalArgumentException();
        }
    }
}
