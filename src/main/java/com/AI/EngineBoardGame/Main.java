package com.AI.EngineBoardGame;

import api.AIPlayer;
import api.GameEngine;
import api.RuleEngine;
import board.Board;
import commands.builder.SendSMSCommandBuilder;
import commands.implementation.SendEmailCommand;
import commands.builder.SendEmailCommandBuilder;
import commands.implementation.SendSMSCommand;
import events.*;
import game.Cell;
import game.Move;
import game.Player;
import game.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import services.EmailService;
import services.SMSService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Main {
    public static void main(String[] args){
        GameEngine gameEngine = new GameEngine();
        RuleEngine ruleEngine = new RuleEngine();
        Board board = gameEngine.start("TicTacToe");
        SMSService smsService = new SMSService();
        EmailService emailService = new EmailService();

        List<Event> eventList = new ArrayList<>();
        EventBus eventBus = new EventBus();
        Player opponent = new Player("X"), computer = new Player("O");
        eventBus.subscribe(new Subscriber((event) -> {emailService.send(event);return null;}));
        eventBus.subscribe(new Subscriber((event) -> {smsService.send(event);return null;}));




        if(opponent.getUser().activeAfter(10, TimeUnit.DAYS)){
            eventBus.publish(new ActivityEvent(opponent.getUser()));
        }
        // Hello This is Start of the game
        AIPlayer aiPlayer = new AIPlayer();
        Scanner scanner = new Scanner(System.in);


        int row;
        int col;
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

            eventBus.publish(new WinEvent(opponent.getUser()));
//            SendEmailCommand sendEmailCommand =
//                    new SendEmailCommandBuilder()
//                    .user(winner)
//                    .message("Congratulations you won")
//                    .build();
//
//            SendSMSCommand sendSMSCommand = new SendSMSCommandBuilder()
//                             .user(winner)
//                             .message("Conrgatuilation you won")
//                             .build();
//
//            emailService.execute(sendEmailCommand);
//            smsService.send(sendSMSCommand);


        }
        System.out.println("Board result is " + ruleEngine.getState(board).isOver() + " Winner is " + ruleEngine.getState(board).getWinner());
    }
}
