package events.logic.gameState;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import logic.figure.IAFigure;

/**
 * The eventDataClass for the ENextFigure Event
 * 
 * @author Fabian R.
 * @version 2.0
 */
public class NextFigureEventData implements INextFigureEventData {

	private IAFigure figure;
	private Map<String, Set<String>> reachableLinks;
	private Map<Integer, String> thiefLog;
	private boolean useDoubleIsAllowed;
	private Map<Integer, Set<String>> possibleThief;
	private Set<String> allPossiblePositions;
	private Map<Integer, String> showThieves;

	/**
	 * The eventDataClass for the ENextFigure Event
	 */
	public NextFigureEventData() {
		figure = null;
		reachableLinks = new HashMap<String, Set<String>>();
		thiefLog = new HashMap<Integer, String>();
		useDoubleIsAllowed = false;
		possibleThief = new HashMap<Integer, Set<String>>();
		allPossiblePositions = new HashSet<String>();
		showThieves = new HashMap<Integer, String>();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.logic.gameState.INextFigureEventData#getFigure()
	 */
	@Override
	public IAFigure getFigure() {
		return figure;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.logic.gameState.INextFigureEventData#setFigure(logic.figure.IAFigure
	 * )
	 */
	@Override
	public void setFigure(IAFigure figure) {
		this.figure = figure;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.logic.gameState.INextFigureEventData#getReachableLinks()
	 */
	@Override
	public Map<String, Set<String>> getReachableLinks() {
		return reachableLinks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.logic.gameState.INextFigureEventData#setReachableLinks(java.util
	 * .Map)
	 */
	@Override
	public void setReachableLinks(Map<String, Set<String>> reachableLinks) {
		this.reachableLinks = reachableLinks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.logic.gameState.INextFigureEventData#getThiefLog()
	 */
	@Override
	public Map<Integer, String> getThiefLog() {
		return thiefLog;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.logic.gameState.INextFigureEventData#setThiefLog(java.util.Map)
	 */
	@Override
	public void setThiefLog(Map<Integer, String> thiefLog) {
		this.thiefLog = thiefLog;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.logic.gameState.INextFigureEventData#isUseDoubleAllowed()
	 */
	@Override
	public boolean isUseDoubleAllowed() {
		return useDoubleIsAllowed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.logic.gameState.INextFigureEventData#setUseDoubleIsAllowed(boolean
	 * )
	 */
	@Override
	public void setUseDoubleIsAllowed(boolean useDoubleIsAllowed) {
		this.useDoubleIsAllowed = useDoubleIsAllowed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.logic.gameState.INextFigureEventData#getPossibleThief()
	 */
	@Override
	public Map<Integer, Set<String>> getPossibleThief() {
		return possibleThief;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.logic.gameState.INextFigureEventData#setPossibleThief(java.util
	 * .Map)
	 */
	@Override
	public void setPossibleThief(Map<Integer, Set<String>> possibleThief) {
		this.possibleThief = possibleThief;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.logic.gameState.INextFigureEventData#getAllPossiblePositions()
	 */
	@Override
	public Set<String> getAllPossiblePositions() {
		return allPossiblePositions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.logic.gameState.INextFigureEventData#setAllPossiblePositions(java
	 * .util.Set)
	 */
	@Override
	public void setAllPossiblePositions(Set<String> allPossiblePositions) {
		this.allPossiblePositions = allPossiblePositions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.logic.gameState.INextFigureEventData#getShowThieves()
	 */
	@Override
	public Map<Integer, String> getShowThieves() {
		return showThieves;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.logic.gameState.INextFigureEventData#setShowThieves(java.util.Map)
	 */
	@Override
	public void setShowThieves(Map<Integer, String> showThieves) {
		this.showThieves = showThieves;
	}
}