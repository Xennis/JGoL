package logic;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The log for the gState. Its used for the little log in the lower right edge
 * 
 * @author Felix K.
 * @version 3.0
 * 
 */
public class GameLog implements IGameLog {

    private Map<Integer, String> gameLog;
    private Map<Integer, String> specialThiefLog;
    private int thiefKeyCounter = 0;
    private int logKeyCounter = 0;

    // ----
    public GameLog() {
	// preserve insertion order with linked hmap
	gameLog = new LinkedHashMap<Integer, String>();
	specialThiefLog = new LinkedHashMap<Integer, String>();

    }

    /*
     * Round 42, Player 5, Figure 13 ==> moved from 14 to 15 with ticket: walk
     * Round 42, Player 5, Figure 13 ==> Collision happened with Player 3,
     * Figure 1, deleting Figure 13 Round 42, Player 5, Figure 14 ==> moved from
     * 1 to 2 with ticket: fly
     */
    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameLog#updateLog(java.lang.String)
     */
    @Override
    public void updateLog(String value) {
	gameLog.put(logKeyCounter++, value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameLog#returnLog()
     */
    @Override
    public Map<Integer, String> returnLog() {
	return gameLog;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameLog#returnThiefLog()
     */
    @Override
    public Map<Integer, String> returnThiefLog() {
	return specialThiefLog;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameLog#updateThiefLog(java.lang.String)
     */
    @Override
    public void updateThiefLog(String value) {
	specialThiefLog.put(thiefKeyCounter++, value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameLog#clearLog()
     */
    @Override
    public void clearLog() {
	specialThiefLog.clear();
	gameLog.clear();
    }

}
