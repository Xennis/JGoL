/**
 * 
 */
package events.logic.gameState;

import java.util.Map;
import java.util.Set;

import core.StationStub;

import logic.figure.FigureThiefStub;
import logic.figure.IAFigure;

/**
 * @author Fabian
 * 
 */
public class NextFigureEventDataStub implements INextFigureEventData {

    /*
     * (non-Javadoc)
     * 
     * @see events.logic.gameState.INextFigureEventData#getFigure()
     */
    @Override
    public IAFigure getFigure() {
	return new FigureThiefStub();
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
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see events.logic.gameState.INextFigureEventData#getReachableLinks()
     */
    @Override
    public Map<String, Set<String>> getReachableLinks() {
	return new StationStub().getReachableLinks(null);
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
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see events.logic.gameState.INextFigureEventData#getThiefLog()
     */
    @Override
    public Map<Integer, String> getThiefLog() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * events.logic.gameState.INextFigureEventData#setThiefLog(java.util.Map)
     */
    @Override
    public void setThiefLog(Map<Integer, String> thiefLog) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see events.logic.gameState.INextFigureEventData#isUseDoubleAllowed()
     */
    @Override
    public boolean isUseDoubleAllowed() {
	// TODO Auto-generated method stub
	return false;
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
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see events.logic.gameState.INextFigureEventData#getPossibleThief()
     */
    @Override
    public Map<Integer, Set<String>> getPossibleThief() {
	// TODO Auto-generated method stub
	return null;
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
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * events.logic.gameState.INextFigureEventData#getAllPossiblePositions()
     */
    @Override
    public Set<String> getAllPossiblePositions() {
	// TODO Auto-generated method stub
	return null;
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
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see events.logic.gameState.INextFigureEventData#getShowThieves()
     */
    @Override
    public Map<Integer, String> getShowThieves() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * events.logic.gameState.INextFigureEventData#setShowThieves(java.util.Map)
     */
    @Override
    public void setShowThieves(Map<Integer, String> showThieves) {
	// TODO Auto-generated method stub

    }

}
