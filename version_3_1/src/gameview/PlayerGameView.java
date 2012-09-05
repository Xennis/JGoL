package gameview;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import logic.Constants;

import core.IMain;
import core.Main;
import core.msgpump.IMsgPump;
import events.EventConstants;
import events.IAEvent;
import events.gameview.GVEventGenerator;
import events.gameview.GVEventListener;
import events.logic.global.ELanguageSwitched;
import gameview.eView.EState;
import gameview.gView.*;
import gameview.generalView.MainWindowListener;
import gameview.generalView.MenuBar;
import gameview.sView.SState;

public class PlayerGameView implements IGameView {

	GState gameState = null;
	SState startState = null;
	EState endState = null;
	MenuBar menus;
	Map<String, String> currentLanguage;
	public GVEventGenerator eventGenerator;
	private IMsgPump msgPump;
	private int viewID;
	private List<IAEvent> evtqueue;

	/**
	 * Create a new player gameView.
	 * 
	 * @param language
	 *            Map with language data
	 * @param languageNames
	 *            List with all lanuages names
	 * @param msgPump
	 *            The message pump
	 * @param viewID
	 *            id of the view
	 */
	public PlayerGameView(Map<String, String> language,
			List<String> languageNames, IMsgPump msgPump, int viewID) {
		this.msgPump = msgPump;
		currentLanguage = language;
		eventGenerator = new GVEventGenerator();
		menus = new MenuBar(this, currentLanguage, languageNames);
		msgPump.addListener(new MainWindowListener(this));
		this.viewID = viewID;
		evtqueue = new LinkedList<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameView.AGameView#onRender()
	 */
	public void onRender() {

		if (core.Main.getInstance().getGameState() == IMain.GAME_STATE_START) {
			processStartSwitch();
		} else if (core.Main.getInstance().getGameState() == IMain.GAME_STATE_PLAY) {
			processGameSwitch();
		} else if (core.Main.getInstance().getGameState() == IMain.GAME_STATE_END) {
			processEndSwitch();
		}
	}

	/**
	 * Switches between the current state to the start state.
	 */
	private void processStartSwitch() {
		if (startState == null) {
			initStartView();
			menus.switchState(0);
		}
		if (gameState != null) {
			gameState.destroy();
			startState.onPanePMenu(true);
			gameState = null;
		}
		if (endState != null) {
			endState.destroy();
			startState.onPanePMenu(true);
			endState = null;
		}
	}


	/**
	 * Switches between the current state to the game state.
	 */
	private void processGameSwitch() {
		if (startState != null) {
			startState = null;
		}
		if (endState != null) {
			endState = null;
		}
		if (gameState == null) {
			initGameView();
			menus.switchState(1);
		}
	}


	/**
	 * Switches between the current state to the end state.
	 */
	private void processEndSwitch() {
		if (gameState != null) {
			gameState.destroy();

			gameState = null;

		}
		if (endState == null) {
			initEndView();
		} else if (evtqueue.size() > 0 && endState != null) {
			for (IAEvent evt : evtqueue) {
				processEvent(evt);

			}
			evtqueue.clear();
		}
		menus.switchState(2);
	}

	/**
	 * Init the game view
	 */
	public void initGameView() {
		gameState = new GState(this, Main.getInstance().getCache()
				.getCoordinates(), Main.getInstance().getCache().getMap(),
				currentLanguage);
		msgPump.logInfo("Switched to gamestate");
	}

	/**
	 * Init the start view
	 */
	public void initStartView() {
		startState = new SState(this, currentLanguage);
		msgPump.logInfo("Switched to startstate");
	}

	/**
	 * Init the end view
	 */
	private void initEndView() {
		endState = new EState(currentLanguage, this);
		msgPump.logInfo("Switched to endstate");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameView.AGameView#processEvent(events.IAEvent)
	 */
	public void processEvent(IAEvent e) {
		if (e.getEventType() == EventConstants.LANGUAGE_SWITCHED) {
			this.processLanguageSwitched((ELanguageSwitched) e);
		} else {
			if (e.getEventType() == EventConstants.PLAYER_WON
					&& endState == null) {
				queueEvt(e);
			}
			if (gameState != null) {
				gameState.processEvent(e);
			}
			if (startState != null) {
				startState.processEvent(e);
			}
			if (endState != null) {

				endState.processEvent(e);
			}
		}
	}

	/**
	 * Queue's the incoming events as long there not processed
	 * @param e the incoming event.
	 */
	private void queueEvt(IAEvent e) {
		evtqueue.add(e);
	}

	/**
	 * If the language is switched it has to be delivered to the current state.
	 * @param e language event. contains the current language map
	 */
	private void processLanguageSwitched(ELanguageSwitched e) {
		msgPump.logInfo("Verarbeite Sprachenwechsel event. Neue Sprache:  "
				+ e.getLanguage().get("language"));
		currentLanguage = e.getLanguage();
		menus.processEvent(e);
		if (gameState != null) {
			gameState.processEvent(e);
		} else if (startState != null) {
			startState.processEvent(e);
		} else if (endState != null) {
			endState.processEvent(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameView.AGameView#init()
	 */
	public void init() {
		// TODO Auto-generated method stub

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

	/**
	 * Add listener to gameView.
	 * 
	 * @param l gameView event listener
	 */
	public void addListener(GVEventListener l) {
		msgPump.logInfo("Habe einen listener hinzugefügt: "
				+ l.getClass().toString());
		eventGenerator.addListener(l);
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
	 * @see gameView.IGameView#getViewType()
	 */
	@Override
	public int getViewType() {
		return Constants.TYPE_PLAYER;
	}

	/**
	 * Enables the show thief function
	 * @param b = true => enable, b = false => disable
	 */
	public void setShowPosThiefs(boolean b) {
		menus.setPossibleThiefsisAllowed(b);
	}

}
