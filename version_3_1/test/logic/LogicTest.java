/**
 * 
 */
package logic;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import logic.figure.FigurePoliceNoTicketsStub;
import logic.figure.FigurePoliceStub;
import logic.figure.FigureThiefStub;
import logic.figure.IAFigure;
import events.EventConstants;
import events.IAEvent;
import events.gameview.GVEventGenerator;
import events.gameview.GVEventListener;
import events.gameview.gameState.EPlayerRequestedMove;
import events.gameview.gameState.EPlayerRequestedMoveStub;
import events.gameview.global.ENewGame;
import events.gameview.startState.EPlayerRequestedMapDataStub;
import events.gameview.startState.IEPlayerChosePlayerCount;
import events.gameview.startState.IEPlayerRequestedMapData;
import events.logic.LogicEventGenerator;
import events.logic.LogicEventListener;
import events.logic.gameState.IEPlayerMoved;
import events.logic.startState.EDataMapData;
import events.logic.startState.EDataMapNames;
import events.logic.startState.EDataPlayerSettingsPanel;
import events.startState.EPlayerChosePlayerCountStub;
import gameview.sView.ISPlayer;
import gameview.sView.SPlayerStubPolice;
import gameview.sView.SPlayerStubPoliceKi;
import gameview.sView.SPlayerStubThief;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import core.ConfigStub;
import core.IConfig;
import core.ILanguages;
import core.IRessourceCache;
import core.IStationMap;
import core.LanguagesStub;
import core.RessourceCacheStub;
import core.StationMapStub;
import core.msgpump.IMsgPump;
import core.msgpump.MsgPumpStub;

/**
 * @author Fabian
 * 
 */
public class LogicTest implements LogicEventListener, GVEventListener {

    private Logic testLogic;
    private IRessourceCache stubCache;
    private IMsgPump stubMsgPump;
    private ILanguages stubLang;
    private IConfig stubConfig;
    private ISPlayer stubSPlayerThief;
    private ISPlayer stubSPlayerPolice;
    private ISPlayer stubSPlayerPoliceKi;
    private IAEvent stubEvent;
    private IAFigure stubFigureThief;
    private IAFigure stubFigurePolice;
    private IAFigure stubFigurePoliceNoTickets;
    private IStationMap stubStationMap;
    private IEPlayerChosePlayerCount stubEPlayerCount;
    private IEPlayerRequestedMapData stubEMapData;
    private IActorController stubActorCon;

    private GVEventGenerator gameViewEg;
    private LogicEventGenerator logicEg;
    private boolean processGvPlayerChosePlayerCountOk;
    private boolean processGvPlayerRequestedMapDataOk;
    private boolean processGvPlayerSwitchedToNewGameViewOk;
    private boolean moveThief21Walk;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
	// Players and figures
	stubSPlayerThief = new SPlayerStubPolice();
	stubSPlayerPolice = new SPlayerStubThief();
	stubSPlayerPoliceKi = new SPlayerStubPoliceKi();
	stubFigurePolice = new FigurePoliceStub();
	stubFigurePoliceNoTickets = new FigurePoliceNoTicketsStub();
	stubFigureThief = new FigureThiefStub();
	// Evemts
	stubEvent = new EPlayerRequestedMoveStub();
	stubEPlayerCount = new EPlayerChosePlayerCountStub();
	stubEMapData = new EPlayerRequestedMapDataStub();
	// Core
	stubCache = new RessourceCacheStub();
	stubMsgPump = new MsgPumpStub();
	stubLang = new LanguagesStub();
	stubConfig = new ConfigStub();
	stubStationMap = new StationMapStub();
	// Ohter
	stubActorCon = new ActorControllerStub();

