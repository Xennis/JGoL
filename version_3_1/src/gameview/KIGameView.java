package gameview;

import logic.Constants;
import core.IStationMap;
import core.ITimer;
import core.Timer;
import core.msgpump.IMsgPump;
import events.IAEvent;
import events.gameview.GVEventGenerator;
import gameview.kIView.AKIBrain;
import gameview.kIView.KIHardBrain;
import gameview.kIView.KIKevinBrain;
import gameview.kIView.Move;

public class KIGameView implements IKiGameView {

	public GVEventGenerator eventGenerator;
	private IMsgPump msgPump;
	private int viewID;
	private int kILevel;
	private AKIBrain brain;
	private ITimer timer;
	private boolean isActive;

	private int currentMoveTime;

	/**
	 * Get a fully initialized KI ready to work
	 * 
	 * @param links
	 *            the link map
	 * @param viewID
	 *            the id of the view
	 * @param msgPump
	 *            a reference to the msgPump for logging
	 * @param kILevel
	 *            the hardness of the KI must be a value from
	 *            {@link logic.Constants}
	 */
	public KIGameView(IStationMap links, int viewID, IMsgPump msgPump,
			int kILevel) {
		eventGenerator = new GVEventGenerator();
		this.msgPump = msgPump;
		timer = new Timer();
		currentMoveTime = 0;
		isActive = false;
		this.viewID = viewID;
		this.kILevel = kILevel;

		if (kILevel == Constants.TYPE_KI_EASY) {
			brain = new KIKevinBrain(links, this);
		} else {
			brain = new KIHardBrain(links, this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameView.IGameView#getViewID()
	 */
	@Override
	public int getViewID() {
		return viewID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameView.IGameView#processEvent(events.IAEvent)
	 */
	@Override
	public void processEvent(IAEvent e) {
		brain.processEvent(e);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameView.IGameView#init()
	 */
	@Override
	public void init() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameView.IGameView#onRender()
	 */
	@Override
	public void onRender() {
		if (isActive) {
			currentMoveTime += timer.getDelta();
			if (currentMoveTime > logic.Constants.WAITTIME) {
				currentMoveTime = 0;
				doMove();
				brain.stopComputing();
				isActive = false;
			}
		}

	}

	/**
	 * doMove is used whenever the waittime is over and a move must be done.
	 */
	private void doMove() {
		Move move = brain.getMove();
		msgPump.logDebug("getting a move: " + move.toString());
		eventGenerator.firePlayerRequestedMove(this, move.getTicket(),
				move.getStation(), move.isUseDouble());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameView.IGameView#getViewType()
	 */
	@Override
	public int getViewType() {
		return kILevel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameView.IKiGameView#isActive()
	 */
	@Override
	public boolean isActive() {
		return isActive;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameView.IKiGameView#setActive(boolean)
	 */
	@Override
	public void setActive(boolean isActive) {
		this.isActive = isActive;
		if (isActive) {
			currentMoveTime = 0;
			timer.getDelta();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameView.IGameView#getMsgPump()
	 */
	@Override
	public IMsgPump getMsgPump() {
		return msgPump;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameView.IKiGameView#destroy()
	 */
	@Override
	public void destroy() {
		eventGenerator.removeAllListener();
	}

}
