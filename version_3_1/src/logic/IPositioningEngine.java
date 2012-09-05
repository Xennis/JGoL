package logic;

import java.util.Map;
import java.util.Set;

import core.IStationMap;
import events.logic.gameState.IEPlayerMoved;

/**
 * The interface to the PositionEngine class.
 * 
 * @author Jasper S.
 * @version 3.0
 */
public interface IPositioningEngine {

    /**
     * set the linkmap to another value
     * 
     * @param links
     *            the linkmap {@link core.StationMap}
     */
    public abstract void setLinks(IStationMap links);

    /**
     * intialize some stuff
     */
    public abstract void init();

    /**
     * destroy all data after games ending
     */
    public abstract void destoryData();

    /**
     * whenever a move was done all positiondata must be updated
     * 
     * @param e
     *            the {@link events.logic.gameState.EPlayerMoved} event
     * @param figureID
     *            the ID of the figure which has moved
     */
    public abstract void moveHappend(IEPlayerMoved e, int figureID);

    /**
     * when ever a thief was shown all possible position data is removed and
     * updated to the single spot where he was shown.
     * 
     * @param position
     *            the position
     * @param figureID
     *            the ID of the thief
     */
    public abstract void updatePosition(String position, int figureID);

    /**
     * get all the possible positions as hashmap with figure ID as key and it's
     * positions as value
     * 
     * @return the positions of the thiefs as hashmap with id as key and
     *         positions as value
     */
    public abstract Map<Integer, Set<String>> getPossiblePositions();

    /**
     * get all positions of all thiefs as one Set
     * 
     * @return all possible positions of all thiefs
     */
    public abstract Set<String> getAllPossiblePositions();

}