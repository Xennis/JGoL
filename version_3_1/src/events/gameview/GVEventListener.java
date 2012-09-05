package events.gameview;

import events.IAEvent;

/**
 * Interface for gameView Events.
 * 
 * @author Fabian R.
 * 
 */
public interface GVEventListener {

    /**
     * Process a event.
     * 
     * @param e
     *            {@link events.IAEvent}
     */
    public void gVEventHappend(IAEvent e);

}
