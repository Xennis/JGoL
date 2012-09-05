package events.gameview;

import events.IAEvent;
import events.IEventGenerator;
import gameview.sView.ISPlayer;

/**
 * Interface for {@link events.gameview.GVEventListener}.
 * 
 * @author Fabian R.
 */
public interface IGVEventGenerator extends IEventGenerator<GVEventListener> {

    /**
     * Fire a {@link events.gameview.global.EEndGame} event.
     * 
     * @see events.gameview.global.EEndGame
     * @param source
     *            Event source
     */
    public abstract void fireEndGameEvent(Object source);

    /**
     * Fire a {@link events.IAEvent}.
     * 
     * @param event Event to fire
     */
    public abstract void fireEvent(IAEvent event);

    /**
     * Fire a {@link events.gameview.startState.EPlayerChosePlayerCount} event.
     * 
     * @see events.gameview.startState.EPlayerChosePlayerCount
     * @param source
     *            Event source
     * @param playerCount
     *            Number of players
     * @param specialTicketArray
     *            Number of special tickets
     */
    public abstract void fireNewChosePlayerCountEvent(Object source,
	    int playerCount, Integer[] specialTicketArray);

    /**
     * Fire a {@link events.gameview.global.ENewGame} event.
     * 
     * @see events.gameview.global.ENewGame
     * @param source
     *            Event source
     */
    public abstract void fireNewGameEvent(Object source);

    /**
     * Fire a {@link events.gameview.startState.EPlayerRequestedMapData} event.
     * 
     * @see events.gameview.startState.EPlayerRequestedMapData
     * @param source
     *            Event source
     * @param mapName
     *            Name of requested map data
     */
    public abstract void fireNewPlayerRequestedMapData(Object source,
	    String mapName);

    /**
     * Fire a {@link events.gameview.startState.EPlayerSetProperties} event.
     * 
     * @see events.gameview.startState.EPlayerSetProperties
     * @param source
     *            Event source
     * @param sPlayer
     *            Player configurations
     */
    public abstract void fireNewPlayerSetPropertiesEvent(Object source,
	    ISPlayer sPlayer);

    /**
     * Fire a {@link events.gameview.startState.EPlayerSwitchedToNewGameView}
     * event.
     * 
     * @see events.gameview.startState.EPlayerSwitchedToNewGameView
     * @param source
     *            Event source
     */
    public abstract void fireNewPlayerSwitchedToNGV(Object source);

    /**
     * Fire a {@link events.gameview.global.ERequestClose} event.
     * 
     * @see events.gameview.global.ERequestClose
     * @param source
     *            Event source
     */
    public abstract void fireNewRequestCloseEvent(Object source);

    /**
     * Fire a {@link events.gameview.global.ERequestedLanguageSwitch} event.
     * 
     * @see events.gameview.global.ERequestedLanguageSwitch
     * @param source
     *            Event source
     * @param languageName
     *            Name of the new language
     */
    public abstract void fireNewRequestedLanguageSwitch(Object source,
	    String languageName);

    /**
     * Fire a {@link events.gameview.global.ESwitchGameState} event.
     * 
     * @see events.gameview.global.ESwitchGameState
     * @param source
     *            Event source
     * @param newState
     *            New gameState as integer
     */
    public abstract void fireNewSwitchStateEvent(Object source, int newState);

    /**
     * Fire a {@link events.gameview.gameState.EPlayerRequestedMove} event.
     * 
     * @see events.gameview.gameState.EPlayerRequestedMove
     * @param source
     *            Event source
     * @param ticketType
     *            Ticket type
     * @param destination
     *            Destination station
     * @param usedDoubleTicket
     *            True, if used double ticket
     */
    public abstract void firePlayerRequestedMove(Object source,
	    String ticketType, String destination, boolean usedDoubleTicket);

    /**
     * Fire a {@link events.gameview.gameState.EPlayerSurrender} event.
     * 
     * @see events.gameview.gameState.EPlayerSurrender
     * @param source
     *            Event source
     */
    public abstract void firePlayerSurrenderEvent(Object source);

    /**
     * Fire a {@link events.gameview.global.EPossibleThief} event.
     * 
     * @see events.gameview.global.EPossibleThief
     * @param source
     *            Event source
     * @param toggle
     *            True, if possible thief position should shown
     */
    public abstract void firePossibleThiefEvent(Object source, boolean toggle);

    /**
     * Fire a {@link events.gameview.global.EStartGame} event.
     * 
     * @see events.gameview.global.EStartGame
     * @param source
     *            Event source
     */
    public abstract void fireStartGameEvent(Object source);

}