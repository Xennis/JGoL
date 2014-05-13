package core;

import java.util.HashMap;

public class StationMapStub extends HashMap<String, IStation> implements
	IStationMap {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public StationMapStub() {
	this.put("22", new StationStub());
	this.put("23", new Station23Stub());
	this.put("70", new Station70Stub());
    }

}
