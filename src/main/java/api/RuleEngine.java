package api;

import board.Board;
import game.*;
import placements.DefensivePlacement;
import placements.OffensivePlacement;

import java.util.HashMap;
import java.util.Optional;

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
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            GameState gameState = getState(ticTacToeBoard);
            for(TicTacToeBoard.Symbol symbol : TicTacToeBoard.Symbol.values()){
                Player currPlayer = new Player(symbol.marker);

                for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
                    for (int colIndex = 0; colIndex < 3; colIndex++) {
                        if(((TicTacToeBoard) board).getSymbol(rowIndex, colIndex) != null ){
                            TicTacToeBoard ticTacToeBoard1 = ticTacToeBoard.move(new Move( currPlayer, new Cell(rowIndex, colIndex)));
                            DefensivePlacement defensivePlacement = DefensivePlacement.get();
                            Optional<Cell> defensivePlace = defensivePlacement.place(ticTacToeBoard1, currPlayer.flip());

                            if(defensivePlace.isPresent()){
                                ticTacToeBoard1.move(new Move( currPlayer.flip(), defensivePlace.get()));
                                OffensivePlacement offensivePlacement = OffensivePlacement.get();
                                Optional<Cell> offensivePlace = offensivePlacement.place(ticTacToeBoard1, currPlayer);
                                if(offensivePlace.isPresent()){
                                    return new GameInfoBuilder()
                                            .isOver(getState((ticTacToeBoard1)).isOver())
                                            .winner(getState(ticTacToeBoard1).getWinner())
                                            .hasFork(true)
                                            .player(currPlayer.flip())
                                            .forkCell(new Cell(rowIndex, colIndex))
                                            .build();
                                }
                            }

                        }

                    }
                }
            }
            return new GameInfoBuilder()
                    .isOver(getState((ticTacToeBoard)).isOver())
                    .winner(getState(ticTacToeBoard).getWinner())
                    .hasFork(false)
                    .player(null)
                    .build();

        }else{
            throw new IllegalArgumentException();
        }
    }
}
