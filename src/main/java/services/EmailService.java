package services;

import commands.implementation.SendEmailCommand;
import game.User;

public class EmailService {

    public void send(User receiver, String template){
        //logic to send the email
    }

    public void execute(SendEmailCommand sendEmailCommand){
        send(sendEmailCommand.receiver, sendEmailCommand.templateString);
    }
}
