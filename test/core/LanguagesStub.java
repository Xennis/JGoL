/**
 * 
 */
package core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Fabi
 * 
 */
public class LanguagesStub implements ILanguages {

    /*
     * (non-Javadoc)
     * 
     * @see core.ILanguages#init()
     */
    @Override
    public void init() {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.ILanguages#getLanguage(java.lang.String)
     */
    @Override
    public HashMap<String, String> getLanguage(String language) {
	HashMap<String, String> lang = new HashMap<String, String>();
	lang.put("next", "Next");
	lang.put("abort", "Abort");
	lang.put("back", "Back to menu");
	lang.put("chooseSavegame", "Choose savegame");
	lang.put("gameName", "Gangs of Lübeck");
	lang.put("newGame", "New game");
	lang.put("loadGame", "Load game");
	lang.put("exitGame", "Exit game");
	lang.put("gMTitel", "Gamemode");
	lang.put("gMMaps", "Choose your map");
	lang.put("gMSettings", "Settings");
	lang.put("gMNumberOfPlayers", "Number of players");
	lang.put("gMRounds", "round");
	lang.put("gMPlayer", "players");
	lang.put("gMMapPreview", "Map preview");
	lang.put("gMMapInfo", "Map info");
	lang.put("gMTicketTyp", "Ticket types");
	lang.put("gMMaxFigures", "Max. Figurecount");
	return lang;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.ILanguages#getLanguageNames()
     */
    @Override
    public List<String> getLanguageNames() {
	List<String> languageNames = new LinkedList<String>();
	languageNames.add("Deutsch");
	languageNames.add("English");
	return languageNames;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.ILanguages#getLanguages()
     */
    @Override
    public Map<String, HashMap<String, String>> getLanguages() {
	// TODO Auto-generated method stub
	return null;
    }

}
