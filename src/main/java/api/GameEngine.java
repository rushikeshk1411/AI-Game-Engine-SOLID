package api;


import board.Board;
import game.*;

public class GameEngine {

	public Board start(String type) {
		if(type.equals("TicTacToe")){
			return new TicTacToeBoard();
		}else{
			throw new IllegalArgumentException();
		}
	}

	public void move(Board board, Move move) {
		if(board instanceof TicTacToeBoard){
			((TicTacToeBoard) board).move(move);
		}else{
			throw new IllegalArgumentException();
		}
	}
}


