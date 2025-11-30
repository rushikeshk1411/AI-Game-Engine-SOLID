package services;

import commands.implementation.SendEmailCommand;
import events.Event;
import game.User;

public class EmailService {

    public void execute(User receiver, String template){
        //logic to send the email
    }

    public void send(Event event) {

        execute(event.getNotificationCommand().getUser(), event.getTemplateString());
    }
}
