package events.logic.gameState;

import events.AEvent;

/**
 * Delivers a figure that has to be removed
 * 
 * @author Fabian R.
 * @version 2.0
 */
public class EFigureKilled extends AEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int figureID;

	/**
	 * create an event to kill a figure
	 * 
	 * @param source
	 *            event source
	 * @param eventType
	 *            eventType
	 * @param figureID
	 *            the killed figure
	 */
	public EFigureKilled(Object source, int eventType, int figureID) {
		super(source, eventType);
		this.figureID = figureID;
	}

	/**
	 * 
	 * @return the figureID
	 */
	public int getFigureID() {
		return figureID;
	}

}
