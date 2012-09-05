package events;

/**
 * The interface for the AEvents
 * 
 * @author Jasper S.
 * @version 3.0
 */
public interface IAEvent {

    /**
     * @since 1.0
     * @return source of the Event
     */
    public abstract Object getSource();

    /**
     * @since 1.0
     * @return the EvenType as int (see EventType for more)
     */
    public abstract int getEventType();

    /**
     * Sets the processed value on true
     * 
     * @since 1.0
     */
    public abstract void processed();

    /**
     * Will tell you if the Event is processed and can be deleted
     * 
     * @return true, if event is processed
     */
    public abstract boolean isProcessed();

}