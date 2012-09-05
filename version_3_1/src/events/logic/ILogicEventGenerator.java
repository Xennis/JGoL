package events.logic;

import events.IAEvent;
import events.IEventGenerator;
import events.gameview.global.EPossibleThief;
import events.logic.gameState.INextFigureEventData;
import gameview.IImagePanel;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import logic.figure.IAFigure;
import logic.player.IAPlayer;
import core.IConfig;

/**
 * Interface to the LogicEventGenerator
 * 
 * @author Raphael A.
 * @version 1.0
 */
public interface ILogicEventGenerator extends
	IEventGenerator<LogicEventListener> {

	/**
	 * fires the event
	 * 
	 * @param event A event to fire
	 */
    public abstract void fireEvent(IAEvent event);


	/**
	 * fires the NewLanguageSwitchedEvent which contains the language map
	 * 
	 * @param source
	 *            Event source
	 * @param language
	 *            Map with language data
	 */
    public abstract void fireNewLanguageSwitchedEvent(Object source,
	    Map<String, String> language);

	/**
	 * fires the NewDataMapDataEvent which contains the confic and the map data
	 * so the map preview works
	 * 
	 * @param source
	 *            Event source
	 * @param config
	 *            Configuration for this map
	 * @param mapImage
	 *            Image of the map
	 */
    public abstract void fireNewDataMapDataEvent(Object source, IConfig config,
	    IImagePanel mapImage);

	/**
	 * fires the NewDataMapNamesEvent which contains a List which is filled with
	 * the mapNames
	 * 
	 * @param source
	 *            Event source
	 * @param mapNames
	 *            List with all map names
	 */
    public abstract void fireNewDataMapNamesEvent(Object source,
	    List<String> mapNames);
    
	/**
	 * fires the DataPlayerSettingsPanelEvent which contains the chosen player
	 * count, the figure images and the max figure count
	 * 
	 * @param source
	 *            Event source
	 * @param playerCount
	 *            Numer of players
	 * @param maxFigureCount
	 *            Maximum of figures
	 * @param figureIcons
	 *            List with all figure icons
	 */
    public abstract void fireDataPlayerSettingsPanelEvent(Object source,
	    int playerCount, int maxFigureCount, List<BufferedImage> figureIcons);

	/**
	 * fires the InitNewGameEvent which contains the player object and the
	 * figure object
	 * 
	 * @param source
	 *            Event source
	 * @param player
	 *            A player
	 * @param figure
	 *            A figure
	 */
    public abstract void fireInitNewGameEvent(Object source, IAPlayer player,
	    IAFigure figure);

	/**
	 * fires the InitNewGameDoneEvent which contains the figureQueue
	 * 
	 * @param source
	 *            Event source
	 * @param figureQueue
	 *            Order of figures
	 */
    public abstract void fireInitNewGameDoneEvent(Object source,
	    List<Integer> figureQueue);

    /**
     * Fired by logic, if next round.
     * 
     * @param source Event source
     * @param number Round number
     */
    public abstract void fireNextRoundEvent(Object source, int number);

	/**
	 * If a player moved, this event is fired.
	 * 
	 * @param source
	 *            Event source
	 * @param newPosition
	 *            New figure position
	 * @param ticketType
	 *            Used ticket types
	 */
    public abstract void firePlayerMovedEvent(Object source,
	    String newPosition, String ticketType);
    
	/**
	 * All information about the next figure.
	 * 
	 * @param source
	 *            Event data
	 * @param data
	 *            All data about the figure
	 */
    public abstract void fireNextFigureEvent(Object source,
	    INextFigureEventData data);

    /**
     * Fired by logic, if a figure moved was denied.
     * 
     * @param source Event source
     */
    public abstract void fireNewMoveDeniedEvent(Object source);

    /**
     * Fired by logic, if a figure was killed.
     * 
     * @param source Event source
     * @param figureID Figure id
     */
    public abstract void fireNewFigureKilledEvent(Object source, int figureID);

	/**
	 * Fire event with winning player informations.
	 * 
	 * @param source
	 *            Event source
	 * @param policeIsWinner
	 *            True, if police team won the game
	 * @param winningPlayers
	 *            List with all winning player names
	 * @param log
	 *            Game log
	 */
    public abstract void firePlayerWonEvent(Object source,
	    boolean policeIsWinner, List<String> winningPlayers,
	    Map<Integer, String> log);

    /**
     * Event for possible thief positions.
     * 
     * @param source Event source
     * @param e Event with possible thief position
     */
    public abstract void firePossibleThiefEvent(Object source, EPossibleThief e);

}