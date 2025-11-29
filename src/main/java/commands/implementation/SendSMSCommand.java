package commands.implementation;

import game.User;

public class SendSMSCommand {
    public User user;
    public String message;

    public SendSMSCommand(User user, String message){
        this.user = user;
        this.message = message;
    }

}
