/**
 * 
 */
package events.logic.endState;

import java.util.List;
import java.util.Map;

import events.AEvent;

/**
 * Delivers the winners and the log.
 * 
 * @author Fabian
 * @version 3.0
 */
public class EPlayerWon extends AEvent {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean policeIsWinner;
    private List<String> winningPlayers;
    private Map<Integer, String> log;

	/**
	 * Event to deliver the winners and the log to the ent state.
	 * 
	 * @param source
	 *            Event source
	 * @param eventType
	 *            Event id integer
	 * @param policeIsWinner
	 *            True, if police team is winner
	 * @param winningPlayers
	 *            List with all winning player names
	 * @param log
	 *            Log of the game
	 */
    public EPlayerWon(Object source, int eventType, boolean policeIsWinner,
	    List<String> winningPlayers, Map<Integer, String> log) {
	super(source, eventType);
	this.policeIsWinner = policeIsWinner;
	this.winningPlayers = winningPlayers;
	this.log = log;
    }

    /**
     * Check police team won the game.
     * 
     * @return True, if police team won the game
     */
    public boolean policeIsWinner() {
	return policeIsWinner;
    }

    /**
     * Get game log.
     * 
     * @return game log
     */
    public Map<Integer, String> getLog() {
	return log;
    }

    /**
     * Get {@link java.util.List} with all winning player names.
     * 
     * @return {@link java.util.List} with all winning player names
     */
    public List<String> getWinningPlayers() {
	return winningPlayers;
    }

}
