/**
 * 
 */
package logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.msgpump.IMsgPump;
import core.msgpump.MsgPump;
import events.IAEvent;
import events.gameview.gameState.EPlayerRequestedMoveStub;
import events.gameview.gameState.EPlayerRequestedMoveStubProcessed;
import gameview.IGameView;
import gameview.KIGameViewStubEasy;
import gameview.KIGameViewStubHard;
import gameview.PlayerGameViewStub;

/**
 * @author Fabian
 * 
 */
public class GameViewManagerTest {

    private IGameViewManager testManager;
    private IMsgPump stubMsgPump;
    private IAEvent stubEvent;
    private IGameView stubKiGameViewEasy;
    private IGameView stubKiGameViewHard;
    private IGameView stubPlayerGameView;
    private IAEvent stubEventProcessed;

    @Before
    public void init() {
	stubMsgPump = new MsgPump();
	stubEvent = new EPlayerRequestedMoveStub();
	stubKiGameViewEasy = new KIGameViewStubEasy();
	stubKiGameViewHard = new KIGameViewStubHard();
	stubPlayerGameView = new PlayerGameViewStub();
	stubEventProcessed = new EPlayerRequestedMoveStubProcessed();
	testManager = new GameViewManager(stubMsgPump);
    }

    /**
     * Test method for {@link logic.GameViewManager#queueEvent(events.IAEvent)}.
     */
    @Test
    public void testQueueEvent() {
	testManager.queueEvent(stubEvent);
	IAEvent actualEvent = testManager.getEventList().get(0);
	assertEquals(stubEvent, actualEvent);
    }

    /**
     * Test method for {@link logic.GameViewManager#removeGameView(int)}.
     */
    @Test
    public void testRemoveGameView() {
	testManager.queueEvent(stubEvent);
	testManager.removeGameView(0);
	assertTrue(testManager.getEventList().isEmpty());
    }

    /**
     * Test method for
     * {@link logic.GameViewManager#addGameView(gameview.IGameView)}.
     */
    @Test
    public void testAddGameView() {
	testManager.addGameView(stubPlayerGameView);
	IGameView expectedGameView = testManager.getGameViewList().get(0);
	assertEquals(stubPlayerGameView, expectedGameView);
    }

    /**
     * Test method for {@link logic.GameViewManager#processEvents()}.
     */
    @Test
    public void testProcessEvents() {
	testManager.addGameView(stubPlayerGameView); // add gameView
	testManager.queueEvent(stubEvent); // add event
	testManager.queueEvent(stubEventProcessed);
	testManager.processEvents();
	assertTrue(testManager.getEventList().isEmpty());
    }

    /**
     * Test method for {@link logic.GameViewManager#removeAllKI()}.
     */
    @Test
    public void testRemoveAllKi() {
	testManager.addGameView(stubKiGameViewEasy);
	testManager.addGameView(stubPlayerGameView);
	testManager.addGameView(stubKiGameViewHard);
	testManager.removeAllKI();
	assertSame(1, testManager.getGameViewList().size());
    }
}
