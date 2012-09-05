package events.gameview;

import java.util.Iterator;

import events.EventConstants;
import events.EventGenerator;
import events.IAEvent;
import events.gameview.gameState.EPlayerRequestedMove;
import events.gameview.gameState.EPlayerSurrender;
import events.gameview.global.EEndGame;
import events.gameview.global.ENewGame;
import events.gameview.global.EPossibleThief;
import events.gameview.global.ERequestClose;
import events.gameview.global.ERequestedLanguageSwitch;
import events.gameview.global.EStartGame;
import events.gameview.global.ESwitchGameState;
import events.gameview.startState.EPlayerChosePlayerCount;
import events.gameview.startState.EPlayerRequestedMapData;
import events.gameview.startState.EPlayerSetProperties;
import events.gameview.startState.EPlayerSwitchedToNewGameView;
import gameview.sView.ISPlayer;

/**
 * Event generator to fire gameView events.
 * 
 * @author Jasper S.
 * 
 */
public class GVEventGenerator extends EventGenerator<GVEventListener> implements
		IGVEventGenerator {

	/**
	 * Create a new event generator to fire gameView events.
	 */
	public GVEventGenerator() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.gameView.IGVEventGenerator#fireEndGameEvent(java.lang.Object)
	 */
	@Override
	public void fireEndGameEvent(Object source) {
		EEndGame temp = new EEndGame(source, EventConstants.END_GAME_EVENT);
		fireEvent(temp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.gameView.IGVEventGenerator#fireEvent(events.IAEvent)
	 */
	@Override
	public void fireEvent(IAEvent event) {
		for (Iterator<GVEventListener> i = listeners.iterator(); i.hasNext();) {
			i.next().gVEventHappend(event);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.gameView.IGVEventGenerator#fireNewChosePlayerCountEvent(java.lang
	 * .Object, int, java.lang.Integer[])
	 */
	@Override
	public void fireNewChosePlayerCountEvent(Object source, int playerCount,
			Integer[] specialTicketArray) {
		EPlayerChosePlayerCount temp = new EPlayerChosePlayerCount(source,
				EventConstants.PLAYER_CHOSE_PLAYER_COUNT, playerCount,
				specialTicketArray);
		fireEvent(temp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.gameView.IGVEventGenerator#fireNewGameEvent(java.lang.Object)
	 */
	@Override
	public void fireNewGameEvent(Object source) {
		ENewGame temp = new ENewGame(source, EventConstants.NEW_GAME_EVENT);
		fireEvent(temp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.gameView.IGVEventGenerator#fireNewPlayerRequestedMapData(java.
	 * lang.Object, java.lang.String)
	 */
	@Override
	public void fireNewPlayerRequestedMapData(Object source, String mapName) {
		EPlayerRequestedMapData temp = new EPlayerRequestedMapData(source,
				EventConstants.PLAYER_REQUESTED_MAP_DATA, mapName);
		fireEvent(temp);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.gameView.IGVEventGenerator#fireNewPlayerSetPropertiesEvent(java
	 * .lang.Object, gameView.sView.SPlayer)
	 */
	@Override
	public void fireNewPlayerSetPropertiesEvent(Object source, ISPlayer sPlayer) {
		EPlayerSetProperties temp = new EPlayerSetProperties(source,
				EventConstants.PLAYER_SET_PROPERTIES, sPlayer);
		fireEvent(temp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.gameView.IGVEventGenerator#fireNewPlayerSwitchedToNGV(java.lang
	 * .Object)
	 */
	@Override
	public void fireNewPlayerSwitchedToNGV(Object source) {
		EPlayerSwitchedToNewGameView temp = new EPlayerSwitchedToNewGameView(
				source, EventConstants.PLAYER_SWITCHED_TO_NEWGAME_VIEW);
		fireEvent(temp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.gameView.IGVEventGenerator#fireNewRequestCloseEvent(java.lang.
	 * Object)
	 */
	@Override
	public void fireNewRequestCloseEvent(Object source) {
		ERequestClose temp = new ERequestClose(source,
				EventConstants.REQUESTED_CLOSE);
		fireEvent(temp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.gameView.IGVEventGenerator#fireNewRequestedLanguageSwitch(java
	 * .lang.Object, java.lang.String)
	 */
	@Override
	public void fireNewRequestedLanguageSwitch(Object source,
			String languageName) {
		ERequestedLanguageSwitch temp = new ERequestedLanguageSwitch(source,
				EventConstants.REQUESTED_LANGUAGE_SWITCH, languageName);
		fireEvent(temp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.gameView.IGVEventGenerator#fireNewSwitchStateEvent(java.lang.Object
	 * , int)
	 */
	@Override
	public void fireNewSwitchStateEvent(Object source, int newState) {
		ESwitchGameState temp = new ESwitchGameState(source,
				EventConstants.SWITCH_GAMESTATE_EVENT, newState);
		fireEvent(temp);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.gameView.IGVEventGenerator#firePlayerRequestedMove(java.lang.Object
	 * , java.lang.String, int)
	 */
	@Override
	public void firePlayerRequestedMove(Object source, String ticketType,
			String destination, boolean usedDoubleTicket) {
		EPlayerRequestedMove temp = new EPlayerRequestedMove(source,
				EventConstants.PLAYER_REQUESTED_MOVE, ticketType, destination,
				usedDoubleTicket);
		fireEvent(temp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.gameView.IGVEventGenerator#firePlayerSurrenderEvent(java.lang.
	 * Object)
	 */
	@Override
	public void firePlayerSurrenderEvent(Object source) {
		EPlayerSurrender temp = new EPlayerSurrender(source,
				EventConstants.PLAYER_SURRENDER);
		fireEvent(temp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.gameView.IGVEventGenerator#firePossibleThiefEvent(java.lang.Object
	 * , boolean)
	 */
	@Override
	public void firePossibleThiefEvent(Object source, boolean toggle) {
		EPossibleThief temp = new EPossibleThief(source,
				EventConstants.POSSIBLE_THIEF_EVENT, toggle);
		fireEvent(temp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * events.gameView.IGVEventGenerator#fireStartGameEvent(java.lang.Object)
	 */
	@Override
	public void fireStartGameEvent(Object source) {
		EStartGame temp = new EStartGame(source,
				EventConstants.START_GAME_EVENT);
		fireEvent(temp);
	}
}