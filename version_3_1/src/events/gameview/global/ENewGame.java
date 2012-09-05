package events.gameview.global;

import events.AEvent;

/**
 * GameView Event to initialize a new game.
 * 
 * @author Fabian
 * @version 1.0
 */
public class ENewGame extends AEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8804852027258477282L;

	/**
	 * Create a Event to initialize a new game.
	 * 
	 * @param source
	 *            Event source
	 * @param eventType
	 *            Event id integer
	 */
	public ENewGame(Object source, int eventType) {
		super(source, eventType);
	}

}
