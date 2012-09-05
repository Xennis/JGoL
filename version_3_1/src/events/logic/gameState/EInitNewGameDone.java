package events.logic.gameState;

import java.util.List;

import events.AEvent;

/**
 * 
 * @author Fabian R.
 * @version 2.0
 * 
 */
public class EInitNewGameDone extends AEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1147156906986701330L;

	private List<Integer> figureQueue;

	/**
	 * When all gameData was send to the GState this event is send to tell that
	 * the game can start.
	 * 
	 * @param source
	 *            the event source
	 * @param eventType
	 *            the event type
	 * @param figureQueue
	 *            the figure queue to display the right figure queue in the HUD
	 */
	public EInitNewGameDone(Object source, int eventType,
			List<Integer> figureQueue) {
		super(source, eventType);
		this.figureQueue = figureQueue;
	}

	/**
	 * 
	 * @return the figureQueue
	 */
	public List<Integer> getFigureQueue() {
		return figureQueue;
	}

}
