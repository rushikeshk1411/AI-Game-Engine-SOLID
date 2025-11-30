package events;

import java.util.function.Function;

public class Subscriber {
    Function<Event, Void> subscriber;

    public Subscriber(Function<Event, Void> subscriber){
        this.subscriber = subscriber;
    }
}
