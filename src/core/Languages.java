package core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import core.msgpump.IMsgPump;

/**
 * Reads all languages.
 * 
 * @author Raphael A., Fabian, Felix
 * @version 1.0
 */
public class Languages implements ILanguages {

    private static final String LANGUAGE_FILE_EXTENSION = "lang";

    private HashMap<String, HashMap<String, String>> languages;
    private List<String> languageNames;
    private IMsgPump msgPump;

    /**
     * Creates object to manage of languages.
     * 
     * @param msgPump The message pump
     */
	public Languages(IMsgPump msgPump) {
		languages = new HashMap<String, HashMap<String, String>>();
		languageNames = new LinkedList<String>();
		this.msgPump = msgPump;
		init();
	}

    /*
     * (non-Javadoc)
     * 
     * @see core.ILanguages#init()
     */
    @Override
    public void init() {
	List<String> languageFilePaths = core.FileScanner.scanDir(
		IMain.MEDIA_DIR, LANGUAGE_FILE_EXTENSION, msgPump);
	for (String languageFile : languageFilePaths) {
	    try {

		Properties prop = new Properties();
	    BufferedReader str;
	    str = new BufferedReader(new InputStreamReader(new FileInputStream(
		    languageFile), Charset.forName("UTF-8")));
		prop.load(str);
		str.close();

		String langName = prop.getProperty("language");
		languageNames.add(langName);

		languages.put(langName, new HashMap<String, String>());
		fillMap(languages.get(langName), prop);
	    } catch (Exception e) {
		msgPump.logError("Einlesen der Sprachdateien fehlgeschlagen");
	    }
	}
    }

	/**
	 * Reads all properties for selected language
	 * 
	 * @param hMap
	 *            Map for language data
	 * @param prop
	 *            Properties with language data
	 */
    private void fillMap(HashMap<String, String> hMap, Properties prop) {
	Enumeration<?> e = prop.propertyNames();
	while (e.hasMoreElements()) {
	    String dmy = (String) e.nextElement();
	    hMap.put(dmy, prop.getProperty(dmy));
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.ILanguages#getLanguage(String)
     */
    @Override
    public HashMap<String, String> getLanguage(String language) {
	if (!languages.containsKey(language)) {
	    return languages.get(IMain.DEFAULT_LANGUAGE);
	}
	return languages.get(language);
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.ILanguages#getLanguages()
     */
    @Override
    public Map<String, HashMap<String, String>> getLanguages() {
	return languages;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.ILanguages#getLanguageNames()
     */
    @Override
    public List<String> getLanguageNames() {
	return languageNames;
    }
}
