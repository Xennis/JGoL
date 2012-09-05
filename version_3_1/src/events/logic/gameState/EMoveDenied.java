package events.logic.gameState;

import events.AEvent;

/**
 * 
 * @author Fabian R.
 * @version 2.0
 */
public class EMoveDenied extends AEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1492424443604140739L;

	/**
	 * This event should normally never occur. However, when the KI does
	 * something stupid it can occur.
	 * 
	 * @param source
	 *            event source
	 * @param eventType
	 *            event type
	 */
	public EMoveDenied(Object source, int eventType) {
		super(source, eventType);
	}

}
