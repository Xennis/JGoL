/**
 * 
 */
package logic.figure;

import static org.junit.Assert.*;

import java.util.Map;

import logic.Constants;
import logic.player.IAPlayer;
import logic.player.PlayerThiefStub;

import org.junit.Before;
import org.junit.Test;

import events.gameview.gameState.EPlayerRequestedMoveStub;
import events.gameview.gameState.IEPlayerRequestedMove;

/**
 * @author Fabian
 * 
 */
public class FigureThiefTest {

    private IFigureThief testFigure;
    private IFigureThief stubFigure;
    private IAPlayer stubPlayer;
    private IEPlayerRequestedMove stubEvent;

    private String testFigurePosition;

    @Before
    public void init() {
	testFigurePosition = "22";
	stubPlayer = new PlayerThiefStub();
	stubFigure = new FigureThiefStub();
	stubEvent = new EPlayerRequestedMoveStub();
	testFigure = new FigureThief(stubPlayer, 0, testFigurePosition,
		stubFigure.getTicketsRemaining(), stubFigure.getIcon());
    }

    /**
     * Test method for
     * {@link logic.figure.AFigure#setPosition(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testSetPositionFalseTicketType() {
	testFigure.setPosition("99", "fly");
	assertEquals("Expected old position", testFigurePosition,
		testFigure.getPosition());
    }

    /**
     * Test method for
     * {@link logic.figure.AFigure#setPosition(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testSetPositionNoTicket() {
	testFigure.setPosition("99", "car");
	assertEquals("Expected old position", testFigurePosition,
		testFigure.getPosition());
    }

    /**
     * Test method for
     * {@link logic.figure.AFigure#setPosition(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testSetPositionTrueData() {
	String newPosition = "99";
	String usedTicket = "walk";

	testFigure.setPosition(newPosition, usedTicket);
	assertEquals("Expected new position", newPosition,
		testFigure.getPosition());
    }

    /**
     * Test method for
     * {@link logic.figure.AFigure#setPosition(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testSetPositionTrueData2() {
	String newPosition = "99";
	String usedTicket = "walk";
	int numWalkTickets = testFigure.getTicketsRemaining().get(usedTicket);
	testFigure.setPosition(newPosition, usedTicket);
	assertSame(numWalkTickets - 1,
		testFigure.getTicketsRemaining().get(usedTicket));
    }

    /**
     * Test method for {@link logic.figure.AFigure#getTicketTypesRemaining()}.
     */
    @Test
    public void testGetTicketTypesRemaining() {
	assertEquals(stubFigure.getTicketTypesRemaining(),
		testFigure.getTicketTypesRemaining());
    }

    /**
     * Test method for {@link logic.figure.AFigure#toString()}.
     */
    @Test
    public void testToString() {
	String expectedString = stubFigure.toString();
	String actualString = testFigure.toString();
	assertEquals(expectedString, actualString);
    }

    /**
     * Test method for {@link logic.figure.AFigure#isPolice()}.
     */
    @Test
    public void testIsPolice() {
	assertFalse(testFigure.isPolice());
    }

    /**
     * Test method for {@link logic.figure.AFigure#isThief()}.
     */
    @Test
    public void testIsThief() {
	assertTrue(testFigure.isThief());
    }

    /**
     * Test method for
     * {@link logic.figure.AFigure#addTicket(java.lang.String, int)}.
     */
    @Test
    public void testAddTicket() {
	testFigure.processEvent(stubEvent);
	assertTrue(testFigure.getTicketTypesRemaining().contains(
		stubEvent.getTicketType()));
    }

    /**
     * Test method for {@link logic.figure.AFigure#hasDoubleTicket()}.
     */
    @Test
    public void testHasDoubleTicketFalseIf() {
	assertFalse(testFigure.hasDoubleTicket());
    }

    /**
     * Test method for {@link logic.figure.AFigure#hasDoubleTicket()}.
     */
    @Test
    public void testHasDoubleTicketFalseElse() {
	testFigure.addTicket(Constants.TICKET_DOUBLE, 0);
	assertFalse(testFigure.hasDoubleTicket());
    }

    /**
     * Test method for {@link logic.figure.AFigure#hasDoubleTicket()}.
     */
    @Test
    public void testHasDoubleTicketTrue() {
	testFigure.addTicket(Constants.TICKET_DOUBLE, 1);
	assertTrue(testFigure.hasDoubleTicket());
    }

    /**
     * Test method for {@link logic.figure.AFigure#usedDoubleTicket()}.
     */
    @Test
    public void testUsedDoubleTicketNoDouble() {
	testFigure.usedDoubleTicket();
	Map<String, Integer> expectedMap = stubFigure.getTicketsRemaining();
	Map<String, Integer> actualMap = testFigure.getTicketsRemaining();
	assertEquals("Expected no change", expectedMap, actualMap);
    }

    /**
     * Test method for {@link logic.figure.AFigure#usedDoubleTicket()}.
     */
    @Test
    public void testUsedDoubleTicketZeroDouble() {
	testFigure.addTicket(Constants.TICKET_DOUBLE, 0);
	testFigure.usedDoubleTicket();
	assertSame(0,
		testFigure.getTicketsRemaining().get(Constants.TICKET_DOUBLE));
    }

    /**
     * Test method for {@link logic.figure.AFigure#usedDoubleTicket()}.
     */
    @Test
    public void testUsedDoubleTicketTrue() {
	testFigure.addTicket(Constants.TICKET_DOUBLE, 2);
	testFigure.usedDoubleTicket();
	assertSame(1,
		testFigure.getTicketsRemaining().get(Constants.TICKET_DOUBLE));
    }
}
