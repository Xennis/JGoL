package events.gameview.global;

import events.AEvent;

/**
 * Send if language switch is requested. Gets the language name, which is used
 * to load the requested language.
 * 
 * @author Fabian R.
 * @version 1.0
 */
public class ERequestedLanguageSwitch extends AEvent {

	private static final long serialVersionUID = -7530343207150415797L;
	private String languageName;

	/**
	 * Event to change language.
	 * 
	 * @param source
	 *            Event source
	 * @param eventType
	 *            Event id integer
	 * @param languageName
	 *            Name of language
	 */
	public ERequestedLanguageSwitch(Object source, int eventType,
			String languageName) {
		super(source, eventType);
		this.languageName = languageName;
	}

	/**
	 * Get name of new language.
	 * 
	 * @return language name
	 */
	public String getLanguageName() {
		return languageName;
	}

}
