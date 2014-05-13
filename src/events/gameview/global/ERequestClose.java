package events.gameview.global;

import events.AEvent;

/**
 * Send if there is a close of the window is requested.
 * 
 * @author Raphael
 * @version 1.0
 */
public class ERequestClose extends AEvent {

	private static final long serialVersionUID = 548148501137621937L;

	/**
	 * Event to request closing the game.
	 * 
	 * @param source
	 *            Event source
	 * @param eventType
	 *            Event id integer
	 */
	public ERequestClose(Object source, int eventType) {
		super(source, eventType);
	}

}
