package com.AI.EngineBoardGame;

import api.AIPlayer;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class GamePlayTest {

    GameEngine gameEngine;
    RuleEngine ruleEngine;
    AIPlayer aiPlayer;
    @Before
    public void setUp(){
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
        aiPlayer = new AIPlayer();
    }
    @Test
    public void playGame(){
        Board board = gameEngine.start("TicTacToe");
        Scanner scanner = new Scanner(System.in);
        int row;
        int col;
        while (!ruleEngine.getState(board).isOver()) {
            row =
            col = scanner.nextInt();
            Player opponent = new Player("X"), computer = new Player("O");
            Move opponentMove = new Move(opponent, new Cell(row, col));
            Move sugeestMove = aiPlayer.suggestMove(board, computer);
            gameEngine.move(board, sugeestMove);
            gameEngine.move(board, opponentMove);
        }
        System.out.println("Board result is " + ruleEngine.getState(board).isOver() + " Winner is " + ruleEngine.getState(board).getWinner());

    }

}
