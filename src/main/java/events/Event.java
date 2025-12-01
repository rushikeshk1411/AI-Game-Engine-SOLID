package events;

import commands.implementation.NotificationCommand;

public abstract class Event {

    NotificationCommand notificationCommand;
    public String link;
    public String templateId;
    public String templateString;

    public NotificationCommand getNotificationCommand(){
        return notificationCommand;
    }

    public String getLink(){
        return link;
    }

    public String getTemplateId(){
        return templateId;
    }

    public String getTemplateString(){
        return templateString;
    }

    //First three sections are done for this things
}
