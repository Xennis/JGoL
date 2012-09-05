package logic.player;

import events.IAEvent;

/**
 * This class holds all specific information of a police player.
 * 
 * @author Jasper S., Fabian R.
 * @version 3.0
 * 
 */
public class PlayerPolice extends APlayer implements IPlayerPolice {

    /**
     * Create a new player of the type police.
     * 
     * @param name
     *            Player name
     * @param color
     *            Player color as String
     * @param id
     *            Player identification integer
     * @param type
     *            Player type
     * @param ownerGV
     *            Game view integer
     */
    public PlayerPolice(String name, String color, int id, int type, int ownerGV) {
	super(name, color, id, type, ownerGV);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IPlayerPolice#processEvent(events.IAEvent)
     */
    @Override
    public void processEvent(IAEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#isThief()
     */
    @Override
    public boolean isThief() {
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#isPolice()
     */
    @Override
    public boolean isPolice() {
	return true;
    }

}
