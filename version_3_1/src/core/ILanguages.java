package core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Interface for {@link core.Languages}.
 * 
 * @author Fabian
 *
 */
public interface ILanguages {

    /**
     * initialize all class variables, search for language files and read them,
     * after this method is called the languages are stored and the getters are
     * useable
     */
    public abstract void init();

    /**
     * get a specific language by using its name as parameter, undefined
     * languages will result in getting the default language
     * 
     * @param language
     *            the languages name as string
     * @return the Hashmap of the corresponding language, does return
     *         DEFAULT_LANGUAGE (English) if language is not found.
     */
    public abstract HashMap<String, String> getLanguage(String language);

    /**
     * get the names of all found languages
     * 
     * @return a list with all the language name strings
     */
    public abstract List<String> getLanguageNames();

    /**
     * get all the languages as Map<String, HashMap<String, String> the key of
     * the outer Map is the name of the Language e.g. "English" the Key of the
     * inner one are field-identifiers like "menu_info"
     * 
     * @return all languages
     */
    public abstract Map<String, HashMap<String, String>> getLanguages();

}