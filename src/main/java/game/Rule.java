package game;

import java.util.function.Function;

public class Rule {
    public Function<Board, GameState> condition;

    public Rule(Function<Board, GameState> ruleState){
        this.condition = ruleState;
    }
}
