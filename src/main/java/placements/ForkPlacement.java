package placements;

import game.*;
import utils.Util;

import java.util.Objects;
import java.util.Optional;

public class ForkPlacement implements Placements{

    public static ForkPlacement forkPlacement;

    private ForkPlacement(){

    }
    public static synchronized ForkPlacement get(){
        return (ForkPlacement) Util.getIfNull(forkPlacement, ForkPlacement::new);
    }
    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        GameInfo gameInfo = ruleEngine.getInfo(board);
        if(gameInfo.hasFork()){
            return Optional.of(gameInfo.getForkCell());
        }

        return Optional.empty();
    }

    @Override
    public Placements next() {
        return CenterPlacement.get();
    }
}
