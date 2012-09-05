package logic.player;

import events.IAEvent;

/**
 * This class holds all specific information of a thief player.
 * 
 * @author Jasper S., Fabian R.
 * @version 3.0
 * 
 */
public class PlayerThief extends APlayer implements IPlayerThief {

    /**
     * Create a new player of the type thief.
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
    public PlayerThief(String name, String color, int id, int type, int ownerGV) {
	super(name, color, id, type, ownerGV);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IPlayerThief#processEvent(events.IAEvent)
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
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#isPolice()
     */
    @Override
    public boolean isPolice() {
	return false;
    }
}
