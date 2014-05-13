package events;

/**
 * The interface for the EventGenerator
 * 
 * @author Jasper S.
 * 
 * @param <T>
 *            listener interface
 */
public interface IEventGenerator<T> {

    /**
     * Fire a {@link events.IAEvent}.
     * 
     * @param event
     *            {@link events.IAEvent} to fire
     */
    public abstract void fireEvent(IAEvent event);

    /**
     * Add a new listener.
     * 
     * @param l
     *            listener to add
     */
    public abstract void addListener(T l);

    /**
     * Remove a listener
     * 
     * @param l
     *            listener to remove
     */
    public abstract void removeListener(T l);

}