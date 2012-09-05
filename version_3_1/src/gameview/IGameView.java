package gameview;

import core.msgpump.IMsgPump;
import events.IAEvent;

public interface IGameView {

	/**
	 * Process a event.
	 * 
	 * @param e A event
	 */
	public abstract void processEvent(IAEvent e);

	/**
	 * Initialize the game view.
	 */
	public abstract void init();

	/**
     * The on render method.
     */
	public abstract void onRender();

	/**
	 * Get the type of the view.
	 * 
	 * @return Type of the view
	 */
	public abstract int getViewType();

	/**
	 * Get the identification integer to the view.
	 * 
	 * @return identification integer to the view
	 */
	public abstract int getViewID();

	/**
	 * Get the messag pump.
	 * 
	 * @return The message pump
	 */
	public abstract IMsgPump getMsgPump();

}
