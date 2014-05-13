package logic.figure;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import logic.player.IAPlayer;

import events.IAEvent;

/**
 * The interface to AFigure.
 * 
 * @author Fabian R.
 * @version 3.0
 */
public interface IAFigure {

    /**
     * Get the figure identification integer.
     * 
     * @return figure identification integer
     */
    public abstract int getId();

    /**
     * Get the {@link logic.player.IAPlayer} of this figure
     * 
     * @return {@link logic.player.IAPlayer} of this figure
     */
    public abstract IAPlayer getOwner();

    /**
     * Set a new position of the figure and decrease the ticket counter
     * 
     * @param position
     *            New map point
     * @param ticket
     *            Ticket type the player uses
     */
    public abstract void setPosition(String position, String ticket);

    /**
     * Get the current figure position.
     * 
     * @return Current position
     */
    public abstract String getPosition();

    /**
     * Get a map with ticket types as identifier and number of this type as
     * value
     * 
     * @return Map with ticket types as identifier and number of this type as
     *         value
     */
    public abstract Map<String, Integer> getTicketsRemaining();

    /**
     * Get a list of ticket types with more than zero tickets.
     * 
     * @return List of ticket types with more than zero tickets
     */
    public abstract List<String> getTicketTypesRemaining();

    /**
     * Get the figure icon.
     * 
     * @return Figure icon
     */
    public abstract BufferedImage getIcon();

    /**
     * Process {@link events.IAEvent}.
     * 
     * @param e
     *            IAEvent happened
     */
    public abstract void processEvent(IAEvent e);

    /**
     * Check figure is thief
     * 
     * @return true, if figure is thief
     */
    public abstract boolean isThief();

    /**
     * Check figure is police
     * 
     * @return true, if figure is police
     */
    public abstract boolean isPolice();

    /**
     * Add a tickets to figure.
     * 
     * @param ticketType
     *            Type of the ticket
     * @param count
     *            Number of tickets
     */
    public abstract void addTicket(String ticketType, int count);

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * Get number of figures moves
     * 
     * @return Number of moves
     */
    public abstract int getNumMoves();

    /**
     * Check figure has more then zero double tickets.
     * 
     * @return true, if figure has more then zero double tickets
     */
    public abstract boolean hasDoubleTicket();

    /**
     * Decrement number of double tickets.
     */
    public abstract void usedDoubleTicket();
}