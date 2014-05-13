/**
 * 
 */
package events.logic;

import static org.junit.Assert.*;

import logic.ActorControllerStub;
import logic.GameLogStub;
import logic.IActorController;
import logic.IGameLog;
import logic.figure.FigureThiefStub;
import logic.figure.IAFigure;
import logic.player.IAPlayer;
import logic.player.PlayerThiefStub;

import org.junit.Before;
import org.junit.Test;
import static events.EventConstants.*;

import core.ConfigStub;
import core.IConfig;
import core.ILanguages;
import core.IStation;
import core.IRessourceCache;
import core.LanguagesStub;
import core.StationStub;
import core.RessourceCacheStub;
import events.IAEvent;
import events.logic.endState.EPlayerWon;
import events.logic.gameState.EFigureKilled;
import events.logic.gameState.EInitNewGame;
import events.logic.gameState.EInitNewGameDone;
import events.logic.gameState.ENextFigure;
import events.logic.gameState.ENextRound;
import events.logic.gameState.IEPlayerMoved;
import events.logic.gameState.INextFigureEventData;
import events.logic.gameState.NextFigureEventDataStub;
import events.logic.global.ELanguageSwitched;
import events.logic.startState.EDataMapData;
import events.logic.startState.EDataMapNames;
import events.logic.startState.EDataPlayerSettingsPanel;
import gameview.IImagePanel;
import gameview.ImagePanelStub;

/**
 * @author Fabian
 * 
 */
public class LogicEventGeneratorTest implements LogicEventListener {

    private LogicEventGenerator logicEG;
    private boolean languageSwichtedOK, dataMapNamesOk, playerCountOk,
	    initNewGameOk, initNewGameDoneOk, nextFoundOK, playerMovedOk,
	    nextFigureOk, moveDeniedOk, figureKilledOk, dataMapDataOk,
	    playerWonOk;
    // Stubs
    private ILanguages stubLanguages;
    private IRessourceCache stubCache;
    private IAPlayer stubPlayerThief;
    private IAFigure stubFigureThief;
    private IStation stubLink;
    private IConfig stubConfig;
    private IImagePanel stubImagePanel;
    private IActorController stubActorController;
    private INextFigureEventData stubFigureEventData;
    private IGameLog stubGameLog;

    @Before
    public void init() {
	logicEG = new LogicEventGenerator();
	logicEG.addListener(this);
	// Stubs
	stubLanguages = new LanguagesStub();
	stubCache = new RessourceCacheStub();
	stubPlayerThief = new PlayerThiefStub();
	stubFigureThief = new FigureThiefStub();
	stubLink = new StationStub();
	stubConfig = new ConfigStub();
	stubImagePanel = new ImagePanelStub();
	stubActorController = new ActorControllerStub();
	stubFigureEventData = new NextFigureEventDataStub();
	stubGameLog = new GameLogStub();
    }

    /**
     * Test method for
     * {@link events.logic.LogicEventGenerator#fireNewLanguageSwitchedEvent(java.lang.Object, java.util.Map)}
     * .
     */
    @Test
    public void testFireNewLanguageSwitchedEvent() {
	logicEG.fireNewLanguageSwitchedEvent(this,
		stubLanguages.getLanguage(null));
	assertTrue(languageSwichtedOK);
    }

    /**
     * Test method for
     * {@link events.logic.LogicEventGenerator#fireNewDataMapDataEvent(java.lang.Object , core.IConfig, gameview.IImagePanel)}
     * .
     */
    @Test
    public void testFireNewDataMapDataEvent() {
	logicEG.fireNewDataMapDataEvent(this, stubConfig, stubImagePanel);
	assertTrue(dataMapDataOk);
    }

    /**
     * Test method for
     * {@link events.logic.LogicEventGenerator#fireNewDataMapNamesEvent(java.lang.Object, java.util.List)}
     * .
     */
    @Test
    public void testFireNewDataMapNamesEvent() {
	logicEG.fireNewDataMapNamesEvent(this, stubCache.getMapNames());
	assertTrue(dataMapNamesOk);
    }

    /**
     * Test method for
     * {@link events.logic.LogicEventGenerator#fireDataPlayerSettingsPanelEvent(java.lang.Object, int, int, java.util.List)}
     * .
     */
    @Test
    public void testfireDataPlayerSettingsPanelEvent() {
	logicEG.fireDataPlayerSettingsPanelEvent(this, 99, 1000,
		stubCache.getFigureIcons());
	assertTrue(playerCountOk);
    }

