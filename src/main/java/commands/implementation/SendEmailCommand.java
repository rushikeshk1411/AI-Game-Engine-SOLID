package commands.implementation;

import events.Event;
import game.User;


public class SendEmailCommand {
    NotificationCommand notificationCommand;
    public String link;
    public String templateId;
    public String templateString;

    public SendEmailCommand(Event event){
        this.notificationCommand = notificationCommand;
        this.link = link;
        this.templateId = templateId;
        this.templateString = templateString;
    }

}
