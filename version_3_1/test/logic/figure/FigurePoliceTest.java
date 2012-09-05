/**
 * 
 */
package logic.figure;

import static org.junit.Assert.*;
import logic.player.IPlayerPolice;
import logic.player.PlayerPoliceStub;
import org.junit.Before;
import org.junit.Test;

import core.ConfigStub;
import core.IConfig;

/**
 * @author Fabian
 * 
 */
public class FigurePoliceTest {

    private IFigurePolice testFigure;
    private IFigurePolice stubFigure;
    private IPlayerPolice stubPlayer;
    private IConfig stubConfig;

    @Before
    public void init() {
	stubPlayer = new PlayerPoliceStub();
	stubFigure = new FigurePoliceStub();
	stubConfig = new ConfigStub();
	testFigure = new FigurePolice(stubPlayer, 1, "66",
		stubConfig.getPoliceTokens(), stubFigure.getIcon());
    }

    /**
     * Test method for {@link logic.figure.FigurePolice#isThief()}.
     */
    @Test
    public void testIsThief() {
	assertFalse(testFigure.isThief());
    }

    /**
     * Test method for {@link logic.figure.FigurePolice#isPolice()}.
     */
    @Test
    public void testIsPolice() {
	assertTrue(testFigure.isPolice());
    }

}
