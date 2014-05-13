/**
 * 
 */
package core.msgpump;

import static org.junit.Assert.*;
import gameview.generalView.MainWindowListener;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Fabian
 * 
 */
public class MsgPumpTest {

    private IMsgPump testMsgPump;

    @Before
    public void init() {
	testMsgPump = new MsgPump();
    }

    /**
     * Test method for {@link core.msgpump.MsgPump#hasMsg()}.
     */
    @Test
    public void testHasMsgFalse() {
	assertFalse(testMsgPump.hasMsg());
    }

    /**
     * Test method for {@link core.msgpump.MsgPump#hasMsg()}.
     */
    @Test
    public void testHasMsgTrue() {
	testMsgPump.logInfo("test");
	assertTrue(testMsgPump.hasMsg());
    }

    /**
     * Test method for {@link core.msgpump.MsgPump#getNextMsg()}.
     */
    @Test
    public void testGetNextMsgElseCondition() {
	Message actualMessage = testMsgPump.getNextMsg();
	assertEquals(Integer.MAX_VALUE, actualMessage.getIdentifier());
    }

    /**
     * Test method for {@link core.msgpump.MsgPump#getNextMsg()}.
     */
    @Test
    public void testGetNextMsgIfCondition() {
	String test = "test";
	testMsgPump.logInfo(test);
	String test1 = (String) testMsgPump.getNextMsg().getArgs1();
	assertEquals(test1, test);
    }

    /**
     * Test method for
     * {@link core.msgpump.MsgPump#addComponent(javax.swing.JComponent)}.
     */
    @Test
    public void testAddComponent() {
	JPanel test = new JPanel();
	testMsgPump.addComponent(test);
	JPanel test1 = (JPanel) testMsgPump.getNextMsg().getArgs1();
	assertEquals(test1, test);

    }

    /**
     * Test method for
     * {@link core.msgpump.MsgPump#removeComponent(javax.swing.JComponent)}.
     */
    @Test
    public void testRemoveComponent() {
	JPanel test = new JPanel();
	testMsgPump.removeComponent(test);
	JPanel test1 = (JPanel) testMsgPump.getNextMsg().getArgs1();
	assertEquals(test1, test);
    }

    /**
     * Test method for {@link core.msgpump.MsgPump#logInfo(java.lang.String)}.
     */
    @Test
    public void testLogInfo() {
	String test = "test";
	testMsgPump.logInfo(test);
	String test1 = (String) testMsgPump.getNextMsg().getArgs1();
	assertEquals(test1, test);
    }

    /**
     * Test method for {@link core.msgpump.MsgPump#logDebug(java.lang.String)}.
     */
    @Test
    public void testLogDebug() {
	String test = "test";
	testMsgPump.logDebug(test);
	String test1 = (String) testMsgPump.getNextMsg().getArgs1();
	assertEquals(test1, test);
    }

    /**
     * Test method for {@link core.msgpump.MsgPump#logError(java.lang.String)}.
     */
    @Test
    public void testLogError() {
	String test = "test";
	testMsgPump.logError(test);
	String test1 = (String) testMsgPump.getNextMsg().getArgs1();
	assertEquals(test1, test);
    }

    /**
     * Test method for
     * {@link core.msgpump.MsgPump#logInfoSource(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testLogInfoSource() {
	String test = "test";
	String testt = "testit!";
	testMsgPump.logInfoSource(test, testt);
	Message n = testMsgPump.getNextMsg();
	String test1 = (String) n.getArgs1();
	String test2 = (String) n.getArgs2();
	assertEquals(test1, test);
	assertEquals(test2, testt);
    }

    /**
     * Test method for {@link core.msgpump.MsgPump#requestShutdown()}.
     */
    @Test
    public void testRequestShutdown() {
	testMsgPump.requestShutdown();
	assertEquals(Message.SHUTDOWN_REQUEST, testMsgPump.getNextMsg()
		.getIdentifier());
    }

    /**
     * Test method for {@link core.msgpump.MsgPump#switchToStartState()}.
     */
    @Test
    public void testSwitchToStartState() {
	testMsgPump.switchToStartState();
	assertEquals(Message.SWITCH_TO_STARTSTATE, testMsgPump.getNextMsg()
		.getIdentifier());
    }

    /**
     * Test method for {@link core.msgpump.MsgPump#switchtoGameState()}.
     */
    @Test
    public void testSwitchtoGameState() {
	testMsgPump.switchtoGameState();
	assertEquals(Message.SWITCH_TO_GAMESTATE, testMsgPump.getNextMsg()
		.getIdentifier());
    }

    /**
     * Test method for {@link core.msgpump.MsgPump#switchToEndState()}.
     */
    @Test
    public void testSwitchToEndState() {
	testMsgPump.switchToEndState();
	assertEquals(Message.SWITCH_TO_ENDSTATE, testMsgPump.getNextMsg()
		.getIdentifier());
    }

    /**
     * Test method for
     * {@link core.msgpump.MsgPump#addListener(gameview.generalView.MainWindowListener)}
     * .
     */
    @Test
    public void testAddListener() {
	MainWindowListener l = new MainWindowListener(null);
	testMsgPump.addListener(l);
	assertEquals(l, (MainWindowListener) testMsgPump.getNextMsg()
		.getArgs1());
    }

    /**
     * Test method for
     * {@link core.msgpump.MsgPump#addMenuBar(javax.swing.JMenuBar)}.
     */
    @Test
    public void testAddMenuBar() {
	JMenuBar n = new JMenuBar();
	testMsgPump.addMenuBar(n);
	assertEquals(n, (JMenuBar) testMsgPump.getNextMsg().getArgs1());
    }

}