	gameViewEg = new GVEventGenerator();
	logicEg = new LogicEventGenerator();
	logicEg.addListener(this);
	testLogic = new Logic(stubCache, stubMsgPump, false);
	gameViewEg.addListener(testLogic);
	gameViewEg.addListener(this);
    }

    /**
     * Test method for {@link logic.Logic#init(java.util.Map, java.util.List)}.
     */
    @Test
    public void testInit() {
	testLogic.init(stubLang.getLanguage(null), stubLang.getLanguageNames());
	assertTrue(testLogic.initDone);
    }

    /**
     * Test method for {@link logic.Logic#init(java.util.Map, java.util.List)}.
     */
    @Test
    public void testInitDone() {
	testLogic.init(stubLang.getLanguage(null), stubLang.getLanguageNames());
	testLogic.init(stubLang.getLanguage(null), stubLang.getLanguageNames());
	assertTrue(testLogic.initDone);
    }

    /**
     * Test method for {@link logic.Logic#onRender()}.
     */
    @Test
    public void testOnRender() {
	testLogic.queueGVEvent(stubEvent);
	testLogic.processEvents();
	assertTrue(testLogic.eventList.isEmpty());
    }

    /**
     * Test method for {@link logic.Logic#queueGVEvent(events.IAEvent)}.
     */
    @Test
    public void testQueueGVEvent() {
	IAEvent expectedEvent = new ENewGame(this, 0);
	testLogic.queueGVEvent(expectedEvent);
	IAEvent acutalEvent = testLogic.eventList.get(0);
	assertEquals(expectedEvent, acutalEvent);
    }

    /**
     * Test method for {@link logic.Logic#destroyCurrentGameData()}.
     */
    @Test
    public void testDestroyCurrentGameData() {
	testLogic.destroyCurrentGameData();
	assertNull(testLogic.currentFigure);
    }

    /**
     * Test method for
     * {@link logic.Logic#processGlobalNewGame(events.gameview.global.ENewGame)}
     * )}.
     */
    @Test
    public void testProcessGlobalNewGame() {
	testLogic.logicEG = logicEg;
	gameViewEg.fireNewGameEvent(this);
	assertNull(testLogic.currentFigure);
    }

    /**
     * Test method for
     * {@link logic.Logic#processGvPlayerChosePlayerCount(events.gameview.startState.EPlayerChosePlayerCount)}
     * .
     */
    @Test
    public void testProcessGvPlayerChosePlayerCount() {
	testLogic.init(stubLang.getLanguage(null), stubLang.getLanguageNames());
	testLogic.logicEG = logicEg;
	gameViewEg.fireNewChosePlayerCountEvent(this,
		stubEPlayerCount.getPlayerCount(),
		stubEPlayerCount.getSpecialTicketArray());
	assertTrue(processGvPlayerChosePlayerCountOk);
    }

    /**
     * Test method for
     * {@link logic.Logic#processGvPlayerRequestedMapData(events.gameview.startState.EPlayerRequestedMapData)}
     * .
     */
    @Test
    public void testProcessGvPlayerRequestedMapData() {
	testLogic.init(stubLang.getLanguage(null), stubLang.getLanguageNames());
	testLogic.logicEG = logicEg;
	gameViewEg.fireNewPlayerRequestedMapData(this,
		stubEMapData.getMapName());
	assertTrue(processGvPlayerRequestedMapDataOk);
    }

    /**
     * Test method for
     * {@link logic.Logic#processGvPlayerSetProperties(events.gameview.startState.EPlayerSetProperties)}
     * .
     * 
     * Scenario: Add a ki police player
     */
    @Test
    public void testProcessGvPlayerSetProperties() {
	testLogic.init(stubLang.getLanguage(null), stubLang.getLanguageNames());
	testLogic.logicEG = logicEg;
	gameViewEg.fireNewPlayerRequestedMapData(this,
		stubEMapData.getMapName());
	gameViewEg.fireNewPlayerSetPropertiesEvent(this, stubSPlayerPoliceKi);
	assertSame(1, testLogic.actorCont.getNumPlayer());
    }

    /**
     * Test method for
     * {@link logic.Logic#processGvPlayerSetProperties(events.gameview.startState.EPlayerSetProperties)}
     * .
     * 
     * Scenario: Add chosen a players
     */
    @Test
    public void testProcessGvPlayerSetPropertiesChosenPlayer() {
	testLogic.logicEG = logicEg;
	gameViewEg.fireNewPlayerRequestedMapData(this,
		stubEMapData.getMapName());
	// choose 2 players
	gameViewEg.fireNewChosePlayerCountEvent(this,
		stubEPlayerCount.getPlayerCount(),
		stubEPlayerCount.getSpecialTicketArray());
	// add 2 players
	gameViewEg.fireNewPlayerSetPropertiesEvent(this, stubSPlayerThief);
	gameViewEg.fireNewPlayerSetPropertiesEvent(this, stubSPlayerPolice);
	// check 2 players were added
	assertSame(2, testLogic.actorCont.getNumPlayer());
    }

    /**
     * Test method for
     * {@link logic.Logic#processGvPlayerSwitchedToNewGameView(events.gameview.startState.EPlayerSwitchedToNewGameView)}
     * .
     */
    @Test
    public void testProcessGvPlayerSwitchedToNewGameView() {
	testLogic.init(stubLang.getLanguage(null), stubLang.getLanguageNames());
	testLogic.logicEG = logicEg;
	gameViewEg.fireNewPlayerSwitchedToNGV(this);
	assertTrue(processGvPlayerSwitchedToNewGameViewOk);
    }

    /**
     * Test method for
     * {@link logic.Logic#processGvPlayerRequestedMove(events.gameview.gameState.EPlayerRequestedMove)}
     * .
     * 
     * Scenario: thief figure moves to existing point with a false ticket for
     * this ways.
     */
    @Test
    public void testCheckMoveIsAllowedNoTicket() {
	((ActorController) testLogic.actorCont).playerList = stubActorCon
		.getPlayerList();
	((ActorController) testLogic.actorCont).figureList = stubActorCon
		.getFigureList();
	testLogic.links = stubStationMap;

	testLogic.nextMove();

	EPlayerRequestedMove e = new EPlayerRequestedMove(this, 0, "noTicket",
		"21", false);
	assertFalse(testLogic.checkMoveIsAllowed(e));
    }

    /**
     * Test method for
     * {@link logic.Logic#processGvPlayerRequestedMove(events.gameview.gameState.EPlayerRequestedMove)}
     * .
     * 
     * Scenario: thief figure moves to a not reachable point.
     */
    @Test
    public void testCheckMoveIsAllowedNotReachablePoint() {
	((ActorController) testLogic.actorCont).playerList = stubActorCon
		.getPlayerList();
	((ActorController) testLogic.actorCont).figureList = stubActorCon
		.getFigureList();
	testLogic.links = stubStationMap;

	testLogic.nextMove();

	EPlayerRequestedMove e = new EPlayerRequestedMove(this, 0, "walk",
		"-1", false);
	assertFalse(testLogic.checkMoveIsAllowed(e));
    }

    /**
     * Test method for
     * {@link logic.Logic#processGvPlayerRequestedMove(events.gameview.gameState.EPlayerRequestedMove)}
     * .
     * 
     * Scenario: thief figure can not move (no tickets).
     */
    @Test
    public void testCheckMoveIsAllowedCanNotMove() {
	testLogic.currentReachableLinks.clear();
	EPlayerRequestedMove e = new EPlayerRequestedMove(this, 0, "walk",
		"22", false);
	assertFalse(testLogic.checkMoveIsAllowed(e));
    }

    /**
     * Test method for
     * {@link logic.Logic#processGvPlayerRequestedMove(events.gameview.gameState.EPlayerRequestedMove)}
     * .
     * 
     * Scenario: police figure tries to use a double ticket, but has no double
     * ticket.
     */
    @Test
    public void testCheckMoveIsAllowedNoDouble() {
	((ActorController) testLogic.actorCont).playerList = stubActorCon
		.getPlayerList();
	((ActorController) testLogic.actorCont).figureList = stubActorCon
		.getFigureList();
	testLogic.links = stubStationMap;
	testLogic.nextMove();
	testLogic.nextMove();

	EPlayerRequestedMove e = new EPlayerRequestedMove(this, 0, "walk",
		"22", true);
	assertFalse(testLogic.checkMoveIsAllowed(e));
    }

    /**
     * Test method for
     * {@link logic.Logic#processGvPlayerRequestedMove(events.gameview.gameState.EPlayerRequestedMove)}
     * .
     * 
     * Scenario: thief figure moves without collision.
     */
    @Test
    public void testProcessGvPlayerRequestedMoveThief() {
	testLogic.logicEG = logicEg;
	// gameViewEg.fireNewPlayerRequestedMapData(this,
	// stubEMapData.getMapName());
	// choose 2 players
	// gameViewEg.fireNewChosePlayerCountEvent(this,
	// stubEPlayerCount.getPlayerCount(),
	// stubEPlayerCount.getSpecialTicketArray());
	// add 2 players
	// gameViewEg.fireNewPlayerSetPropertiesEvent(this, stubSPlayerThief);
	// gameViewEg.fireNewPlayerSetPropertiesEvent(this, stubSPlayerPolice);

	// testLogic.nextMove();

	((ActorController) testLogic.actorCont).playerList = stubActorCon
		.getPlayerList();
	((ActorController) testLogic.actorCont).figureList = stubActorCon
		.getFigureList();
	testLogic.currentFigure = stubActorCon.getCurrentFigure();
	testLogic.links = stubStationMap;

	testLogic.nextMove();
	testLogic.processGvPlayerRequestedMove(new EPlayerRequestedMove(this,
		EventConstants.PLAYER_REQUESTED_MOVE, "walk", "21", true));

	assertTrue(moveThief21Walk);
    }

    /**
     * Test method for {@link logic.Logic#checkEndGame()}.
     * 
     * Scenario: thief figure list is empty. (surrender or caught by police)
     */
    @Test
    public void testCheckEndGameNoThieveFigures() {
	testLogic.links = stubStationMap;
	List<IAFigure> figureList = new LinkedList<IAFigure>();
	figureList.add(stubFigurePolice);
	((ActorController) testLogic.actorCont).figureList = figureList;
	assertTrue(testLogic.checkEndGame());
    }

    /**
     * Test method for {@link logic.Logic#checkEndGame()}.
     * 
     * Scenario: police figure list is empty. (surrender)
     */
    @Test
    public void testCheckEndGameNoPoliceFigures() {
	testLogic.links = stubStationMap;
	List<IAFigure> figureList = new LinkedList<IAFigure>();
	figureList.add(stubFigureThief);
	((ActorController) testLogic.actorCont).figureList = figureList;
	assertTrue(testLogic.checkEndGame());
    }

    /**
     * Test method for {@link logic.Logic#checkEndGame()}.
     * 
     * Scenario: 1 x thief figure + 1 x police figure can not move.
     */
    @Test
    public void testCheckEndGamePoliceCanNotMove() {
	testLogic.links = stubStationMap;
	List<IAFigure> figureList = new LinkedList<IAFigure>();
	figureList.add(stubFigureThief);
	figureList.add(stubFigurePoliceNoTickets);
	((ActorController) testLogic.actorCont).figureList = figureList;
	assertTrue(testLogic.checkEndGame());
    }

    /**
     * Test method for {@link logic.Logic#checkEndGame()}.
     * 
     * Scenario: 1 x thief figure + 1 x police figure.
     */
    @Test
    public void testCheckEndGameFalse() {
	testLogic.links = stubStationMap;
	List<IAFigure> figureList = new LinkedList<IAFigure>();
	figureList.add(stubFigureThief);
	figureList.add(stubFigurePolice);
	((ActorController) testLogic.actorCont).figureList = figureList;
	assertFalse(testLogic.checkEndGame());
    }

    /**
     * Test method for {@link logic.Logic#checkEndGame()}.
     */
    @Test
    public void testCheckEndGameException() {
	List<IAFigure> figureList = new LinkedList<IAFigure>();
	figureList.add(stubFigureThief);
	figureList.add(stubFigurePolice);
	((ActorController) testLogic.actorCont).figureList = figureList;
	assertFalse(testLogic.checkEndGame());
    }

    /**
     * Test method for
     * {@link logic.Logic#processGvPlayerSurrender(events.gameview.gameState.EPlayerSurrender)}
     * .
     */
    @Test
    public void testProcessGvPlayerSurrender() {
	gameViewEg.fireNewPlayerRequestedMapData(this,
		stubEMapData.getMapName());
	// choose 2 players
	gameViewEg.fireNewChosePlayerCountEvent(this,
		stubEPlayerCount.getPlayerCount(),
		stubEPlayerCount.getSpecialTicketArray());
	// add 2 players
	gameViewEg.fireNewPlayerSetPropertiesEvent(this, stubSPlayerThief);
	gameViewEg.fireNewPlayerSetPropertiesEvent(this, stubSPlayerPolice);

	// thief surrender
	gameViewEg.firePlayerSurrenderEvent(this);
	assertTrue(testLogic.actorCont.getFigureThiefList().isEmpty());
    }

    @Override
    public void gVEventHappend(IAEvent e) {
	// TODO Auto-generated method stub

    }
    
    /*
     * (non-Javadoc)
     * 
     * @see events.logic.LogicEventListener#logicEventHappend(events.IAEvent)
     */
    @Override
    public void logicEventHappend(IAEvent e) {
		if (e.getEventType() < EventConstants.LOGIC_END_START_STATE) {
			logicEventEndState(e);
		} else if (e.getEventType() < EventConstants.LOGIC_END_GAME_STATE) {
			if (e.getEventType() == EventConstants.PLAYER_MOVED_EVENT) {
				IEPlayerMoved ee = (IEPlayerMoved) e;
				if (ee.getNewPosition().equals("21")
						&& ee.getTicketType().equals("walk")) {
					moveThief21Walk = true;
				}
			}
		}
	}
    
    /**
     * Test logic events from end state
     * 
     * @param e logic events from end state
     */
    public void logicEventEndState(IAEvent e) {
		if (e.getEventType() == EventConstants.DATA_PLAYER_SETTINGS_PANEL) {
			EDataPlayerSettingsPanel ee = (EDataPlayerSettingsPanel) e;
			if (ee.getPlayerCount() == 2
					&& ee.getMaxFigureCount() == stubConfig.getStartat()
							.size()) {
				processGvPlayerChosePlayerCountOk = true;
			}
		} else if (e.getEventType() == EventConstants.DATA_MAP_DATA) {
			EDataMapData ee = (EDataMapData) e;
			if (ee.getConfig().getStartat().equals(stubConfig.getStartat())
					&& ee.getMapImage() != null) {
				processGvPlayerRequestedMapDataOk = true;
			}
		} else if (e.getEventType() == EventConstants.DATA_MAP_NAMES) {
			EDataMapNames ee = (EDataMapNames) e;
			if (ee.getMapNames().equals(stubCache.getMapNames())) {
				processGvPlayerSwitchedToNewGameViewOk = true;
			}
		}

    }

}
