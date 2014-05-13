/**
 * 
 */
package events.logic.startState;

import core.IConfig;
import events.AEvent;
import gameview.IImagePanel;

/**
 * Event to deliver map configuration and image.
 * 
 * @author Fabian R.
 * @version 2.0
 */
public class EDataMapData extends AEvent {

	private static final long serialVersionUID = -2471844902745006749L;

	private IConfig config;
	private IImagePanel mapImage;

	/**
	 * Create Event to deliver map configuration and image.
	 * 
	 * @param source
	 *            Event source
	 * @param eventType
	 *            Event id integer
	 * @param config
	 *            Game configuration of the map
	 * @param mapImage
	 *            Image of the map
	 */
	public EDataMapData(Object source, int eventType, IConfig config,
			IImagePanel mapImage) {
		super(source, eventType);
		this.config = config;
		this.mapImage = mapImage;
	}

	/**
	 * Get configuration of the map.
	 * 
	 * @return configuration of the map
	 */
	public IConfig getConfig() {
		return config;
	}

	/**
	 * Get image of the map.
	 * 
	 * @return Image of the map
	 */
	public IImagePanel getMapImage() {
		return mapImage;
	}
}
