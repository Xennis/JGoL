package events.gameview.startState;

import events.AEvent;

/**
 * Send if the map is chosen. Contains the chosen map. If this event is fired
 * the Data_Map_Data event will be fired.
 * 
 * @author Raphael A.
 * @version 2.0
 * 
 */
public class EPlayerRequestedMapData extends AEvent implements
		IEPlayerRequestedMapData {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mapName;

	/**
	 * Create Event to request data of a map.
	 * 
	 * @param source
	 *            Event source
	 * @param eventType
	 *            Event id integer
	 * @param mapName
	 *            Name of the map
	 */
	public EPlayerRequestedMapData(Object source, int eventType, String mapName) {
		super(source, eventType);
		this.mapName = mapName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.gameView.startState.IEPlayerRequestedMapData#getMapName()
	 */
	@Override
	public String getMapName() {
		return mapName;
	}

}
