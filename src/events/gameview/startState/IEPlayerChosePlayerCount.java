package events.gameview.startState;

/**
 * Interface for {@link events.gameview.startState.EPlayerChosePlayerCount}.
 * 
 * @author Raphael A.
 * @version 2.0
 */
public interface IEPlayerChosePlayerCount {

    /**
     * Get number of players.
     * 
     * @return number of players
     */
    public abstract int getPlayerCount();

    /**
     * Get number of special tickets.
     * 
     * @return Array with number of special tickets
     */
    public abstract Integer[] getSpecialTicketArray();

}