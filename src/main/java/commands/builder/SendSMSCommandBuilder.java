package commands.builder;

import commands.implementation.SendSMSCommand;
import game.User;

public class SendSMSCommandBuilder {
    NotificationCommandBuilder notificationCommandBuilder;
    public User user;
    public String message;
    String link;

    public SendSMSCommandBuilder user(User user){
        this.user = user;
        return this;
    }

    public SendSMSCommandBuilder message(String message){
        this.message = message;
        return this;
    }

    public SendSMSCommand build(){
        return new SendSMSCommand(notificationCommandBuilder.build(), link);
    }
}
