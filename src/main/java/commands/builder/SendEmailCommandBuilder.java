package commands.builder;

import commands.implementation.SendEmailCommand;
import game.User;

public class SendEmailCommandBuilder {
    NotificationCommandBuilder notificationCommandBuilder;
    String link;
    String templateId;
    String templateString;


    public SendEmailCommandBuilder user(User user){
        this.notificationCommandBuilder.user(user);
        return this;
    }

    public SendEmailCommandBuilder message(String message){
        this.notificationCommandBuilder.message(message);
        return this;
    }

    public SendEmailCommandBuilder link (String link){
        this.link = link;
        return this;
    }

    public SendEmailCommandBuilder templateId(String templateId){
        this.templateId = templateId;
        return this;
    }

    public SendEmailCommandBuilder templateString(String templateString){
        this.templateString = templateString;
        return this;
    }

    public SendEmailCommand build(){
        return new SendEmailCommand(notificationCommandBuilder.build(), link, templateId, templateString);
    }

}
