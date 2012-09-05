package core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.msgpump.Message;
import core.pedobear.IPedoLogger;
import core.pedobear.PedoLogger;

public class MainTest {
    Thread testThread;
    Main main;

    @Before
    public void init() {
	testThread = new Thread() {
	    public void run() {

		main = Main.getInstance();
		main.setLoggingMode(PedoLogger.CONSOLE_ONLY);
		main.start();
	    }
	};
	testThread.start();
	try {
	    Thread.sleep(1000);
	} catch (Exception e) {
	    fail("Multithreading im Test ist halt sonne Sache");
	    System.exit(0);
	}
	main.setLoggingMode(IPedoLogger.CONSOLE_ONLY);
    }

    /**
     * Test method for {@link core.Main#start()}.
     */
    @Test
    public void testStart() {
	main.msgPump.requestShutdown();
	assertNotSame(Thread.State.RUNNABLE, testThread.getState());
    }

    /**
     * Test method for {@link core.Main#initCache()}.
     */
    @Test
    public void testInitCache() {
	assertNotNull(main.getCache().getMapIniPaths());
    }

    /**
     * Test method for {@link core.Main#initLog()}.
     */
    @Test
    public void testInitLogic() {
	assertNotNull(main.logic);
    }

    /**
     * Test method for {@link core.Main#dispatchLogMsg(core.msgpump.Message)}.
     */
    @Test
    public void testLoggingMsgDispatcher() {
	main.msgPump.logDebug("Test");
	main.msgPump.logError("Test");
	assertTrue(true);
    }

    /**
     * Test method for
     * {@link core.Main#dispatchGameStateSwitchmsg(core.msgpump.Message)}.
     */
    @Test
    public void testDispatchGameStateSwitchmsgStart() {
	main.dispatchGameStateSwitchmsg(new Message(
		Message.SWITCH_TO_STARTSTATE, null, null));
	assertEquals(IMain.GAME_STATE_START, main.gameState);
    }

    /**
     * Test method for
     * {@link core.Main#dispatchGameStateSwitchmsg(core.msgpump.Message)}.
     */
    @Test
    public void testDispatchGameStateSwitchmsgPlay() {
	main.dispatchGameStateSwitchmsg(new Message(
		Message.SWITCH_TO_GAMESTATE, null, null));
	assertEquals(IMain.GAME_STATE_PLAY, main.gameState);
    }

    /**
     * Test method for
     * {@link core.Main#dispatchGameStateSwitchmsg(core.msgpump.Message)}.
     */
    @Test
    public void testDispatchGameStateSwitchmsgEnd() {
	main.dispatchGameStateSwitchmsg(new Message(Message.SWITCH_TO_ENDSTATE,
		null, null));
	assertEquals(IMain.GAME_STATE_END, main.gameState);
    }

    /**
     * Test method for
     * {@link core.Main#dispatchGameStateSwitchmsg(core.msgpump.Message)}.
     */
    @Test
    public void testDispatchGameStateSwitchmsgFalse() {
	main.dispatchGameStateSwitchmsg(new Message(-1374, null, null));
	assertEquals(2, main.gameState);
    }

    @After
    public void shutdownThread() {
	main.msgPump.requestShutdown();
    }

}
