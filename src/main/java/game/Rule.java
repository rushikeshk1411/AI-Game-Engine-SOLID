package game;

import board.CellBoard;

import java.util.function.Function;

public class Rule {
    public Function<CellBoard, GameState> condition;

    public Rule(Function<CellBoard, GameState> ruleState){
        this.condition = ruleState;
    }
}
