/**
 * 
 */
package events.gameview.global;

import events.AEvent;

/**
 * Event to end the game.
 * 
 * @author Fabian
 * @version 2.0
 */
public class EEndGame extends AEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1477584272958036369L;

	/**
	 * Create a Event to end the game.
	 * 
	 * @param source
	 *            Event source
	 * @param eventType
	 *            Event id integer
	 */
	public EEndGame(Object source, int eventType) {
		super(source, eventType);
	}

}
