package events.logic.gameState;

import java.util.Map;
import java.util.Set;

import logic.figure.IAFigure;

/**
 * The figure data set in this object.
 * 
 * @author Fabian R.
 * @version 2.0
 */
public interface INextFigureEventData {

	/**
	 * 
	 * @return the IAFigure 
	 */
    public abstract IAFigure getFigure();

    /**
     * Sets the figure
     * 
     * @param figure A figure
     */
    public abstract void setFigure(IAFigure figure);

    /**
     * 
     * @return the reachable Links map
     */
    public abstract Map<String, Set<String>> getReachableLinks();

    /**
     * Sets the reachableLinks map
     * 
     * @param reachableLinks Map with all reachable links
     */
    public abstract void setReachableLinks(
	    Map<String, Set<String>> reachableLinks);

    /**
     * 
     * @return the thief log map
     */
    public abstract Map<Integer, String> getThiefLog();

    /**
     * Sets the thief log map.
     * 
     * @param thiefLog Log for thief figures
     */
    public abstract void setThiefLog(Map<Integer, String> thiefLog);

    /**
     * 
     * @return true = is allowed, false = is not allowed
     */
    public abstract boolean isUseDoubleAllowed();

    /**
     * If set to true double ticket is allowed, else not.
     * 
     * @param useDoubleIsAllowed True, if is allowed to use a double ticket
     */
    public abstract void setUseDoubleIsAllowed(boolean useDoubleIsAllowed);

    /**
     * 
     * @return the possible thief map
     */
    public abstract Map<Integer, Set<String>> getPossibleThief();

    /**
     * Sets the possible thief map
     * 
     * @param possibleThief Possible thief positions
     */
    public abstract void setPossibleThief(
	    Map<Integer, Set<String>> possibleThief);

    /**
     * 
     * @return the AllPossiblePositions Set
     */
    public abstract Set<String> getAllPossiblePositions();

    /**
     * Sets the allPossiblePositions
     * @param allPossiblePositions 
     */
    public abstract void setAllPossiblePositions(
	    Set<String> allPossiblePositions);

    /**
     * 
     * @return ShowThieves map
     */
    public abstract Map<Integer, String> getShowThieves();

    /**
     * Sets the show thief map
     * 
     * @param showThieves Map with thief positions
     */
    public abstract void setShowThieves(Map<Integer, String> showThieves);

}