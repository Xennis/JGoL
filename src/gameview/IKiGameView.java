package gameview;

/**
 * The KIGameView is the viewport of the KI. It does use the same events as a
 * normal player.
 * 
 * @version 3.0
 * @author Jasper S.
 * 
 */
public interface IKiGameView extends IGameView {

	/**
	 * Check ki gameView is active.
	 * 
	 * @return returns true if the ki is active
	 */
	public abstract boolean isActive();

	/**
	 * Destroy all current threads and all data references
	 */
	public abstract void destroy();

	/**
	 * Enable or disable the ki gameView.
	 * 
	 * @param isActive
	 *            is a boolean which enables the Ki
	 */
	public abstract void setActive(boolean isActive);

}