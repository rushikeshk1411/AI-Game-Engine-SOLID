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
    private void playGame(Board board, int[][] firstPlayerMoves, int[][] secondPlayerMoves){

        int row;
        int col;
        int index = 0;
        while (index < 3) {
            row = firstPlayerMoves[index][0];
            col = firstPlayerMoves[index][1];
            Player opponent = new Player("X"), computer = new Player("O");
            Move opponentMove = new Move(opponent, new Cell(row, col));
            gameEngine.move(board, opponentMove);
            row = secondPlayerMoves[index][0];
            col = secondPlayerMoves[index][1];
            Move sugeestMove = new Move(computer, new Cell(row, col));
            gameEngine.move(board, sugeestMove);

            index++;
        }
        System.out.println("Board result is " + ruleEngine.getState(board).isOver() + " Winner is " + ruleEngine.getState(board).getWinner());
    }

    @Test
    public void rowWin(){
        int[][] firstPlayerMoves = new int[][]{{0, 0}, {0, 1}, {0, 2}};
        int[][] secondPlayerMoves = new int[][]{{1, 0},{1, 1},{1, 2}};
        Board board = gameEngine.start("TicTacToe");
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void columnWin(){
        int[][] firstPlayerMoves = new int[][]{{0, 0},{1, 0},{2, 0}};
        int[][] secondPlayerMoves = new int[][] {{0, 1}, {1, 1}, {2, 1}};
        Board board = gameEngine.start("TicTacToe");
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void firstDiagonalWin(){
        int[][] firstPlayerMoves = new int[][]{{0, 0},{1, 1},{2, 2}};
        int[][] secondPlayermoves = new int[][]{{0, 1}, {0, 2}, {2, 0}};
        Board board = gameEngine.start("TicTacToe");
        playGame(board, firstPlayerMoves, secondPlayermoves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void lastDiagonalWin(){
        int[][] firstPlayerMoves = new int[][]{{0, 2},{1, 1},{2, 0}};
        int[][] secondPlayerMoves = new int[][] {{0, 0}, {0, 1}, {2, 2}};
        Board board = gameEngine.start("TicTacToe");
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void secondPlayerWin(){
        int[][] firstPlayerMoves = new int[][]{{1, 0}, {2, 0}, {2, 2}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {0, 1}, {0, 2}};
        Board board = gameEngine.start("TicTacToe");
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("O", ruleEngine.getState(board).getWinner());
    }


}
