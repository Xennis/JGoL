/**
 * 
 */
package logic;

import static org.junit.Assert.*;

import logic.figure.FigureThiefStub;
import logic.figure.IAFigure;

import org.junit.Before;
import org.junit.Test;

import core.IStationMap;
import core.StationMapStub;
import events.logic.gameState.EPlayerMovedStub;
import events.logic.gameState.IEPlayerMoved;

/**
 * @author Fabian
 * 
 */
public class PositioningEngineTest {

    private PositioningEngine testPosEngine;
    private IActorController stubActorCon;
    private IStationMap stubStationMap;
    private IEPlayerMoved stubEPlayerMoved;
    private IAFigure stubFigure;

    @Before
    public void init() {
	stubActorCon = new ActorControllerStub();
	stubStationMap = new StationMapStub();
	stubEPlayerMoved = new EPlayerMovedStub();
	stubFigure = new FigureThiefStub();
	testPosEngine = new PositioningEngine(stubActorCon, stubStationMap);
    }

    /**
     * Test method for {@link logic.PositioningEngine#init()}.
     */
    @Test
    public void testInit() {
	testPosEngine.init();
	testPosEngine.init();
	assertFalse(testPosEngine.getPossiblePositions().isEmpty());
    }

    /**
     * Test method for {@link logic.PositioningEngine#destoryData()}.
     */
    @Test
    public void testDestoryData() {
	testPosEngine.init();
	testPosEngine.destoryData();
	assertTrue(testPosEngine.getPossiblePositions().isEmpty());
    }

    /**
     * Test method for
     * {@link logic.PositioningEngine#updatePosition(java.lang.String, int)}.
     */
    @Test
    public void testUpdatePosition() {
	testPosEngine.init();
	testPosEngine.moveHappend(stubEPlayerMoved, stubFigure.getId());
	testPosEngine.updatePosition("97", stubFigure.getId());
	assertTrue(testPosEngine.getPossiblePositions().get(stubFigure.getId())
		.contains("97"));
    }

}
