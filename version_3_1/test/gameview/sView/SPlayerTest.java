/**
 * 
 */
package gameview.sView;

import static org.junit.Assert.*;
import logic.Constants;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Fabian
 * 
 */
public class SPlayerTest {

    private ISPlayer testSPlayer;
    private ISPlayer stubSPlayerThief;

    @Before
    public void init() {
	stubSPlayerThief = new SPlayerStubThief();
	testSPlayer = new SPlayer(stubSPlayerThief.getName(),
		stubSPlayerThief.getType(), stubSPlayerThief.getColor(),
		stubSPlayerThief.getFigureCount(), stubSPlayerThief.getIcon());
    }

    /**
     * Test method for {@link gameview.sView.SPlayer#isKiPlayer()}.
     */
    @Test
    public void testIsKiPlayerFalse() {
	assertFalse(testSPlayer.isKiPlayer());
    }

    /**
     * Test method for {@link gameview.sView.SPlayer#isKiPlayer()}.
     */
    @Test
    public void testIsKiPlayerTrueEasy() {
	testSPlayer.setBotOrPlayer(Constants.TYPE_KI_EASY);
	assertTrue(testSPlayer.isKiPlayer());
    }

    /**
     * Test method for {@link gameview.sView.SPlayer#isKiPlayer()}.
     */
    @Test
    public void testIsKiPlayerTrueHard() {
	testSPlayer.setBotOrPlayer(Constants.TYPE_KI_HARD);
	assertTrue(testSPlayer.isKiPlayer());
    }

}
