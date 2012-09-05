package events.gameview;

import static events.EventConstants.END_GAME_EVENT;
import static events.EventConstants.PLAYER_CHOSE_PLAYER_COUNT;
import static events.EventConstants.PLAYER_REQUESTED_MAP_DATA;
import static events.EventConstants.PLAYER_REQUESTED_MOVE;
import static events.EventConstants.PLAYER_SET_PROPERTIES;
import static events.EventConstants.PLAYER_SURRENDER;
import static events.EventConstants.PLAYER_SWITCHED_TO_NEWGAME_VIEW;
import static events.EventConstants.REQUESTED_CLOSE;
import static events.EventConstants.REQUESTED_LANGUAGE_SWITCH;
import static events.EventConstants.START_GAME_EVENT;
import static events.EventConstants.SWITCH_GAMESTATE_EVENT;
import static events.EventConstants.NEW_GAME_EVENT;
import static events.EventConstants.GAMEVIEW_END_GLOBAL;
import static events.EventConstants.GAMEVIEW_END_START_STATE;
import static events.EventConstants.GAMEVIEW_END_GAME_STATE;
import static events.EventConstants.POSSIBLE_THIEF_EVENT;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import events.IAEvent;
import events.gameview.gameState.EPlayerRequestedMove;
import events.gameview.global.EPossibleThief;
import events.gameview.global.ERequestedLanguageSwitch;
import events.gameview.startState.EPlayerSetProperties;
import events.gameview.startState.IEPlayerChosePlayerCount;
import events.gameview.startState.IEPlayerRequestedMapData;

import gameview.sView.ISPlayer;
import gameview.sView.SPlayerStubPolice;

/**
 * @author Jasper
 * 
 */
public class GVEventGeneratorTest implements GVEventListener {

    private IGVEventGenerator gVEG;
    private ISPlayer stubSPlayer;
    private boolean endGameEventOK, chosePlayerCountEventOK,
	    playerRequestedMapDataOK, playerSetPropertiesEventOK,
	    playerSwitchedtoNGVEventOK, requestCloseEventOK,
	    requestedLanguageSwitchEventOK, switchStateEventOK,
	    playerRequestedMoveEventOK, playerSurrenderEventOK,
	    startGameEventOk, newGameOk, possibleThiefOk;

    @Before
    public void init() {
	gVEG = new GVEventGenerator();
	gVEG.addListener(this);
	stubSPlayer = new SPlayerStubPolice();
    }

    /**
     * Test method for
     * {@link events.gameview.GVEventGenerator#fireNewSwitchStateEvent(java.lang.Object, int)}
     * .
     */
    @Test
    public void testFireNewSwitchStateEvent() {
	gVEG.fireNewSwitchStateEvent(this, Integer.MAX_VALUE);
	assertTrue(switchStateEventOK);
    }

    /**
     * Test method for
     * {@link events.gameview.GVEventGenerator#fireEndGameEvent(java.lang.Object)}
     * .
     */
    @Test
    public void testFireEndGameEvent() {
	gVEG.fireEndGameEvent(this);
	assertTrue(endGameEventOK);
    }

    /**
     * Test method for
     * {@link events.gameview.GVEventGenerator#fireNewGameEvent(java.lang.Object)}
     * .
     */
    @Test
    public void testFireNewGameEvent() {
	gVEG.fireNewGameEvent(this);
	assertTrue(newGameOk);
    }

    /**
     * Test method for
     * {@link events.gameview.GVEventGenerator#fireNewRequestCloseEvent(java.lang.Object)}
     * .
     */
    @Test
    public void testFireNewRequestCloseEvent() {
	gVEG.fireNewRequestCloseEvent(this);
	assertTrue(requestCloseEventOK);
    }

    /**
     * Test method for
     * {@link events.gameview.GVEventGenerator#fireNewRequestedLanguageSwitch(java.lang.Object, java.lang.String)}
     * .
     */
    @Test
    public void testFireNewRequestedLanguageSwitch() {
	gVEG.fireNewRequestedLanguageSwitch(this, "English");
	assertTrue(requestedLanguageSwitchEventOK);
    }

    /**
     * Test method for
     * {@link events.gameview.GVEventGenerator#fireStartGameEvent(java.lang.Object)}
     * .
     */
    @Test
    public void testFireStartGameEvent() {
	gVEG.fireStartGameEvent(this);
	assertTrue(startGameEventOk);
    }

    /**
     * Test method for
     * {@link events.gameview.GVEventGenerator#fireNewChosePlayerCountEvent(java.lang.Object, int, java.lang.Integer[])}
     * .
     */
    @Test
    public void testFireNewChosePlayerCountEvent() {
	gVEG.fireNewChosePlayerCountEvent(this, 1000, new Integer[4]);
	assertTrue(chosePlayerCountEventOK);
    }

    /**
     * Test method for
     * {@link events.gameview.GVEventGenerator#fireNewPlayerRequestedMapData(java.lang.Object, java.lang.String)}
     * .
     */
    @Test
    public void testFireNewPlayerRequestedMapData() {
	gVEG.fireNewPlayerRequestedMapData(this, "Gangs of Old York");
	assertTrue(playerRequestedMapDataOK);
    }

