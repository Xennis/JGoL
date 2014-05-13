/**
 * 
 */
package logic.player;

import static org.junit.Assert.*;
import logic.figure.FigurePoliceStub;
import logic.figure.IFigurePolice;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Fabian
 * 
 */
public class PlayerPoliceTest {

    private IPlayerPolice testPlayer;
    private IPlayerPolice stubPlayer;
    private IFigurePolice stubFigure;

    @Before
    public void init() {
	stubPlayer = new PlayerPoliceStub();
	stubFigure = new FigurePoliceStub();
	testPlayer = new PlayerPolice(stubPlayer.getName(),
		stubPlayer.getColorName(), stubPlayer.getId(),
		stubPlayer.getType(), stubPlayer.getOwnerGV());
    }

    /**
     * Test method for
     * {@link logic.player.APlayer#addFigure(logic.figure.IAFigure)} .
     */
    @Test
    public void testAddFigure() {
	testPlayer.addFigure(stubFigure);
	assertTrue(testPlayer.getFigures().get(0).equals(stubFigure));
    }

    /**
     * Test method for {@link logic.player.APlayer#isThief()} .
     */
    @Test
    public void testIsThief() {
	assertFalse(testPlayer.isThief());
    }

    /**
     * Test method for {@link logic.player.APlayer#isPolice()} .
     */
    @Test
    public void testIsPolice() {
	assertTrue(testPlayer.isPolice());
    }
}
