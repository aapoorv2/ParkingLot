package org.example;

public interface Subscriber {
    default void notify(Event event, Object publisher) {
        System.out.println(event.toString() + " " + publisher.toString());
    }
}