    /**
     * Test method for
     * {@link events.gameview.GVEventGenerator#fireNewPlayerSetPropertiesEvent(java.lang.Object, gameview.sView.ISPlayer)}
     * .
     */
    @Test
    public void testFireNewPlayerSetPropertiesEvent() {
	gVEG.fireNewPlayerSetPropertiesEvent(this, stubSPlayer);
	assertTrue(playerSetPropertiesEventOK);
    }

    /**
     * Test method for
     * {@link events.gameview.GVEventGenerator#fireNewPlayerSwitchedToNGV(java.lang.Object)}
     * .
     */
    @Test
    public void testFireNewPlayerSwitchedToNGV() {
	gVEG.fireNewPlayerSwitchedToNGV(this);
	assertTrue(playerSwitchedtoNGVEventOK);
    }

    /**
     * Test method for
     * {@link events.gameview.GVEventGenerator#firePlayerRequestedMove(java.lang.Object , java.lang.String, java.lang.String, boolean)}
     * .
     */
    @Test
    public void testFirePlayerRequestedMove() {
	gVEG.firePlayerRequestedMove(this, "Subway", "9000", true);
	assertTrue(playerRequestedMoveEventOK);
    }

    /**
     * Test method for
     * {@link events.gameview.GVEventGenerator#firePlayerSurrenderEvent(java.lang.Object)}
     * .
     */
    @Test
    public void testFirePlayerSurrenderEvent() {
	gVEG.firePlayerSurrenderEvent(this);
	assertTrue(playerSurrenderEventOK);
    }

    /**
     * Test method for
     * {@link events.gameview.GVEventGenerator#firePossibleThiefEvent(java.lang.Object , boolean)}
     * .
     */
    @Test
    public void testFirePossibleThiefEvent() {
	gVEG.firePossibleThiefEvent(this, true);
	assertTrue(possibleThiefOk);
    }

    /*
     * (non-Javadoc)
     * 
     * @see events.gameView.GVEventListener#gVEventHappend(events.IAEvent)
     */
    @Override
    public void gVEventHappend(IAEvent e) {
	if (e.getEventType() < GAMEVIEW_END_GLOBAL) {
	    gvGlobalEventHappend(e);
	} else if (e.getEventType() < GAMEVIEW_END_START_STATE) {
	    gVStartStateEventHappend(e);
	} else if (e.getEventType() < GAMEVIEW_END_GAME_STATE) {
	    gVGameStateEventHappend(e);
	}
    }

    private void gVGameStateEventHappend(IAEvent e) {
	if (e.getEventType() == PLAYER_REQUESTED_MOVE) {
	    EPlayerRequestedMove ee = (EPlayerRequestedMove) e;
	    if (ee.getDestination().equals("9000")
		    && ee.getTicketType().equals("Subway")
		    && ee.usedDoubleTicket()) {
		playerRequestedMoveEventOK = true;
	    }
	}
	if (e.getEventType() == PLAYER_SURRENDER) {
	    playerSurrenderEventOK = true;
	}

    }

    private void gVStartStateEventHappend(IAEvent e) {
	if (e.getEventType() == PLAYER_CHOSE_PLAYER_COUNT) {
	    IEPlayerChosePlayerCount ee = (IEPlayerChosePlayerCount) e;
	    if (ee.getPlayerCount() == 1000
		    && ee.getSpecialTicketArray().length == 4) {
		chosePlayerCountEventOK = true;
	    }
	}
	if (e.getEventType() == PLAYER_REQUESTED_MAP_DATA
		&& ((IEPlayerRequestedMapData) e).getMapName().equals(
			"Gangs of Old York")) {
	    playerRequestedMapDataOK = true;
	}
	if (e.getEventType() == PLAYER_SET_PROPERTIES
		&& ((EPlayerSetProperties) e).getSPlayer().equals(stubSPlayer)) {
	    playerSetPropertiesEventOK = true;
	}
	if (e.getEventType() == PLAYER_SWITCHED_TO_NEWGAME_VIEW) {
	    playerSwitchedtoNGVEventOK = true;
	}

    }

    private void gvGlobalEventHappend(IAEvent e) {
	switch (e.getEventType()) {
	case START_GAME_EVENT:
	    startGameEventOk = true;
	    break;
	case NEW_GAME_EVENT:
	    newGameOk = true;
	    break;
	case END_GAME_EVENT:
	    endGameEventOK = true;
	    break;
	case REQUESTED_CLOSE:
	    requestCloseEventOK = true;
	    break;
	case REQUESTED_LANGUAGE_SWITCH:
	    if (((ERequestedLanguageSwitch) e).getLanguageName().equals(
		    "English")) {
		requestedLanguageSwitchEventOK = true;
	    }
	    break;
	case SWITCH_GAMESTATE_EVENT:
		switchStateEventOK = true;
	    break;
	case POSSIBLE_THIEF_EVENT:
	    if (((EPossibleThief) e).getToggle()) {
		possibleThiefOk = true;
	    }
	default:
	    break;
	}
    }

}
