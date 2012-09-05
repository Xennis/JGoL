/**
 * 
 */
package events.gameview.global;

import events.AEvent;

/**
 * Event to start game.
 * 
 * @author Fabian
 * @version 1.0
 */
public class EStartGame extends AEvent {

	private static final long serialVersionUID = -1691657465694970187L;

	/**
	 * Create event to start game.
	 * 
	 * @param source
	 *            Event source
	 * @param eventType
	 *            Event id integer
	 */
	public EStartGame(Object source, int eventType) {
		super(source, eventType);
	}

}
