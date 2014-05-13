package events.logic.startState;

import java.awt.image.BufferedImage;
import java.util.List;

import events.AEvent;

/**
 * Event to deliver information for the data-player-settings-panel.
 * 
 * @author Fabian R.
 * @version 2.0
 */
public class EDataPlayerSettingsPanel extends AEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3806806095314950797L;
	private int playerCount;
	private int maxFigureCount;
	private List<BufferedImage> figureIcons;

	/**
	 * Create Event to deliver information for the data-player-settings-panel.
	 * 
	 * @param source
	 *            Event source
	 * @param eventType
	 *            Event integer id
	 * @param playerCount
	 *            Number of players
	 * @param maxFigureCount
	 *            Maximum number of figures for the map
	 * @param figureIcons
	 *            All available figure icons
	 */
	public EDataPlayerSettingsPanel(Object source, int eventType,
			int playerCount, int maxFigureCount, List<BufferedImage> figureIcons) {
		super(source, eventType);
		this.playerCount = playerCount;
		this.maxFigureCount = maxFigureCount;
		this.figureIcons = figureIcons;
	}

	/**
	 * Get number of players.
	 * 
	 * @return number of players
	 */
	public int getPlayerCount() {
		return playerCount;
	}

	/**
	 * Get maximum number of figures for the map.
	 * 
	 * @return maximum number of figures for the map
	 */
	public int getMaxFigureCount() {
		return maxFigureCount;
	}

	/**
	 * Get all available figure icons.
	 * 
	 * @return List with all available figure icons
	 */
	public List<BufferedImage> getFigureIcons() {
		return figureIcons;
	}

}
