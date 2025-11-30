package services;

import commands.implementation.NotificationCommand;
import commands.implementation.SendSMSCommand;
import events.Event;
import game.User;

public class SMSService {

    private void execute(User receiver, String message){
        //logic to send Email
    }

    public void send(Event event){
        execute(event.getNotificationCommand().getUser(), event.getNotificationCommand().getMessage());
    }

}
