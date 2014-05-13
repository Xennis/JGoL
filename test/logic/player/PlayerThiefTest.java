/**
 * 
 */
package logic.player;

import static org.junit.Assert.*;
import logic.figure.FigureThiefStub;
import logic.figure.IAFigure;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Fabian
 * 
 */
public class PlayerThiefTest {

    private IAPlayer testPlayer;
    private IAPlayer stubPlayer;
    private IAFigure stubFigure;

    @Before
    public void init() {
	this.stubPlayer = new PlayerThiefStub();
	this.stubFigure = new FigureThiefStub();
	this.testPlayer = new PlayerThief(stubPlayer.getName(),
		stubPlayer.getColorName(), stubPlayer.getId(),
		stubPlayer.getType(), stubPlayer.getOwnerGV());
    }

    /**
     * Test method for
     * {@link logic.player.APlayer#addFigure(logic.figure.IAFigure)}.
     */
    @Test
    public void testAddFigure() {
	testPlayer.addFigure(stubFigure);
	assertTrue(testPlayer.getFigures().get(0).equals(stubFigure));
    }

    /**
     * Test method for {@link logic.player.APlayer#destroyFigure(int)}.
     */
    @Test
    public void testDestroyFigureFalseData() {
	testPlayer.addFigure(stubFigure);
	testPlayer.destroyFigure(2);
	assertFalse(testPlayer.getFigures().isEmpty());
    }

    /**
     * Test method for {@link logic.player.APlayer#destroyFigure(int)}.
     */
    @Test
    public void testDestroyFigureTrueData() {
	testPlayer.addFigure(stubFigure);
	testPlayer.destroyFigure(0);
	assertTrue(testPlayer.getFigures().isEmpty());
    }

    /**
     * Test method for {@link logic.player.APlayer#toString()}.
     */
    @Test
    public void testToString() {
	String expectedString = stubPlayer.toString();
	String actualString = testPlayer.toString();
	assertEquals(expectedString, actualString);
    }

    /**
     * Test method for {@link logic.player.APlayer#getColor()}.
     */
    @Test
    public void testGetColor() {
	String expectedString = "java.awt.Color[r=0,g=0,b=255]";
	String actualString = testPlayer.getColor().toString();
	assertEquals(expectedString, actualString);
    }

    /**
     * Test method for {@link logic.player.APlayer#isThief()}.
     */
    @Test
    public void testIsThief() {
	assertTrue(testPlayer.isThief());
    }

    /**
     * Test method for {@link logic.player.APlayer#isPolice()}.
     */
    @Test
    public void testIsPolice() {
	assertFalse(testPlayer.isPolice());
    }

    /**
     * Test method for {@link logic.player.APlayer#hasFigures}.
     */
    @Test
    public void testHasFiguresTrue() {
	testPlayer.addFigure(stubFigure);
	assertTrue(testPlayer.hasFigures());
    }

    /**
     * Test method for {@link logic.player.APlayer#hasFigures}.
     */
    @Test
    public void testHasFiguresFalse() {
	assertFalse(testPlayer.hasFigures());
    }
}
