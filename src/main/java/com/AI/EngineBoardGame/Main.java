package com.AI.EngineBoardGame;

import api.AIPlayer;
import api.GameEngine;
import api.RuleEngine;
import board.Board;
import commands.builder.SendSMSCommandBuilder;
import commands.implementation.SendEmailCommand;
import commands.builder.SendEmailCommandBuilder;
import commands.implementation.SendSMSCommand;
import game.Cell;
import game.Move;
import game.Player;
import game.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import services.EmailService;
import services.SMSService;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Main {
    public static void main(String[] args){
        GameEngine gameEngine = new GameEngine();
        RuleEngine ruleEngine = new RuleEngine();
        Board board = gameEngine.start("TicTacToe");
        // Hello This is Start of the game
        AIPlayer aiPlayer = new AIPlayer();
        Scanner scanner = new Scanner(System.in);
        SMSService smsService = new SMSService();
        EmailService emailService = new EmailService();

        int row;
        int col;
        Player opponent = new Player("X"), computer = new Player("O");
        while (!ruleEngine.getState(board).isOver()) {
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move opponentMove = new Move(opponent, new Cell(row, col));
            gameEngine.move(board, opponentMove);
            Move sugeestMove = aiPlayer.suggestMove(board, computer);
            gameEngine.move(board, sugeestMove);
            System.out.println(board);
        }



        User winner = opponent.getUser();
        if(winner.activeAfter(10, TimeUnit.DAYS)){
            SendEmailCommand sendEmailCommand =
                    new SendEmailCommandBuilder()
                    .receiver(winner)
                    .message("Congratulations you won")
                    .build();

            SendSMSCommand sendSMSCommand = new SendSMSCommandBuilder()
                             .user(winner)
                             .message("Conrgatuilation you won")
                             .build();

            emailService.execute(sendEmailCommand);
            smsService.send(sendSMSCommand);


        }
        System.out.println("Board result is " + ruleEngine.getState(board).isOver() + " Winner is " + ruleEngine.getState(board).getWinner());
    }
}
