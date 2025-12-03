package commands.implementation;

import events.Event;
import game.User;

public class NotificationCommand extends Event {
    User user;
    String message;

    public NotificationCommand(User user, String message){
        this.user = user;
        this.message = message;
    }

    public User getUser(){
        return user;
    }

    public String getMessage() {
        return message;
    }
}
