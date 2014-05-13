/**
 * 
 */
package events.logic.global;

import java.util.Map;

import events.AEvent;

/**
 * Event to change the language of the gameView.
 * 
 * @author Fabian
 */
public class ELanguageSwitched extends AEvent {

	private static final long serialVersionUID = -3282047882521720161L;
	private Map<String, String> language;

	/**
	 * Create event to change the language of the gameView.
	 * 
	 * @param source
	 *            Event source
	 * @param eventType
	 *            Event id integer
	 * @param language
	 *            Map with language translations
	 */
	public ELanguageSwitched(Object source, int eventType,
			Map<String, String> language) {
		super(source, eventType);
		this.language = language;
	}

	/**
	 * Get language map.
	 * 
	 * @return Language map
	 */
	public Map<String, String> getLanguage() {
		return language;
	}

}
