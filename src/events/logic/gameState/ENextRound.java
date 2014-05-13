/**
 * 
 */
package events.logic.gameState;

import events.AEvent;
import events.EventConstants;

/**
 * 
 * @author Fabian R.
 * @version 2.0
 */
public class ENextRound extends AEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1780330161963229881L;

	private int number;

	/**
	 * The next round event, only to display the roundnumber to players
	 * 
	 * @param source
	 *            the events source
	 * @param number
	 *            the roundnumber
	 */
	public ENextRound(Object source, int number) {
		super(source, EventConstants.NEXT_ROUND);
		this.number = number;
	}

	public int getNumer() {
		return this.number;
	}

}
