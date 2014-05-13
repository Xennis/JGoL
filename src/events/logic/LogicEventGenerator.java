package events.logic;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import logic.figure.IAFigure;
import logic.player.IAPlayer;

import core.IConfig;

import events.EventConstants;
import events.EventGenerator;
import events.IAEvent;
import events.gameview.global.EPossibleThief;
import events.logic.endState.EPlayerWon;
import events.logic.gameState.EFigureKilled;
import events.logic.gameState.EInitNewGame;
import events.logic.gameState.EInitNewGameDone;
import events.logic.gameState.EMoveDenied;
import events.logic.gameState.ENextFigure;
import events.logic.gameState.ENextRound;
import events.logic.gameState.EPlayerMoved;
import events.logic.gameState.INextFigureEventData;
import events.logic.global.ELanguageSwitched;
import events.logic.startState.EDataMapData;
import events.logic.startState.EDataMapNames;
import events.logic.startState.EDataPlayerSettingsPanel;
import gameview.IImagePanel;

/**
 * Generates all events that are send to the logic.
 * 
 * @author Jasper S.
 * @version 1.0
 * 
 */
public class LogicEventGenerator extends EventGenerator<LogicEventListener>
	implements ILogicEventGenerator {

    public LogicEventGenerator() {
	super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * events.logic.ILogicEventGenerator#fireDataPlayerSettingsPanelEvent(java
     * .lang.Object, int, int, java.util.List)
     */
    @Override
    public void fireDataPlayerSettingsPanelEvent(Object source,
	    int playerCount, int maxFigureCount, List<BufferedImage> figureIcons) {
	EDataPlayerSettingsPanel temp = new EDataPlayerSettingsPanel(source,
		EventConstants.DATA_PLAYER_SETTINGS_PANEL, playerCount,
		maxFigureCount, figureIcons);
	fireEvent(temp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see events.logic.IEventGenerator#fireEvent(events.IAEvent)
     */
    @Override
    public void fireEvent(IAEvent event) {
	for (Iterator<LogicEventListener> i = listeners.iterator(); i.hasNext();) {
	    i.next().logicEventHappend(event);
	}
    }

    /*
     * StartState (sorted by name)
     */

    /*
     * (non-Javadoc)
     * 
     * @see
     * events.logic.ILogicEventGenerator#fireInitNewGameDoneEvent(java.lang.
     * Object, java.util.List)
     */
    @Override
    public void fireInitNewGameDoneEvent(Object source,
	    List<Integer> figureQueue) {
	EInitNewGameDone temp = new EInitNewGameDone(source,
		EventConstants.INIT_NEW_GAME_DONE, figureQueue);
	fireEvent(temp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * events.logic.ILogicEventGenerator#fireInitNewGameEvent(java.lang.Object,
     * logic.player.IAPlayer, logic.figure.IAFigure)
     */
    @Override
    public void fireInitNewGameEvent(Object source, IAPlayer player,
	    IAFigure figure) {
	EInitNewGame temp = new EInitNewGame(source,
		EventConstants.INIT_NEW_GAME, player, figure);
	fireEvent(temp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * events.logic.ILogicEventGenerator#fireNewDataMapDataEvent(java.lang.Object
     * , core.IConfig, gameView.IImagePanel)
     */
    @Override
    public void fireNewDataMapDataEvent(Object source, IConfig config,
	    IImagePanel mapImage) {
	EDataMapData temp = new EDataMapData(source,
		EventConstants.DATA_MAP_DATA, config, mapImage);
	fireEvent(temp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * events.logic.ILogicEventGenerator#fireNewDataMapNamesEvent(java.lang.
     * Object, java.util.List)
     */
    @Override
    public void fireNewDataMapNamesEvent(Object source, List<String> mapNames) {
	EDataMapNames temp = new EDataMapNames(source,
		EventConstants.DATA_MAP_NAMES, mapNames);
	fireEvent(temp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * events.logic.ILogicEventGenerator#fireNewFigureKilledEvent(java.lang.
     * Object, int)
     */
    @Override
    public void fireNewFigureKilledEvent(Object source, int figureID) {
	EFigureKilled temp = new EFigureKilled(source,
		EventConstants.FIGURE_KILLED, figureID);
	fireEvent(temp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * events.logic.ILogicEventGenerator#fireNewLanguageSwitchedEvent(java.lang
     * .Object, java.util.Map)
     */
    @Override
    public void fireNewLanguageSwitchedEvent(Object source,
	    Map<String, String> language) {
	ELanguageSwitched temp = new ELanguageSwitched(source,
		EventConstants.LANGUAGE_SWITCHED, language);
	fireEvent(temp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * events.logic.ILogicEventGenerator#fireNewMoveDeniedEvent(java.lang.Object
     * )
     */
    @Override
    public void fireNewMoveDeniedEvent(Object source) {
	EMoveDenied temp = new EMoveDenied(source,
		EventConstants.MOVE_DENIED_EVENT);
	fireEvent(temp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * events.logic.ILogicEventGenerator#fireNextFigureEvent(java.lang.Object,
     * events.logic.gameState.INextFigureEventData)
     */
    @Override
    public void fireNextFigureEvent(Object source, INextFigureEventData data) {
	ENextFigure temp = new ENextFigure(source, data);
	fireEvent(temp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * events.logic.ILogicEventGenerator#fireNextRoundEvent(java.lang.Object,
     * int)
     */
    @Override
    public void fireNextRoundEvent(Object source, int number) {
	ENextRound temp = new ENextRound(source, number);
	fireEvent(temp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * events.logic.ILogicEventGenerator#firePlayerMovedEvent(java.lang.Object,
     * int, java.lang.String)
     */
    @Override
    public void firePlayerMovedEvent(Object source, String newPosition,
	    String ticketType) {
	EPlayerMoved temp = new EPlayerMoved(source,
		EventConstants.PLAYER_MOVED_EVENT, newPosition, ticketType);
	fireEvent(temp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * events.logic.ILogicEventGenerator#firePlayerWonEvent(java.lang.Object,
     * boolean, java.util.List, java.util.Map)
     */
    @Override
    public void firePlayerWonEvent(Object source, boolean policeIsWinner,
	    List<String> winningPlayers, Map<Integer, String> log) {
	EPlayerWon temp = new EPlayerWon(source, EventConstants.PLAYER_WON,
		policeIsWinner, winningPlayers, log);
	fireEvent(temp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * events.logic.ILogicEventGenerator#firePossibleThiefEvent(java.lang.Object
     * , events.gameView.global.EPossibleThief)
     */
    @Override
    public void firePossibleThiefEvent(Object source, EPossibleThief e) {
	fireEvent(e);
    }
}
