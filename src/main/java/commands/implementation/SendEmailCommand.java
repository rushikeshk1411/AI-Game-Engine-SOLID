package commands.implementation;

import events.Event;
import game.User;


public class SendEmailCommand {
    NotificationCommand notificationCommand;
    public String link;
    public String templateId;
    public String templateString;

    public SendEmailCommand(Event event){
        this.notificationCommand = event.getNotificationCommand();
        this.link = event.getLink();
        this.templateId = event.getTemplateId();
        this.templateString = event.getTemplateString();
    }

}
