package events.gameview.global;

import events.AEvent;

/**
 * Delivers the toggle of the check box in the gameModePanel which activates or
 * deactivates the pos. thief position function.
 * 
 * @author Raphael A.
 * @version 3.0
 * 
 */
public class EPossibleThief extends AEvent {

	private static final long serialVersionUID = 3988967952901866320L;

	private boolean toggle = false;

	/**
	 * Create event to active to show possible thief positions.
	 * 
	 * @param source
	 *            Event source
	 * @param eventType
	 *            Event id integer
	 * @param toggle
	 *            True, if toogle
	 */
	public EPossibleThief(Object source, int eventType, boolean toggle) {
		super(source, eventType);
		this.toggle = toggle;
	}

	/**
	 * Get boolean, if possible thief position should shown
	 * 
	 * @return true, if possible thief position should shown
	 */
	public boolean getToggle() {
		return toggle;
	}
}