    /**
     * Test method for
     * {@link events.logic.LogicEventGenerator#fireInitNewGameEvent(java.lang.Object, logic.player.IAPlayer, logic.figure.IAFigure)}
     * .
     */
    @Test
    public void testFireInitNewGameEvent() {
	logicEG.fireInitNewGameEvent(this, stubPlayerThief, stubFigureThief);
	assertTrue(initNewGameOk);
    }

    /**
     * Test method for
     * {@link events.logic.LogicEventGenerator#fireInitNewGameDoneEvent(java.lang.Object, java.util.List)}
     * .
     */
    @Test
    public void testFireInitNewGameDoneEvent() {
	logicEG.fireInitNewGameDoneEvent(this,
		stubActorController.getFigureQueue());
	assertTrue(initNewGameDoneOk);
    }

    /**
     * Test method for
     * {@link events.logic.LogicEventGenerator#fireNextRoundEvent(java.lang.Object, int)}
     * .
     */
    @Test
    public void testFireNextRoundEvent() {
	logicEG.fireNextRoundEvent(this, 5);
	assertTrue(nextFoundOK);
    }

    /**
     * Test method for
     * {@link events.logic.LogicEventGenerator#firePlayerMovedEvent(java.lang.Object, java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testFirePlayerMovedEvent() {
	logicEG.firePlayerMovedEvent(this, "22", "walk");
	assertTrue(playerMovedOk);
    }

    /**
     * Test method for
     * {@link events.logic.LogicEventGenerator#fireNextFigureEvent(java.lang.Object, events.logic.gameState.INextFigureEventData)}
     * .
     */
    @Test
    public void testFireNextFigureEvent() {
	logicEG.fireNextFigureEvent(this, stubFigureEventData);
	assertTrue(nextFigureOk);
    }

    /**
     * Test method for
     * {@link events.logic.LogicEventGenerator#fireNewMoveDeniedEvent(java.lang.Object)}
     * .
     */
    @Test
    public void testFireNewMoveDeniedEvent() {
	logicEG.fireNewMoveDeniedEvent(this);
	assertTrue(moveDeniedOk);
    }

    /**
     * Test method for
     * {@link events.logic.LogicEventGenerator#fireNewFigureKilledEvent(java.lang.Object, int)}
     * .
     */
    @Test
    public void testFireNewFigureKilledEvent() {
	logicEG.fireNewFigureKilledEvent(this, 3);
	assertTrue(figureKilledOk);
    }

    /**
     * Test method for
     * {@link events.logic.LogicEventGenerator#firePlayerWonEvent(java.lang.Object, boolean, java.util.List, java.util.Map)}
     * .
     */
    @Test
    public void testFirePlayerWonEvent() {
	logicEG.firePlayerWonEvent(this, true,
		stubActorController.getPlayerPoliceNameList(),
		stubGameLog.returnLog());
	assertTrue(playerWonOk);
    }

    /*
     * (non-Javadoc)
     * 
     * @see events.logic.LogicEventListener#logicEventHappend(events.IAEvent)
     */
    @Override
    public void logicEventHappend(IAEvent e) {
	if (e.getEventType() < LOGIC_END_GLOBAL) {
	    logicEventGlobalHappend(e);
	} else if (e.getEventType() < LOGIC_END_START_STATE) {
	    logicEventStartStateHappend(e);
	} else if (e.getEventType() < LOGIC_END_GAME_STATE_MAIN) {
	    logicEventGameStateMainHappend(e);
	} else if (e.getEventType() < LOGIC_END_GAME_STATE) {
		logicEventGameStatePlayingHappend(e);
	} else if (e.getEventType() < LOGIC_END_END_STATE) {
	    logicEventEndStateHappend(e);
	}
    }

    /**
     * Test endState logic events.
     * 
     * @param e
     *            Event
     */
    private void logicEventEndStateHappend(IAEvent e) {
	if (e.getEventType() == PLAYER_WON) {
	    EPlayerWon ee = (EPlayerWon) e;
	    if (ee.policeIsWinner()
		    && ee.getWinningPlayers().equals(
			    stubActorController.getPlayerPoliceNameList())
		    && ee.getLog().equals(stubGameLog.returnLog())) {
		playerWonOk = true;
	    }
	}
    }

