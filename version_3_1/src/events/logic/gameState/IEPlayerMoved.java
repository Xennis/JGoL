package events.logic.gameState;

import events.IAEvent;

/**
 * 
 * @author Fabian R.
 * @version 2.0
 */
public interface IEPlayerMoved extends IAEvent {

	/**
	 * 
	 * @return the new position String
	 */
    public abstract String getNewPosition();

    /**
     * 
     * @return the ticket type string
     */
    public abstract String getTicketType();

}