package board;

import game.Move;

public interface Board{

	public abstract void move(Move move);

	public abstract Board copy();
}
