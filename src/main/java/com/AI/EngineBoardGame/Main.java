package com.AI.EngineBoardGame;

import api.AIPlayer;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        GameEngine gameEngine = new GameEngine();
        RuleEngine ruleEngine = new RuleEngine();
        Board board = gameEngine.start("TicTacToe");
        // Hello This is Start of the game
        AIPlayer aiPlayer = new AIPlayer();
        Scanner scanner = new Scanner(System.in);
        int row;
        int col;
        while (!ruleEngine.getState(board).isOver()) {
            row = scanner.nextInt();
            col = scanner.nextInt();
            Player opponent = new Player("X"), computer = new Player("O");
            Move opponentMove = new Move(opponent, new Cell(row, col));
            Move sugeestMove = aiPlayer.suggestMove(board, computer);
            gameEngine.move(board, sugeestMove);
            gameEngine.move(board, opponentMove);
            System.out.println(board);
        }
        System.out.println("Board result is " + ruleEngine.getState(board).isOver() + " Winner is " + ruleEngine.getState(board).getWinner());
    }
}
