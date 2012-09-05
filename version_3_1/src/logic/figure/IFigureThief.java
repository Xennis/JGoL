package logic.figure;

import events.IAEvent;

/**
 * The interface to the FigureThief
 * 
 * @author Fabian R.
 * @version 3.0
 */
public interface IFigureThief extends IAFigure {

    /**
     * Process {@link events.IAEvent} for a figure.
     * 
     * @param e A event
     */
    public abstract void processEvent(IAEvent e);

    /**
     * Add one ticket to figures tickets.
     * 
     * @param ticket
     *            Ticket type
     */
    public abstract void addTicket(String ticket);

}