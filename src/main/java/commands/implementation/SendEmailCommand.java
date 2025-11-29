package commands.implementation;

import game.User;


public class SendEmailCommand {
    public User receiver;
    public String message;
    public String link;
    public String templateId;
    public String templateString;

    public SendEmailCommand(User receiver, String message, String link, String templateId, String templateString){
        this.receiver = receiver;
        this.message = message;
        this.link = link;
        this.templateId = templateId;
        this.templateString = templateString;
    }

}
