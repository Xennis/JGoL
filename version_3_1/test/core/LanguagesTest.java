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
 * @author Fabi
 * 
 */
public class LanguagesTest {

    private ILanguages testLanguages;
    private ILanguages stubLanguages;
    private IMsgPump stubMsgPump;

    @Before
    public void init() {
	stubMsgPump = new MsgPumpStub();
	stubLanguages = new LanguagesStub();
	testLanguages = new Languages(stubMsgPump);
    }

    /**
     * Test method for {@link core.Languages#init()}.
     */
    @Test
    public void testInit() {
	List<String> expectedString = stubLanguages.getLanguageNames();
	List<String> actualString = testLanguages.getLanguageNames();
	assertEquals("Expected amount", expectedString.size(),
		actualString.size());
    }

    /**
     * Test method for {@link core.Languages#getLanguage(java.lang.String)}.
     */
    @Test
    public void testGetLanguageFalseData() {
	String expectedString = "Gamemode";
	String actualString = testLanguages.getLanguage("noLang")
		.get("gMTitel");
	assertEquals("Expected string", expectedString, actualString);
    }

    /**
     * Test method for {@link core.Languages#getLanguage(java.lang.String)}.
     */
    @Test
    public void testGetLanguageTrueData() {
	String expectedString = "Spiel Modus";
	String actualString = testLanguages.getLanguage("Deutsch").get(
		"gMTitel");
	assertEquals("Expected string", actualString, expectedString);
    }
}
