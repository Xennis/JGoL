/**
 * 
 */
package events.gameview.startState;

import events.AEvent;
import gameview.sView.ISPlayer;

/**
 * This event delivers the SPlayer objects to the ActorCcontroler. The object
 * contains all informations to the players.
 * 
 * @author Raphael A.
 * @version 2.0
 */
public class EPlayerSetProperties extends AEvent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    ISPlayer sPlayer;

    /**
     * Create Event to add a new player to game.
     * 
     * @param source
     *            Event source
     * @param eventType
     *            Event id integer
     * @param sPlayer
     *            Player configurations
     */
    public EPlayerSetProperties(Object source, int eventType, ISPlayer sPlayer) {
	super(source, eventType);
	this.sPlayer = sPlayer;
    }

    /**
     * Get player configurations.
     * 
     * @return player configurations.
     */
    public ISPlayer getSPlayer() {
	return sPlayer;
    }
}