    /**
     * Test gameState logic main events.
     * 
     * @param e
     *            The gameState logic event
     */
    private void logicEventGameStateMainHappend(IAEvent e) {
	if (e.getEventType() == INIT_NEW_GAME) {
		logicEventGameStateInitHappend(e);
	}
	if (e.getEventType() == INIT_NEW_GAME_DONE) {
	    EInitNewGameDone ee = (EInitNewGameDone) e;
	    if (ee.getFigureQueue()
		    .equals(stubActorController.getFigureQueue())) {
		initNewGameDoneOk = true;
	    }
	}

	if (e.getEventType() == PLAYER_MOVED_EVENT) {
	    IEPlayerMoved ee = (IEPlayerMoved) e;
	    if (ee.getNewPosition().equals("22")
		    && ee.getTicketType().equals("walk")) {
		playerMovedOk = true;
	    }
	}

    }

    /**
     * Test gameState logic playing events.
     * 
     * @param e
     *            Event
     */
    private void logicEventGameStatePlayingHappend(IAEvent e) {
    	if (e.getEventType() == NEXT_ROUND) {
    	    ENextRound ee = (ENextRound) e;
    	    if (ee.getNumer() == 5) {
    		nextFoundOK = true;
    	    }
    	}
    	else if (e.getEventType() == NEXT_FIGURE_EVENT) {
    	    ENextFigure ee = (ENextFigure) e;
    	    if (ee.getFigureId() == stubFigureEventData.getFigure().getId()
    		    && ee.getPosition().equals(
    			    stubFigureEventData.getFigure().getPosition())
    		    && ee.getReachableLinks().equals(
    			    stubLink.getReachableLinks(null))) {
    		nextFigureOk = true;
    	    }
    	}
    	else if (e.getEventType() == MOVE_DENIED_EVENT) {
    	    moveDeniedOk = true;
    	}
    	else if (e.getEventType() == FIGURE_KILLED) {
    	    EFigureKilled ee = (EFigureKilled) e;
    	    if (ee.getFigureID() == 3) {
    		figureKilledOk = true;
    	    }
    	}
    	
    }
    
    /**
     * Test gameState init new game event. Just for Metrics.
     * 
     * @param e
     *            The gameState logic event
     */
    private void logicEventGameStateInitHappend(IAEvent e) {
	    EInitNewGame ee = (EInitNewGame) e;
	    if (ee.getId() == stubPlayerThief.getId()
		    && ee.getName().equals(stubPlayerThief.getName())
		    && ee.getColor().equals(stubPlayerThief.getColor())
		    && ee.getType() == stubPlayerThief.getType()) {
		if (ee.getFigureId() == stubFigureThief.getId()
			&& ee.getFigurePosition().equals(
				stubFigureThief.getPosition())
			&& ee.getFigureTokens().equals(
				stubFigureThief.getTicketsRemaining())) {
		    initNewGameOk = true;
		}

	    }
    }
    
    /**
     * Test startState logic events.
     * 
     * @param e
     *            Event
     */
    private void logicEventStartStateHappend(IAEvent e) {
	if (e.getEventType() == DATA_MAP_DATA) {
	    EDataMapData ee = (EDataMapData) e;
	    if (ee.getConfig() == stubConfig
		    && ee.getMapImage() == stubImagePanel) {
		dataMapDataOk = true;
	    }
	}
	if (e.getEventType() == DATA_MAP_NAMES) {
	    EDataMapNames ee = (EDataMapNames) e;
	    if (ee.getMapNames().equals(stubCache.getMapNames())) {
		dataMapNamesOk = true;
	    }
	}
	if (e.getEventType() == DATA_PLAYER_SETTINGS_PANEL) {
	    EDataPlayerSettingsPanel ee = (EDataPlayerSettingsPanel) e;
	    if (ee.getPlayerCount() == 99 && ee.getMaxFigureCount() == 1000
		    && !ee.getFigureIcons().isEmpty()) {
		playerCountOk = true;
	    }
	}
    }

    /**
     * Test global logic events.
     * 
     * @param e
     *            Event
     */
    private void logicEventGlobalHappend(IAEvent e) {
	if (e.getEventType() == LANGUAGE_SWITCHED) {
	    ELanguageSwitched ee = (ELanguageSwitched) e;
	    if (ee.getLanguage().equals(stubLanguages.getLanguage(null))) {
		languageSwichtedOK = true;
	    }
	}
    }

}
