package events.gameview.gameState;

import events.AEvent;

/**
 * This event is for a player surrenders.
 * 
 * @author Fabian
 * @version 1.0
 * 
 */
public class EPlayerSurrender extends AEvent {

    /**
	 * 
	 */
    private static final long serialVersionUID = -75193249266529736L;

    /**
     * Create a new Event the owner of the current figure surrenders.
     * 
     * @param source
     *            Event source
     * @param eventType
     *            Event id integer
     */
    public EPlayerSurrender(Object source, int eventType) {
	super(source, eventType);
    }

}
