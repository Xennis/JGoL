/**
 * 
 */
package logic;

import static org.junit.Assert.*;

import gameview.sView.ISPlayer;
import gameview.sView.SPlayerStubPolice;
import gameview.sView.SPlayerStubThief;

import java.util.List;

import logic.figure.IAFigure;
import logic.figure.IFigurePolice;
import logic.figure.IFigureThief;
import logic.player.IAPlayer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import core.ConfigStub;
import core.IConfig;
import core.IRessourceCache;
import core.RessourceCacheStub;

/**
 * @author Fabian
 * 
 */
public class ActorControllerTest {

    private IActorController testActorCon;

    private IActorController stubActorCon;
    private ISPlayer stubSPlayerThief;
    private ISPlayer stubSPlayerPolice;
    private IRessourceCache stubCache;
    private IConfig stubConfig;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
	stubSPlayerThief = new SPlayerStubThief();
	stubSPlayerPolice = new SPlayerStubPolice();
	stubCache = new RessourceCacheStub();
	stubConfig = new ConfigStub();
	stubActorCon = new ActorControllerStub();

	testActorCon = new ActorController();
    }

    /**
     * Test method for
     * {@link logic.ActorController#addPlayerWithFigures(gameview.sView.ISPlayer, int, java.util.Map, java.awt.image.BufferedImage)}
     * .
     */
    @Test
    public void testAddPlayerWithFiguresThief() {
	testActorCon.setStartAt(stubConfig.getStartat());
	testActorCon.addPlayerWithFigures(stubSPlayerThief, 0,
		stubConfig.getThiefTokens(), stubCache.getFigureIcons().get(0));
	IAPlayer actualPlayer = testActorCon.getPlayerList().get(0);
	assertEquals(stubSPlayerThief.getName(), actualPlayer.getName());
    }

    /**
     * Test method for
     * {@link logic.ActorController#addPlayerWithFigures(gameview.sView.ISPlayer, int, java.util.Map, java.awt.image.BufferedImage)}
     * .
     */
    @Test
    public void testAddPlayerWithFiguresPolice() {
	testActorCon.setStartAt(stubConfig.getStartat());
	testActorCon
		.addPlayerWithFigures(stubSPlayerPolice, 0, stubConfig
			.getPoliceTokens(), stubCache.getFigureIcons().get(0));
	IAPlayer actualPlayer = testActorCon.getPlayerList().get(0);
	assertEquals(stubSPlayerPolice.getName(), actualPlayer.getName());
    }

    /**
     * Test method for
     * {@link logic.ActorController#removeFigure(logic.figure.IAFigure)}.
     */
    @Test
    public void testRemoveFigure() {
	testActorCon.setStartAt(stubConfig.getStartat());
	testActorCon
		.addPlayerWithFigures(stubSPlayerPolice, 0, stubConfig
			.getPoliceTokens(), stubCache.getFigureIcons().get(0));
	testActorCon.removeFigure(testActorCon.getFigureList().get(0));
	assertTrue(testActorCon.getFigureList().isEmpty());
    }

    /**
     * Test method for {@link logic.ActorController#setStartAt(java.util.List)}.
     */
    @Test
    public void testSetStartAt() {
	List<String> expectedList = stubConfig.getStartat();
	testActorCon.setStartAt(expectedList);
	List<String> actualList = ((ActorController) testActorCon).startAt;
	assertEquals(expectedList, actualList);
    }

    /**
     * Test method for {@link logic.ActorController#removePlayer(int)}.
     */
    @Test
    public void testRemovePlayer() {
	testActorCon.setStartAt(stubConfig.getStartat());
	testActorCon
		.addPlayerWithFigures(stubSPlayerPolice, 0, stubConfig
			.getPoliceTokens(), stubCache.getFigureIcons().get(0));
	testActorCon.removePlayer(0);
	assertTrue(testActorCon.getPlayerList().isEmpty());
    }

    /**
     * Test method for {@link logic.ActorController#removePlayer(int)}.
     */
    @Test
    public void testRemovePlayer2() {
	testActorCon.setStartAt(stubConfig.getStartat());
	testActorCon.addPlayerWithFigures(stubSPlayerThief, 0,
		stubConfig.getThiefTokens(), stubCache.getFigureIcons().get(0));
	testActorCon
		.addPlayerWithFigures(stubSPlayerPolice, 0, stubConfig
			.getPoliceTokens(), stubCache.getFigureIcons().get(0));
	testActorCon.removePlayer(1);
	assertTrue(testActorCon.getPlayerPoliceNameList().isEmpty());
    }

    /**
     * Test method for {@link logic.ActorController#getNextFigure()}.
     */
    @Test
    public void testGetNextFigure() {
	testActorCon.setStartAt(stubConfig.getStartat());
	testActorCon
		.addPlayerWithFigures(stubSPlayerPolice, 0, stubConfig
			.getPoliceTokens(), stubCache.getFigureIcons().get(0));
	testActorCon.addPlayerWithFigures(stubSPlayerThief, 0,
		stubConfig.getThiefTokens(), stubCache.getFigureIcons().get(0));

	testActorCon.getNextFigure();
	testActorCon.getNextFigure();
	IAFigure actualFigure = testActorCon.getNextFigure();

	assertEquals(1, actualFigure.getId());
    }

    /**
     * Test method for {@link logic.ActorController#getCurrentFigure()}.
     */
    @Test
    public void testGetCurrentFigureException() {
	exception.expect(IndexOutOfBoundsException.class);
	testActorCon.getCurrentFigure();
    }

    /**
     * Test method for {@link logic.ActorController#getCurrentFigure()}.
     */
    @Test
    public void testGetCurrentFigure() {
	testActorCon.setStartAt(stubConfig.getStartat());
	testActorCon
		.addPlayerWithFigures(stubSPlayerPolice, 0, stubConfig
			.getPoliceTokens(), stubCache.getFigureIcons().get(0));

	IAFigure actualFigure = testActorCon.getCurrentFigure();
	assertEquals(0, actualFigure.getId());
    }

    /**
     * Test method for {@link logic.ActorController#getFigureQueue()}.
     */
    @Test
    public void testGetFigureQueue() {
	testActorCon.setStartAt(stubConfig.getStartat());
	testActorCon.addPlayerWithFigures(stubSPlayerThief, 0,
		stubConfig.getThiefTokens(), stubCache.getFigureIcons().get(0));
	testActorCon
		.addPlayerWithFigures(stubSPlayerPolice, 0, stubConfig
			.getPoliceTokens(), stubCache.getFigureIcons().get(0));

	List<Integer> expectedList = stubActorCon.getFigureQueue();
	List<Integer> acutalList = testActorCon.getFigureQueue();
	assertEquals(expectedList, acutalList);
    }

    /**
     * Test method for {@link logic.ActorController#destroyCurrentGameState()}.
     */
    @Test
    public void testDestroyCurrentGameState() {
	testActorCon.setStartAt(stubConfig.getStartat());
	testActorCon.addPlayerWithFigures(stubSPlayerThief, 0,
		stubConfig.getThiefTokens(), stubCache.getFigureIcons().get(0));

	testActorCon.destroyCurrentGameState();
	assertTrue(testActorCon.getPlayerList().isEmpty()
		&& testActorCon.getFigureList().isEmpty());
    }

    /**
     * Test method for {@link logic.ActorController#getPlayerPoliceNameList()}.
     */
    @Test
    public void testGetPlayerPoliceNameList() {
	testActorCon.setStartAt(stubConfig.getStartat());
	testActorCon.addPlayerWithFigures(stubSPlayerThief, 0,
		stubConfig.getThiefTokens(), stubCache.getFigureIcons().get(0));
	testActorCon
		.addPlayerWithFigures(stubSPlayerPolice, 0, stubConfig
			.getPoliceTokens(), stubCache.getFigureIcons().get(0));

	List<String> expectedList = stubActorCon.getPlayerPoliceNameList();
	List<String> actualList = testActorCon.getPlayerPoliceNameList();
	assertEquals(expectedList, actualList);
    }

    /**
     * Test method for {@link logic.ActorController#getPlayerThiefNameList()}.
     */
    @Test
    public void testGetPlayerThiefNameList() {
	testActorCon.setStartAt(stubConfig.getStartat());
	testActorCon.addPlayerWithFigures(stubSPlayerThief, 0,
		stubConfig.getThiefTokens(), stubCache.getFigureIcons().get(0));
	testActorCon
		.addPlayerWithFigures(stubSPlayerPolice, 0, stubConfig
			.getPoliceTokens(), stubCache.getFigureIcons().get(0));

	List<String> expectedList = stubActorCon.getPlayerThiefNameList();
	List<String> actualList = testActorCon.getPlayerThiefNameList();
	assertEquals(expectedList, actualList);
    }

    /**
     * Test method for {@link logic.ActorController#getFigureThiefList()}.
     */
    @Test
    public void testGetFigureThiefList() {
	testActorCon.setStartAt(stubConfig.getStartat());
	testActorCon.addPlayerWithFigures(stubSPlayerThief, 0,
		stubConfig.getThiefTokens(), stubCache.getFigureIcons().get(0));
	testActorCon
		.addPlayerWithFigures(stubSPlayerPolice, 0, stubConfig
			.getPoliceTokens(), stubCache.getFigureIcons().get(0));
	testActorCon.addPlayerWithFigures(stubSPlayerThief, 0,
		stubConfig.getThiefTokens(), stubCache.getFigureIcons().get(0));

	List<IFigureThief> actualList = testActorCon.getFigureThiefList();
	assertEquals(2, actualList.size());
    }

    /**
     * Test method for {@link logic.ActorController#getFigurePoliceList()}.
     */
    @Test
    public void testGetFigurePoliceList() {
	testActorCon.setStartAt(stubConfig.getStartat());
	testActorCon.addPlayerWithFigures(stubSPlayerThief, 0,
		stubConfig.getThiefTokens(), stubCache.getFigureIcons().get(0));
	testActorCon
		.addPlayerWithFigures(stubSPlayerPolice, 0, stubConfig
			.getPoliceTokens(), stubCache.getFigureIcons().get(0));
	testActorCon
		.addPlayerWithFigures(stubSPlayerPolice, 0, stubConfig
			.getPoliceTokens(), stubCache.getFigureIcons().get(0));

	List<IFigurePolice> actualList = testActorCon.getFigurePoliceList();
	assertEquals(2, actualList.size());
    }

    /**
     * Test method for {@link logic.ActorController#getNumPlayer()}.
     */
    @Test
    public void testGetNumPlayerZero() {
	int expectedInt = 0;
	int actualInt = testActorCon.getNumPlayer();
	assertSame(expectedInt, actualInt);
    }

    /**
     * Test method for {@link logic.ActorController#getNumPlayer()}.
     */
    @Test
    public void testGetNumPlayer() {
	testActorCon.setStartAt(stubConfig.getStartat());
	testActorCon
		.addPlayerWithFigures(stubSPlayerPolice, 0, stubConfig
			.getPoliceTokens(), stubCache.getFigureIcons().get(0));

	int expectedInt = 1;
	int actualInt = testActorCon.getNumPlayer();
	assertSame(expectedInt, actualInt);
    }

}
