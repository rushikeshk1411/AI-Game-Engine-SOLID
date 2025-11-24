package placements;

import api.RuleEngine;
import game.*;

import java.util.Optional;

public interface Placements {

    RuleEngine ruleEngine = new RuleEngine();
    Optional<Cell> place(TicTacToeBoard board, Player player);

    Placements next();
}
