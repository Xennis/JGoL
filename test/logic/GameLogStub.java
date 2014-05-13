/**
 * 
 */
package logic;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Fabian
 * 
 */
public class GameLogStub implements IGameLog {

    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameLog#updateLog(java.lang.String)
     */
    @Override
    public void updateLog(String value) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameLog#returnLog()
     */
    @Override
    public Map<Integer, String> returnLog() {
	Map<Integer, String> log = new HashMap<Integer, String>();
	log.put(0, "figure0");
	log.put(1, "figure");
	return log;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameLog#returnThiefLog()
     */
    @Override
    public Map<Integer, String> returnThiefLog() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameLog#updateThiefLog(java.lang.String)
     */
    @Override
    public void updateThiefLog(String value) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameLog#clearLog()
     */
    @Override
    public void clearLog() {
	// TODO Auto-generated method stub

    }

}
