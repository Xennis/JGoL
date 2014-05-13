package gameview.kIView;

import core.IStationMap;
import events.IAEvent;
import gameview.IKiGameView;

/**
 * The AKIBrain is used to compute every move of the KI. There are serveral
 * classes which implant this Abstract class and thereby are fully useable KI's
 * 
 * @author Jasper S.
 * @version 3.0
 */
public abstract class AKIBrain {

	IStationMap links;
	IKiGameView kiGV;
	/**
	 * the next move the KI will do.
	 */
	Move move;

	/**
	 * Get a AKIBrain
	 * 
	 * @param links
	 *            the linkmap
	 * @param kiGV
	 *            the KIGameView to get access to the MessagePump
	 */
	public AKIBrain(IStationMap links, IKiGameView kiGV) {
		this.links = links;
		this.kiGV = kiGV;
		move = new Move(null, null, false);
	}

	/**
	 * getMove is called by the KIGameView whenever the waittime is over
	 * 
	 * @return the move the KI will do
	 */
	public abstract Move getMove();

	/**
	 * Set the next move. only to be set by Brains
	 * 
	 * @param move
	 *            the next move
	 */
	protected abstract void setMove(Move move);

	/**
	 * process the Eventdata, as every normal gameview
	 * 
	 * @param e A event
	 */
	public abstract void processEvent(IAEvent e);

	/**
	 * Stop computing, should be called whenever when getMove is called
	 */
	public abstract void stopComputing();

	/**
	 * Get ki gameView.
	 * 
	 * @return ki gameView
	 */
	public IKiGameView getKiGV() {
		return kiGV;
	}

}
