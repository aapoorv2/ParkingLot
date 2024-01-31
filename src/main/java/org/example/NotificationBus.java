package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NotificationBus {
    private static final NotificationBus INSTANCE = new NotificationBus();
    private final Map<Event, ArrayList<Subscriber>> subscribers;
    private NotificationBus () {
        this.subscribers = new HashMap<>();
        for (Event event : Event.values()) {
            subscribers.put(event, new ArrayList<>());
        }
    }

    public static NotificationBus instance() {
        return INSTANCE;
    }

    public void publish(Object publisher, Event event) {
        for (Subscriber subscriber : subscribers.get(event)) {
            subscriber.notify(event, publisher);
        }
    }

    public void subscribe(Subscriber observer, Event event){

        subscribers.computeIfAbsent(event, k -> subscribers.getOrDefault(k, new ArrayList<>())).add(observer);
    }
}