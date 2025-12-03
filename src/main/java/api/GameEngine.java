package api;


import board.Board;
import board.TicTacToeBoard;
import game.*;

public class GameEngine {

	public Board start(String type) {
		if(type.equals("TicTacToe")){
			return new TicTacToeBoard();
		}else{
			throw new IllegalArgumentException();
		}
	}

	public Board move(Board board, Move move) {
		if(board instanceof TicTacToeBoard){
			return ((TicTacToeBoard) board).move(move);
		}else{
			throw new IllegalArgumentException();
		}
	}
}


