package api;

import board.Board;
import game.*;

import java.util.HashMap;

public class RuleEngine {

    HashMap<String, RuleSet>  ruleMap = new HashMap<>();
    public RuleEngine(){
        ruleMap.put(TicTacToeBoard.class.getName(), TicTacToeBoard.getRules());
    }

    public GameState getState(Board board) {

        String firstPlayer = "-";
        String winner = "-";
        if (board instanceof TicTacToeBoard board1) {

            String key  = TicTacToeBoard.class.getName();

            for(Rule r : ruleMap.get(key)){
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
                                    .forkCell(cell)
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
