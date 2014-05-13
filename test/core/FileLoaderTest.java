/**
 * 
 */
package core;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import core.msgpump.IMsgPump;
import core.msgpump.MsgPumpStub;

/**
 * @author Fabian
 * 
 */
public class FileLoaderTest {

    private IMsgPump stubMsgPump;

    @Before
    public void init() {
	stubMsgPump = new MsgPumpStub();
    }

    /**
     * Test method for
     * {@link core.FileLoader#loadImage(java.lang.String, java.lang.String, core.msgpump.IMsgPump)
     * )}.
     */
    @Test
    public void testLoadImageEmpty() {
	BufferedImage actualImage = FileLoader.loadImage("", "", stubMsgPump);
	assertNull(actualImage);
    }

    /**
     * Test method for
     * {@link core.FileLoader#loadImage(java.lang.String, java.lang.String, core.msgpump.IMsgPump)
     * )}.
     */
    @Test
    public void testLoadImageFalseData() {
	BufferedImage actualImage = FileLoader.loadImage(
		".\\mediaTest\\maps\\noIni.ini", "noImage", stubMsgPump);
	assertNull(actualImage);
    }

    /**
     * Test method for
     * {@link core.FileLoader#loadImage(java.lang.String, java.lang.String, core.msgpump.IMsgPump)}
     * .
     */
    @Test
    public void testLoadImageTrueData() {
	BufferedImage actualImage = FileLoader.loadImage(
		"./mediaTest/maps/sample.ini", "sample.jpg", stubMsgPump);
	assertEquals(1650, actualImage.getHeight());
    }

    /**
     * Test method for
     * {@link core.FileLoader#loadCoordinates(java.lang.String, java.lang.String, core.msgpump.IMsgPump)}
     * .
     */
    @Test
    public void testLoadCoordinatesEmpty() {
	Map<String, Point> actualMap = FileLoader.loadCoordinates("", "",
		stubMsgPump);
	assertTrue("Expected empty map", actualMap.isEmpty());
    }

    /**
     * Test method for
     * {@link core.FileLoader#loadCoordinates(java.lang.String, java.lang.String, core.msgpump.IMsgPump)
     * )}.
     */
    @Test
    public void testLoadCoordinatesFalseData() {
	Map<String, Point> actualMap = FileLoader.loadCoordinates(
		".\\mediaTest\\maps\\noIni.ini", "noFile", stubMsgPump);
	assertTrue("Expected empty map", actualMap.isEmpty());
    }

    /**
     * Test method for
     * {@link core.FileLoader#loadCoordinates(java.lang.String, java.lang.String, core.msgpump.IMsgPump)}
     * .
     */
    @Test
    public void testLoadCoordinatesTrueData() {
	Map<String, Point> expectedMap = FileLoaderStub.loadCoordinates(null,
		null);
	Map<String, Point> actualMap = FileLoader.loadCoordinates(
		"./mediaTest/maps/sample.ini", "sample_coordinates.txt",
		stubMsgPump);
	assertEquals("Expected map", expectedMap, actualMap);
    }

    /**
     * Test method for
     * {@link core.FileLoader#loadLinks(java.lang.String, java.lang.String, core.msgpump.IMsgPump)}
     * .
     */
    @Test
    public void testLoadLinksFalseData() {
	IStationMap actualMap = FileLoader.loadLinks("noDir", "noFile",
		stubMsgPump);
	assertTrue("Expected empty map", actualMap.isEmpty());
    }

    /**
     * Test method for
     * {@link core.FileLoader#loadLinks(java.lang.String, java.lang.String, core.msgpump.IMsgPump)}
     * .
     */
    @Test
    public void testLoadLinksTrueData() {
	IStationMap expectedMap = FileLoaderStub.loadLinks(null, null);
	IStationMap actualMap = FileLoader.loadLinks(
		"./mediaTest/maps/sample.ini", "sample_links.txt", stubMsgPump);
	assertEquals("Expected map", expectedMap, actualMap);
    }
}
