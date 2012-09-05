package events.logic.startState;

import java.util.List;

import events.AEvent;

/**
 * Event to deliver all map names.
 * 
 * @author Fabian R.
 * @version 2.0
 */
public class EDataMapNames extends AEvent {

	private static final long serialVersionUID = 7138807639079509363L;

	private List<String> mapNames;

	/**
	 * Create event to deliver all map names.
	 * 
	 * @param source
	 *            Event source
	 * @param eventType
	 *            Event id integer
	 * @param mapNames
	 *            List with all map names
	 */
	public EDataMapNames(Object source, int eventType, List<String> mapNames) {
		super(source, eventType);
		this.mapNames = mapNames;
	}

	/**
	 * Get list with all map names.
	 * 
	 * @return List with all map names
	 */
	public List<String> getMapNames() {
		return mapNames;
	}

}
