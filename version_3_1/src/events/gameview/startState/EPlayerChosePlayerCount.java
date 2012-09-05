/**
 * 
 */
package events.gameview.startState;

import events.AEvent;

/**
 * send if the player choose the player count this is necessary to create the
 * right number of player panels.
 * 
 * @author Raphael A.
 * @version 2.0
 * 
 */
public class EPlayerChosePlayerCount extends AEvent implements
		IEPlayerChosePlayerCount {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int playerCount;
	private Integer[] specialTicketArray;

	/**
	 * Create Event with game configurations.
	 * 
	 * @param source
	 *            Event source
	 * @param eventType
	 *            Event id integer
	 * @param playerCount
	 *            Number of players
	 * @param specialTicketArray
	 *            Number of special tickets
	 */
	public EPlayerChosePlayerCount(Object source, int eventType,
			int playerCount, Integer[] specialTicketArray) {
		super(source, eventType);
		this.playerCount = playerCount;
		this.specialTicketArray = specialTicketArray;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.gameView.startState.IEPlayerChosePlayerCount#getPlayerCount()
	 */
	@Override
	public int getPlayerCount() {
		return playerCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.gameView.startState.IEPlayerChosePlayerCount#getSpecialTicketArray
	 * ()
	 */
	@Override
	public Integer[] getSpecialTicketArray() {
		return specialTicketArray;
	}
}
