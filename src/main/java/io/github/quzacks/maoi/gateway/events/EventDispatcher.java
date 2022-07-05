package io.github.quzacks.maoi.gateway.events;

import java.util.function.Consumer;

/**
 * Used to listen to events.
 *
 * @see GenericEvent
 */
public class EventDispatcher<E extends GenericEvent> {
    /**
     * Class of the event it is listening to.
     */
    private final Class<E> eventClass;
    /**
     * Effect that runs when event fires.
     */
    private final Consumer<E> effect;

    /**
     * Creates an event dispatcher/
     *
     * @param eventClass Class of the event listening to.
     * @param effect Code to run when event fires.
     */
    public EventDispatcher(Class<E> eventClass, Consumer<E> effect) {
        this.eventClass = eventClass;
        this.effect = effect;
    }

    /**
     * @return Event class.
     */
    public Class<E> getEventClass() {
        return eventClass;
    }

    /**
     * Runs the consumer.
     */
    public void run(E instance) {
        effect.accept(instance);
    }
}
