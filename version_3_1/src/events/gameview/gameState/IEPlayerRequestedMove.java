package events.gameview.gameState;

import events.IAEvent;

/**
 * 
 * @author Fabian
 * @version 1.0
 */
public interface IEPlayerRequestedMove extends IAEvent {

    /**
     * Get destination station.
     * 
     * @return destination station
     */
    public abstract String getDestination();

    /**
     * Get used ticket type.
     * 
     * @return ticket type
     */
    public abstract String getTicketType();

    /**
     * Check player used double ticket.
     * 
     * @return true, if used double ticket
     */
    public abstract boolean usedDoubleTicket();

}