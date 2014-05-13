/**
 * 
 */
package logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Fabian
 * 
 */
public class GameLogTest {

    private GameLog testGameLog;

    @Before
    public void init() {
	testGameLog = new GameLog();
    }

    /**
     * Test method for {@link logic.GameLog#updateLog(java.lang.String)}.
     */
    @Test
    public void testUpdateLog() {
	String expectedString = "Wie geht es?";
	testGameLog.updateLog(expectedString);
	String actualString = testGameLog.returnLog().get(0);
	assertEquals(expectedString, actualString);
    }

    /**
     * Test method for {@link logic.GameLog#updateThiefLog(java.lang.String)}.
     */
    @Test
    public void testUpdateThiefLog() {
	String expectedString = "Dies ist nur ein Test";
	testGameLog.updateThiefLog(expectedString);
	String actualString = testGameLog.returnThiefLog().get(0);
	assertEquals(expectedString, actualString);
    }

    /**
     * Test method for {@link logic.GameLog#clearLog()}.
     */
    @Test
    public void testClearLogThiefLog() {
	testGameLog.updateThiefLog("test2");
	testGameLog.clearLog();
	assertTrue(testGameLog.returnThiefLog().isEmpty());
    }

    /**
     * Test method for {@link logic.GameLog#clearLog()}.
     */
    @Test
    public void testClearLogGameLog() {
	testGameLog.updateLog("test1");
	testGameLog.clearLog();
	assertTrue(testGameLog.returnLog().isEmpty());
    }
}
