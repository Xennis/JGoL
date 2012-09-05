package events.logic.gameState;

import java.util.Map;
import java.util.Set;

import events.AEvent;
import events.EventConstants;

/**
 * 
 * @author Fabian R.
 * @version 2.0
 */
public class ENextFigure extends AEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1614758543958108852L;

	private int figureID;
	private String position;
	private int playerID;
	private Map<String, Integer> tokens;
	private Map<String, Set<String>> reachableLinks;
	private Map<Integer, String> thiefLog;
	private boolean useDoubleIsAllowed;
	private Map<Integer, Set<String>> possibleThief;
	private Map<Integer, String> showThieves;

	private Set<String> allPossiblePositions;

	/**
	 * The next figure Event signals the GameView that the next figure should do
	 * a move. All current data about the figure and stuff like possible
	 * Positions of the thiefs are send by this event
	 * 
	 * @param source
	 *            the event source
	 * @param data
	 *            the eventDataObject
	 */
	public ENextFigure(Object source, INextFigureEventData data) {
		super(source, EventConstants.NEXT_FIGURE_EVENT);
		this.figureID = data.getFigure().getId();
		this.position = data.getFigure().getPosition();
		this.tokens = data.getFigure().getTicketsRemaining();
		this.reachableLinks = data.getReachableLinks();
		this.thiefLog = data.getThiefLog();
		this.useDoubleIsAllowed = data.isUseDoubleAllowed();
		this.playerID = data.getFigure().getOwner().getId();
		this.possibleThief = data.getPossibleThief();
		this.allPossiblePositions = data.getAllPossiblePositions();
		this.showThieves = data.getShowThieves();
	}

	/**
	 * 
	 * @return the showThieves map
	 */
	public Map<Integer, String> getShowThieves() {
		return showThieves;
	}

	/**
	 * 
	 * @return the figure ID
	 */
	public int getFigureId() {
		return figureID;
	}

	/**
	 * 
	 * @return the player ID
	 */
	public int getPlayerId() {
		return playerID;
	}

	/**
	 * 
	 * @return the possibleThief map
	 */
	public Map<Integer, Set<String>> getPossibleThief() {
		return possibleThief;
	}

	/**
	 * 
	 * @return the position String
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * 
	 * @return the token map
	 */
	public Map<String, Integer> getTokens() {
		return tokens;
	}

	/**
	 * 
	 * @return the reachable links 
	 */
	public Map<String, Set<String>> getReachableLinks() {
		return reachableLinks;
	}

	/**
	 * 
	 * @return the thief log map
	 */
	public Map<Integer, String> getThiefLog() {
		return thiefLog;
	}

	/**
	 * 
	 * @return the useDoubleIsAllowed boolean
	 */
	public boolean useDoubleTicketIsAllowed() {
		return useDoubleIsAllowed;
	}

	/**
	 * 
	 * @return the allPossiblePositions Set
	 */
	public Set<String> getAllPossiblePositions() {
		return allPossiblePositions;
	}

	/* (non-Javadoc)
	 * @see java.util.EventObject#toString()
	 */
	@Override
	public String toString() {
		return "ENextFigure [figureID=" + figureID + ", position=" + position
				+ ", playerID=" + playerID + ", tokens=" + tokens
				+ ", reachableLinks=" + reachableLinks
				+ ", useDoubleIsAllowed=" + useDoubleIsAllowed
				+ ", possibleThief=" + possibleThief + ", showThieves="
				+ showThieves + ", allPossiblePositions="
				+ allPossiblePositions + "]";
	}

}
