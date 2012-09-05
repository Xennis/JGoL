/**
 * 
 */
package core;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import logic.Constants;
import logic.figure.FigureThiefStub;
import logic.figure.IAFigure;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Fabian
 * 
 */
public class StationTest {

    private IAFigure stubFigureThief;
    private IStation testLink;
    private IStation stubLink;

    @Before
    public void init() {
	testLink = new Station("22");
	stubLink = new StationStub();
	stubFigureThief = new FigureThiefStub();
    }

    /**
     * Test method for {@link core.Station#addLink(java.lang.String, String)}.
     */
    @Test
    public void testAddLink() {
	testLink.addLink("walk", "21");
	assertTrue(testLink.checkLink("walk", "21"));
    }

    /**
     * Test method for {@link core.Station#addLink(java.lang.String, String)}.
     */
    @Test
    public void testAddLink2() {
	testLink.addLink("walk", "21");
	testLink.addLink("walk", "23");
	assertTrue(testLink.checkLink("walk", "23"));
    }

    /**
     * Test method for {@link core.Station#checkLink(java.lang.String, String)}.
     */
    @Test
    public void testCheckLinkFalse() {
	assertFalse(testLink.checkLink("noType", "-1"));
    }

    /**
     * Test method for {@link core.Station#getReachableLinks(java.util.List)}.
     */
    @Test
    public void testGetReachableLinks() {
	((Station) testLink).linkMap = stubLink.getLinkMap();

	Map<String, Set<String>> excpetedMap = stubLink.getReachableLinks(null);
	excpetedMap.get("21").remove("swim");
	Map<String, Set<String>> actualMap = testLink
		.getReachableLinks(stubFigureThief.getTicketTypesRemaining());
	assertEquals(excpetedMap, actualMap);
    }

    /**
     * Test method for
     * {@link core.Station#getReachableStations(java.lang.String)}.
     */
    @Test
    public void testGetReachableStationsFalse() {
	Set<String> actualList = testLink.getReachableStations("noType");
	assertTrue(actualList.isEmpty());
    }

    /**
     * Test method for
     * {@link core.Station#getReachableStations(java.lang.String)}.
     */
    @Test
    public void testGetReachableStationsTrue() {
	((Station) testLink).linkMap = stubLink.getLinkMap();

	Set<String> excpetedList = stubLink.getReachableStations("");
	Set<String> actualList = testLink.getReachableStations("walk");
	assertEquals(excpetedList, actualList);
    }

    /**
     * Test method for {@link core.Station#getReachableStations(java.util.List)}
     * .
     */
    @Test
    public void testGetReachableStations() {
	((Station) testLink).linkMap = stubLink.getLinkMap();

	List<String> testParam = new LinkedList<>();
	testParam.add("black");
	testParam.add("walk");

	Set<String> excpetedList = stubLink.getReachableStations("");
	Set<String> actualList = testLink.getReachableStations(testParam);
	assertEquals(excpetedList, actualList);
    }

    /**
     * Test method for {@link core.Station#toString()}.
     */
    @Test
    public void testToString() {
	((Station) testLink).linkMap = stubLink.getLinkMap();

	String excpetedString = stubLink.toString();
	String actualString = testLink.toString();
	assertEquals(excpetedString, actualString);
    }

    /**
     * Test method for {@link core.Station#getTicketTypes(java.lang.String)}.
     */
    @Test
    public void testGetTicketTypes() {
	((Station) testLink).linkMap = stubLink.getLinkMap();

	Set<String> excpetedSet = stubLink.getTicketTypes(null);
	excpetedSet.add(Constants.TICKET_BLACK);
	Set<String> actualSet = testLink.getTicketTypes("23");
	assertEquals(excpetedSet, actualSet);
    }

    /**
     * Test method for {@link core.Station#getTicketTypes(java.lang.String)}.
     */
    @Test
    public void testGetTicketTypes2() {
	((Station) testLink).linkMap = stubLink.getLinkMap();

	Set<String> expectedSet = new HashSet<String>();
	expectedSet.add(Constants.TICKET_BLACK);
	Set<String> actualSet = testLink.getTicketTypes("-2");
	assertEquals(expectedSet, actualSet);
    }
}
