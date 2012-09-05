/**
 * 
 */
package core;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import core.msgpump.IMsgPump;
import core.msgpump.MsgPumpStub;

/**
 * @author Fabian
 * 
 */
public class FileScannerTest {

    private IMsgPump stubMsgPump;

    @Before
    public void init() {
	stubMsgPump = new MsgPumpStub();
    }

    /**
     * Test method for
     * {@link core.FileScanner#scanDir(java.lang.String, java.lang.String, core.msgpump.IMsgPump)}
     * .
     */
    @Test
    public void testScanDirEmptyData() {
	List<String> actualList = FileScanner.scanDir("", "", null);
	assertTrue("Expected empty list", actualList.isEmpty());
    }

    /**
     * Test method for
     * {@link core.FileScanner#scanDir(java.lang.String, java.lang.String, core.msgpump.IMsgPump)}
     * .
     */
    @Test
    public void testScanDirFalseData() {
	List<String> actualList = FileScanner.scanDir("NoDir", "lang",
		stubMsgPump);
	assertTrue("Expected empty list", actualList.isEmpty());
    }

    /**
     * Test method for
     * {@link core.FileScanner#scanDir(java.lang.String, java.lang.String, core.msgpump.IMsgPump)}
     * .
     */
    @Test
    public void testScanDirTrueData() {
	List<String> expectedList = FileScannerStub.scanDir(null, null);
	List<String> actualList = FileScanner.scanDir("./mediaTest", "lang",
		stubMsgPump);
	assertEquals("Expected amount", expectedList.size(), actualList.size());
    }
}
