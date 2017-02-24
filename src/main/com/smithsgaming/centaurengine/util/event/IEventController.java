package smithsgaming.centaurengine.util.event;

import java.util.Queue;

/**
 * Created by Tim on 05/09/2016.
 */
public interface IEventController {

    Queue<Event> getEventQueue();

    default void registerEvent(Event event) {
        synchronized (getEventQueue()) {
            getEventQueue().add(event);
        }
    }

}
