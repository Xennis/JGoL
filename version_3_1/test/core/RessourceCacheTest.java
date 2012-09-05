/**
 * 
 */
package core;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import core.msgpump.IMsgPump;
import core.msgpump.MsgPumpStub;

/**
 * @author Fabian
 * 
 */
public class RessourceCacheTest {

    private RessourceCache testCache;
    private IRessourceCache stubCache;
    private IMsgPump stubMsgPump;

    @Before
    public void init() {
	stubMsgPump = new MsgPumpStub();
	testCache = new RessourceCache(stubMsgPump);
	stubCache = new RessourceCacheStub();
    }

    /**
     * Test method for {@link core.RessourceCache#getConfig()}.
     */
    @Test
    public void testGetConfigNull() {
	IConfig actualConfig = testCache.getConfig();
	assertNull(actualConfig);
    }

    /**
     * Test method for
     * {@link core.RessourceCache#getConfigByName(java.lang.String)}.
     */
    @Test
    public void testGetConfigByNameFalseData() {
	// Returns last element of "mapIniPaths"
	IConfig actualConfig = testCache.getConfigByName("noName");
	assertEquals("Expected String", "Low ticket test",
		actualConfig.getName());
    }

    /**
     * Test method for
     * {@link core.RessourceCache#getConfigByName(java.lang.String)}.
     */
    @Test
    public void testGetConfigByNameTrueData() {
	IConfig actualConfig = testCache.getConfigByName("Gangs of Lübeck");
	assertEquals("Expected String", "Gangs of Lübeck",
		actualConfig.getName());
    }

    /**
     * Test method for {@link core.RessourceCache#getFigureIcons()}.
     */
    @Test
    public void testGetFigureIconsNull() {
	List<BufferedImage> actualList = testCache.getFigureIcons();
	assertNull(actualList);
    }

    /**
     * Test method for {@link core.RessourceCache#getFigureIcons()}.
     */
    @Test
    public void testGetFigureIconsIfCondition() {
	testCache.scanDir();
	List<BufferedImage> actualList = testCache.getFigureIcons();
	assertSame(19, actualList.size());
    }

    /**
     * Test method for {@link core.RessourceCache#getFigureIcons()}.
     */
    @Test
    public void testGetFigureIconsElseCondition() {
	testCache.scanDir();
	testCache.loadFigureIcons();
	List<BufferedImage> actualList = testCache.getFigureIcons();
	assertSame(19, actualList.size());
    }

    /**
     * Test method for {@link core.RessourceCache#getLanguage(java.lang.String)}
     * .
     */
    @Test
    public void testGetLanguage() {
	Map<String, String> actualMap = testCache.getLanguage("English");
	assertEquals("Expected String", "Gamemode", actualMap.get("gMTitel"));
    }

    /**
     * Test method for {@link core.RessourceCache#getLanguageNames()}.
     */
    @Test
    public void testGetLanguageNames() {
	List<String> expectedList = stubCache.getLanguageNames();
	List<String> actualList = testCache.getLanguageNames();
	assertEquals("Expected amount", expectedList.size(), actualList.size());
    }

    /**
     * Test method for {@link core.RessourceCache#getMap()}.
     */
    @Test
    public void testGetMapNull() {
	BufferedImage actualImage = testCache.getMap();
	assertNull(actualImage);
    }

    /**
     * Test method for {@link core.RessourceCache#getMapIniPaths()}.
     */
    @Test
    public void testGetMapIniPathsIfCondition() {
	testCache.scanDir();
	String[] actualArray = testCache.getMapIniPaths();
	assertNotNull(actualArray);
    }

    /**
     * Test method for {@link core.RessourceCache#getMapIniPaths()}.
     */
    @Test
    public void testGetMapIniPathsElseCondition() {
	String[] actualArray = testCache.getMapIniPaths();
	assertNull(actualArray);
    }

    /**
     * Test method for {@link core.RessourceCache#getMapNames()}.
     */
    @Test
    public void testGetMapNames() {
	List<String> expectedList = stubCache.getMapNames();
	List<String> actualList = testCache.getMapNames();
	assertEquals("Expected list", expectedList, actualList);
    }

