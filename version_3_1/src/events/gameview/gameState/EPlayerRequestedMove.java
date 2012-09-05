/**
 * 
 */
package events.gameview.gameState;

import events.AEvent;

/**
 * Player sets figure to a new position. GameView requested by logic.
 * 
 * @author Fabian
 * @version 1.0
 */
public class EPlayerRequestedMove extends AEvent implements
		IEPlayerRequestedMove {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ticketType;
	private String destination;
	private boolean usedDoubleTicket;

	/**
	 * Create a new Event to request a figure move.
	 * 
	 * @param source
	 *            Event source
	 * @param eventType
	 *            Event id integer
	 * @param ticketType
	 *            Ticket type
	 * @param destination
	 *            Destination station
	 * @param usedDoubleTicket
	 *            True, if used double ticket
	 */
	public EPlayerRequestedMove(Object source, int eventType,
			String ticketType, String destination, boolean usedDoubleTicket) {
		super(source, eventType);
		this.ticketType = ticketType;
		this.destination = destination;
		this.usedDoubleTicket = usedDoubleTicket;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.gameView.gameState.IEPlayerRequestedMove#getDestination()
	 */
	@Override
	public String getDestination() {
		return destination;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.gameView.gameState.IEPlayerRequestedMove#getTicketType()
	 */
	@Override
	public String getTicketType() {
		return ticketType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.gameView.gameState.IEPlayerRequestedMove#usedDoubleTicket()
	 */
	@Override
	public boolean usedDoubleTicket() {
		return usedDoubleTicket;
	}

}
