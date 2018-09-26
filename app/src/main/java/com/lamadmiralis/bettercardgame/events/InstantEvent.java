package com.lamadmiralis.bettercardgame.events;

public class InstantEvent<E extends AbstractEvent> {

    private E event;

    public InstantEvent(final E event) {
        this.event = event;
    }

    public E getEvent() {
        return event;
    }

    public void setEvent(E event) {
        this.event = event;
    }

    public void fire() {
        event.fire();
    }
}
