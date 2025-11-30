package events;


import java.util.ArrayList;
import java.util.List;

public class EventBus {
    private List<Event> eventList = new ArrayList<>();
    private List<Subscriber> suscriberList = new ArrayList<>();

    public void subscribe(Subscriber subscriber){
        suscriberList.add(subscriber);
    }

    public void publish(Event event){
        eventList.add(event);
    }
}