    /**
     * Test method for {@link core.RessourceCache#loadConfig(int)}.
     */
    @Test
    public void testLoadConfigFalseParam() {
	assertFalse(testCache.loadConfig(-1));
    }

    /**
     * Test method for {@link core.RessourceCache#loadConfig(int)}.
     */
    @Test
    public void testLoadConfigTrueParam() {
	testCache.scanDir();
	assertTrue(testCache.loadConfig(0));
    }

    /**
     * Test method for {@link core.RessourceCache#loadCoordinates()}.
     */
    @Test
    public void testLoadCoordinatesFalse() {
	assertFalse(testCache.loadCoordinates());
    }

    /**
     * Test method for {@link core.RessourceCache#loadCoordinates()}.
     */
    @Test
    public void testLoadCoordinatesTrue() {
	testCache.scanDir();
	testCache.loadConfig(0);
	assertTrue(testCache.loadCoordinates());
    }

    /**
     * Test method for {@link core.RessourceCache#loadFigureIcons()}.
     */
    @Test
    public void testLoadFigureIconsFalse() {
	assertFalse(testCache.loadFigureIcons());
    }

    /**
     * Test method for {@link core.RessourceCache#loadFigureIcons()}.
     */
    @Test
    public void testLoadFigureIconsTrue() {
	testCache.scanDir();
	assertTrue(testCache.loadFigureIcons());
    }

    /**
     * Test method for {@link core.RessourceCache#loadLanguages()}.
     */
    @Test
    public void testLoadLanguages() {
	testCache.loadLanguages();
	assertNotNull(testCache.lang);
    }

    /**
     * Test method for {@link core.RessourceCache#loadLinks()}.
     */
    @Test
    public void testLoadLinksFalse() {
	assertFalse(testCache.loadLinks());
    }

    /**
     * Test method for {@link core.RessourceCache#loadLinks()}.
     */
    @Test
    public void testLoadLinksTrue() {
	testCache.scanDir();
	testCache.loadConfig(0);
	assertTrue(testCache.loadLinks());
    }

    /**
     * Test method for {@link core.RessourceCache#loadMap()}.
     */
    @Test
    public void testLoadMapFalse() {
	assertFalse(testCache.loadMap());
    }

    /**
     * Test method for {@link core.RessourceCache#loadMap()}.
     */
    @Test
    public void testLoadMapTrue() {
	testCache.scanDir();
	testCache.loadConfig(0);
	assertTrue(testCache.loadMap());
    }

    /**
     * Test method for
     * {@link core.RessourceCache#getConfigProperties(java.lang.String)}.
     */
    @Test
    public void testGetConfigPropertiesFileNotFound() {
	Properties actualProperties = testCache.getConfigProperties("");
	assertTrue(actualProperties.isEmpty());
    }

    /**
     * Test method for
     * {@link core.RessourceCache#getConfigProperties(java.lang.String)}.
     */
    @Test
    public void testGetConfigProperties() {
	Properties actualProperties = testCache
		.getConfigProperties("./mediaTest/maps/sample.ini");
	assertEquals("luebeck_coordinates.txt",
		actualProperties.get("coordinates"));
    }

    /**
     * Test method for
     * {@link core.RessourceCache#convertArrayToList(java.lang.String[])}.
     */
    @Test
    public void testConvertArrayToListException() {
	String[] paramArray = new String[] { "&56" };
	List<Integer> actualList = testCache.convertArrayToList(paramArray);
	assertNull(actualList);
    }

    /**
     * Test method for
     * {@link core.RessourceCache#convertArrayToList(java.lang.String[])}.
     */
    @Test
    public void testConvertArrayToList() {
	String[] paramArray = new String[] { "24", "23", "-1" };
	List<Integer> expectedList = stubCache.convertArrayToList(null);
	List<Integer> actualList = testCache.convertArrayToList(paramArray);
	assertEquals(expectedList, actualList);
    }

}
