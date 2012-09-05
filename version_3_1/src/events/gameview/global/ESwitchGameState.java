package events.gameview.global;

import events.AEvent;

/**
 * Send if a game state change is requested.
 * 
 * @author Fabian R.
 * @version 1.0
 * 
 */
public class ESwitchGameState extends AEvent {

	private static final long serialVersionUID = 6893072669035206193L;

	private int gameState;

	/**
	 * Create Event to switch the gameState.
	 * 
	 * @param source
	 *            Event source
	 * @param eventType
	 *            Event id integer
	 * @param gameState
	 *            New gameState as integer
	 */
	public ESwitchGameState(Object source, int eventType, int gameState) {
		super(source, eventType);
		this.gameState = gameState;
	}

	/**
	 * Get new gameState as integer
	 * 
	 * @return gameState integer
	 */
	public int getGameState() {
		return gameState;
	}

}
