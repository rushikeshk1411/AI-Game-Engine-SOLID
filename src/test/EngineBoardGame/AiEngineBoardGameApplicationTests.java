package EngineBoardGame;


import EngineBoardGame.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import api.*;
import game.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Main.class)
class AiEngineBoardGameApplicationTests {

	api.GameEngine gameEngine;
	api.RuleEngine ruleEngine;
	api.AIPlayer aiPlayer;


	@BeforeEach
	public void setup(){
		gameEngine = new api.GameEngine();
		ruleEngine = new api.RuleEngine();
		aiPlayer = new api.AIPlayer();
	}

	private void playGame(game.Board board, int[][] moves){
		int row;
		int col;
		int next = 0;
		while (!ruleEngine.getState(board).isOver()) {
			row = moves[next][0];
			col = moves[next][1];
			next++;
			game.Player opponent = new Player("X"), computer = new Player("O");
			Move opponentMove = new Move(opponent, new Cell(row, col));
			Move sugeestMove = aiPlayer.suggestMove(board, computer);
			gameEngine.move(board, sugeestMove);
			gameEngine.move(board, opponentMove);
		}

	}
	@Test
	public void checkRowWin(){
		Board board = gameEngine.start("TicTacToe");

		int[][] moves = new int[][]{{1, 0}, {1, 1}, {1,2}};
		playGame(board, moves);
		assertTrue(ruleEngine.getState(board).isOver());
		assertEquals("X", ruleEngine.getState(board).getWinner());
		System.out.println("Board result is " + ruleEngine.getState(board).isOver() + " Winner is " + ruleEngine.getState(board).getWinner());

	}

	@Test
	public void checkColWin(){
		Board board = gameEngine.start("TicTacToe");
		int[][] moves = new int[][]{{0, 0}, {1, 0}, {2, 0}};
		playGame(board, moves);
		assertTrue(ruleEngine.getState(board).isOver());
		assertEquals("X", ruleEngine.getState(board).getWinner());
	}

	@Test
	public void checkStartDiagonalWin(){
		Board board = gameEngine.start("TicTacToe");
		int[][] moves = new int[][]{{0, 0}, {1, 1}, {2, 2}};
		playGame(board, moves);
		assertTrue(ruleEngine.getState(board).isOver());
		assertEquals("X", ruleEngine.getState(board).getWinner());
	}

	@Test
	public void checkEndDiagonalWin(){
		Board board = gameEngine.start("TicTacToe");
		int[][] moves = new int[][]{{0, 2}, {1, 1}, {2, 0}};
		playGame(board, moves);

		assertTrue(ruleEngine.getState(board).isOver());
		assertEquals("X", ruleEngine.getState(board).getWinner());
	}
}
