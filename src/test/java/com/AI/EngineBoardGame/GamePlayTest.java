package com.AI.EngineBoardGame;

import api.AIPlayer;
import api.GameEngine;
import api.RuleEngine;
import game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class GamePlayTest {

    GameEngine gameEngine;
    RuleEngine ruleEngine;
    AIPlayer aiPlayer;
    @BeforeEach
    public void setUp(){
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
        aiPlayer = new AIPlayer();
    }
    private void playGame(Board board, int[][] moves){

        int row;
        int col;
        int index = 0;
        while (index < 3) {
            row = moves[index][0];
            col = moves[index][1];
            Player opponent = new Player("X"), computer = new Player("O");
            Move opponentMove = new Move(opponent, new Cell(row, col));
            gameEngine.move(board, opponentMove);

            Move sugeestMove = aiPlayer.suggestMove(board, computer);
            gameEngine.move(board, sugeestMove);

            index++;
        }
        System.out.println("Board result is " + ruleEngine.getState(board).isOver() + " Winner is " + ruleEngine.getState(board).getWinner());

    }

    @Test
    public void rowWin(){
        int[][] moves = new int[][]{{1, 0},{1, 1},{1, 2}};
        Board board = gameEngine.start("TicTacToe");
        playGame(board, moves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void columnWin(){
        int[][] moves = new int[][]{{0, 0},{1, 0},{2, 0}};
        Board board = gameEngine.start("TicTacToe");
        playGame(board, moves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void firstDiagonalWin(){
        int[][] moves = new int[][]{{0, 0},{1, 1},{2, 2}};
        Board board = gameEngine.start("TicTacToe");
        playGame(board, moves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void lastDiagonalWin(){
        int[][] moves = new int[][]{{0, 2},{1, 1},{2, 0}};
        Board board = gameEngine.start("TicTacToe");
        playGame(board, moves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }


}
