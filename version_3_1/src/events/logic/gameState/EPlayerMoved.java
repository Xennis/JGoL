/**
 * 
 */
package events.logic.gameState;

import events.AEvent;

/**
 * 
 * @author Fabian R.
 * @version 2.0
 */
public class EPlayerMoved extends AEvent implements IEPlayerMoved {

	private String newPosition;
	private String ticketType;

	/**
	 * 
	 */
	private static final long serialVersionUID = -565817573318608932L;

	/**
	 * Player moved event, fired after a EPlayerRequestedMove event was
	 * accepted. Isn't needed anymore.
	 * 
	 * @param source
	 *            the event source
	 * @param eventType
	 *            the eventtype
	 * @param newPosition
	 *            the new position of the player
	 * @param ticketType
	 *            the tickettype used by the player
	 */
	public EPlayerMoved(Object source, int eventType, String newPosition,
			String ticketType) {
		super(source, eventType);
		this.newPosition = newPosition;
		this.ticketType = ticketType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.logic.gameState.IEPlayerMoved#getNewPosition()
	 */
	@Override
	public String getNewPosition() {
		return newPosition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.logic.gameState.IEPlayerMoved#getTicketType()
	 */
	@Override
	public String getTicketType() {
		return ticketType;
	}
}
