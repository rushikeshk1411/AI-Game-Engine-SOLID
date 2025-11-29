package services;

import commands.implementation.SendSMSCommand;
import game.User;

public class SMSService {

    private void execute(User receiver, String message){
        //logic to send Email
    }

    public void send(SendSMSCommand smsCommand){
        execute(smsCommand.user, smsCommand.message);
    }

}
