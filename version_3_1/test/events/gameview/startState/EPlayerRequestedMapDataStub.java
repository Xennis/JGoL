/**
 * 
 */
package events.gameview.startState;

/**
 * @author Fabian
 * 
 */
public class EPlayerRequestedMapDataStub implements IEPlayerRequestedMapData {

    /*
     * (non-Javadoc)
     * 
     * @see events.gameView.startState.IEPlayerRequestedMapData#getMapName()
     */
    @Override
    public String getMapName() {
	return "Gangs of Lübeck";
    }

}
