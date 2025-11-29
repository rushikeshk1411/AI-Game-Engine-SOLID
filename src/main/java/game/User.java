package game;

import java.util.concurrent.TimeUnit;

public class User {
    private String id;
    long lastActiveTime;

    public User(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean activeAfter(int threshhold, TimeUnit timeUnit){
        return System.currentTimeMillis() - lastActiveTime > timeUnit.toMillis(threshhold);
    }
}
