package commands.builder;

import commands.implementation.NotificationCommand;
import game.User;

public class NotificationCommandBuilder {
    User user;
    String message;

    public NotificationCommandBuilder user(User user){
        this.user = user;
        return this;
    }

    public NotificationCommandBuilder message(String message){
        this.message = message;
        return this;
    }

    public NotificationCommand build(){
        return new NotificationCommand(user, message);
    }

}
