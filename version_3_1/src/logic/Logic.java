package logic;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import logic.figure.IAFigure;
import logic.player.IAPlayer;

import core.IConfig;
import core.IStation;
import core.IStationMap;
import core.IMain;
import core.IRessourceCache;
import core.msgpump.IMsgPump;
import events.EventConstants;
import events.IAEvent;
import events.gameview.GVEventListener;
import events.gameview.gameState.EPlayerRequestedMove;
import events.gameview.gameState.EPlayerSurrender;
import events.gameview.global.ENewGame;
import events.gameview.global.EPossibleThief;
import events.gameview.global.ERequestClose;
import events.gameview.global.ERequestedLanguageSwitch;
import events.gameview.global.ESwitchGameState;
import events.gameview.startState.EPlayerChosePlayerCount;
import events.gameview.startState.EPlayerRequestedMapData;
import events.gameview.startState.EPlayerSetProperties;
import events.gameview.startState.EPlayerSwitchedToNewGameView;
import events.logic.ILogicEventGenerator;
import events.logic.LogicEventGenerator;
import events.logic.LogicEventListener;
import events.logic.gameState.IEPlayerMoved;
import events.logic.gameState.INextFigureEventData;
import events.logic.gameState.NextFigureEventData;
import gameview.ImagePanel;
import gameview.KIGameView;
import gameview.PlayerGameView;
import gameview.sView.ISPlayer;

/**
 * 
 * @author Raphael A.,Jasper S.,Fabian R., Felix K.
 * @version 3.0
 */
public class Logic implements LogicEventListener, GVEventListener, ILogic {

	private boolean noGUI;
	/** The {@link core.IRessourceCache} */
	private IRessourceCache cache;

	/** The message pump */
	private IMsgPump msgPump;

	/** The {@link logic.IGameViewManager} */
	private IGameViewManager gVManager;

	/** List with {@link events.AEvent} */
	List<IAEvent> eventList;

	/** The {@link logic.IActorController} */
	volatile IActorController actorCont;

	/** The {@link events.logic.LogicEventGenerator} */
	ILogicEventGenerator logicEG;

	/** The {@link core.IStation} */
	IStationMap links;

	/** The {@link logic.IGameLog} */
	private IGameLog log;

	/** The {@link logic.figure.IAFigure} */
	IAFigure currentFigure;

	boolean initDone;
	private int nextViewID;
	private int roundCounter;
	private int playerCount;

	private int thiefdouble, thiefblack, policedouble, policeblack;
	boolean currentUsedDoubleTicket;
	// showthieves must be field coz its used for log reasons in other methods
	/** The {@link logic.IPositioningEngine} */
	IPositioningEngine positioningE;
	Map<String, Set<String>> currentReachableLinks;

	private Map<Integer, String> showThieves;

	// -------------------------------Construct and
	// Destruct-------------------------------------------------------------

	public Logic(IRessourceCache cache, IMsgPump msgPump, boolean noGUI) {
		logicEG = new LogicEventGenerator();
		logicEG.addListener(this);
		this.noGUI = noGUI;
		this.cache = cache;
		this.msgPump = msgPump;
		gVManager = new GameViewManager(msgPump);
		eventList = new LinkedList<IAEvent>();
		initDone = false;
		nextViewID = 0;
		actorCont = new ActorController();
		playerCount = 0;
		// playerInitializedCount = 0;
		currentFigure = null;
		roundCounter = 0;
		thiefdouble = 0;
		thiefblack = 0;
		policedouble = 0;
		policeblack = 0;
		log = new GameLog();
		currentUsedDoubleTicket = false;
		positioningE = new PositioningEngine(actorCont, null);
		showThieves = new HashMap<Integer, String>();
		currentReachableLinks = new HashMap<String, Set<String>>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logic.ILogic#init(java.util.Map, java.util.List)
	 */
	@Override
	public void init(Map<String, String> language, List<String> languageNames) {
		if (!initDone) {
			if (!noGUI) {
				PlayerGameView initView = new PlayerGameView(language,
						languageNames, msgPump, nextViewID);
				initView.addListener(this);
				nextViewID++;
				gVManager.addGameView(initView);
			}
			initDone = true;
		}
	}

	// -------------------------------GAME DATA
	// CONTROLL----------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see logic.ILogic#onRender()
	 */
	@Override
	public void onRender() {
		gVManager.onRender();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logic.ILogic#processEvents()
	 */
	@Override
	public synchronized void processEvents() {
		for (IAEvent e : eventList) {
			msgPump.logInfo("Processing Event" + e.getEventType());

			actorCont.processEvent(e);
		}
		eventList.clear();
		gVManager.processEvents();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logic.ILogic#queueGVEvent(events.IAEvent)
	 */
	@Override
	public synchronized void queueGVEvent(IAEvent e) {
		eventList.add(e);
	}

	private int addNewKI(int hardNess) {
		KIGameView temp = new KIGameView(links, nextViewID, msgPump, hardNess);
		temp.eventGenerator.addListener(this);
		gVManager.addGameView(temp);
		msgPump.logDebug("KI Hardness: " + hardNess + " " + temp.getViewType());
		return nextViewID++;
	}

	private void initGame() {
		links = cache.getLinks();
		positioningE.setLinks(links);
		for (IAFigure fig : actorCont.getFigureList()) {
			msgPump.logInfoSource(this.getClass().getName(),
					"Figure -> GameView: " + fig.toString());
			logicEG.fireInitNewGameEvent(this, fig.getOwner(), fig);
			// logging a new init game event

		}
		msgPump.logInfoSource(this.getClass().getName(),
				"Figure queue -> GameView: " + actorCont.getFigureQueue());
		logicEG.fireInitNewGameDoneEvent(this, actorCont.getFigureQueue());
		positioningE.init();
		nextMove();
	}

	void destroyCurrentGameData() {
		nextViewID = 1;
		roundCounter = 0;
		currentFigure = null;
		currentReachableLinks = null;
		showThieves.clear();
		actorCont.destroyCurrentGameState();
		eventList.clear();
		log.returnLog().clear();
		log.returnThiefLog().clear();
		gVManager.removeAllKI();
		msgPump.logDebug(" NextViewID: " + nextViewID + " roundCounter: "
				+ roundCounter + " FigureData " + actorCont.getFigureList()
				+ " PlayerData: " + actorCont.getPlayerList());
	}

	private Map<String, Set<String>> getReachableLinks(IAFigure figure) {
		IStation station = links.get(figure.getPosition());
		try {
			Map<String, Set<String>> reachableLinks = station
					.getReachableLinks(figure.getTicketTypesRemaining());

			// The rule for police figures: only one figure at a map point is
			// allowed
			if (figure.isPolice()) {
				for (IAFigure fig : actorCont.getFigureList()) {
					if (fig.isPolice()) {
						String policeFigurePosition = fig.getPosition();
						if (reachableLinks.containsKey(policeFigurePosition)) {
							reachableLinks.remove(policeFigurePosition);
						}
					}
				}
			}

			return reachableLinks;
		} catch (Exception e) {
			return null;
		}
	}

	void nextMove() {

		if (!currentUsedDoubleTicket) {
			currentFigure = actorCont.getNextFigure();
			if (currentFigure.getId() == actorCont.getFigureQueue().get(0)) {
				showThieves.clear();
				logicEG.fireNextRoundEvent(this, ++roundCounter);
			}
		}
		currentReachableLinks = this.getReachableLinks(currentFigure);
		INextFigureEventData data = new NextFigureEventData();
		data.setFigure(currentFigure);
		data.setAllPossiblePositions(positioningE.getAllPossiblePositions());
		data.setPossibleThief(positioningE.getPossiblePositions());
		data.setReachableLinks(currentReachableLinks);
		data.setThiefLog(log.returnThiefLog());
		data.setUseDoubleIsAllowed(!currentUsedDoubleTicket
				&& currentFigure.hasDoubleTicket());
		data.setShowThieves(showThieves);
		logicEG.fireNextFigureEvent(this, data);

		if (currentUsedDoubleTicket) {
			currentUsedDoubleTicket = false;
		}
	}

	// -------------------------------EVENT
	// HANDLING---------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see logic.ILogic#gVEventHappend(events.IAEvent)
	 */
	@Override
	public void gVEventHappend(IAEvent e) {
		if (e.getEventType() < EventConstants.GAMEVIEW_END_GLOBAL) {
			processGameViewGlobalEvent(e);

		} else if (e.getEventType() < EventConstants.GAMEVIEW_END) {
			processGVEvent(e);

		} else {
			queueGVEvent(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logic.ILogic#logicEventHappend(events.IAEvent)
	 */
	@Override
	public void logicEventHappend(IAEvent e) {
		gVManager.queueEvent(e);
		if (e.getEventType() == EventConstants.PLAYER_MOVED_EVENT
				&& currentFigure != null && currentFigure.isThief()) {

			if (cache.getConfig().getShowat()
					.contains(currentFigure.getNumMoves())) {
				positioningE.updatePosition(
						((IEPlayerMoved) e).getNewPosition(),
						currentFigure.getId());
			} else {
				positioningE.moveHappend((IEPlayerMoved) e,
						currentFigure.getId());
			}
		}

	}

	// -----------------GAMEVIEW GLOBAL
	// EVENTs-------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see logic.ILogic#processGameViewGlobalEvent(events.IAEvent)
	 */
	@Override
	public void processGameViewGlobalEvent(IAEvent e) {
		switch (e.getEventType()) {
		case EventConstants.NEW_GAME_EVENT:
			this.processGlobalNewGame((ENewGame) e);
			break;
		case EventConstants.REQUESTED_CLOSE:
			this.processGlobalRequestedClose((ERequestClose) e);
			break;
		case EventConstants.REQUESTED_LANGUAGE_SWITCH:
			processGlobalRequestedLanguageSwitch((ERequestedLanguageSwitch) e);
			break;
		case EventConstants.SWITCH_GAMESTATE_EVENT:
			this.processGlobalSwitchGameEvent((ESwitchGameState) e);
			break;
		case EventConstants.POSSIBLE_THIEF_EVENT:
			processGlobalPossibleThiefEvent((EPossibleThief) e);
			break;
		default:
			break;
		}
	}

	/**
	 * Process {@link events.gameview.global.ENewGame} event: Destroy old game
	 * data and change GameState.
	 * 
	 * @param e
	 *            The {@link events.gameview.global.ENewGame} event
	 */
	void processGlobalNewGame(ENewGame e) {
		this.destroyCurrentGameData();
		msgPump.switchToStartState();
	}

	/**
	 * Process {@link events.gameview.global.ERequestClose} event.
	 * 
	 * @param e
	 *            The {@link events.gameview.global.ERequestClose} event
	 */
	private void processGlobalRequestedClose(ERequestClose e) {
		msgPump.requestShutdown();
	}

	/**
	 * Process {@link events.gameview.global.ESwitchGameState} event.
	 * 
	 * @param e
	 *            The {@link events.gameview.global.ESwitchGameState} event
	 */
	private void processGlobalSwitchGameEvent(ESwitchGameState e) {
		switch (e.getGameState()) {
		case (IMain.GAME_STATE_START):
			msgPump.switchToStartState();
			break;
		case (IMain.GAME_STATE_PLAY):
			msgPump.switchtoGameState();
			break;
		case (IMain.GAME_STATE_END):
			msgPump.switchToEndState();
			break;
		default:
			break;
		}

	}

	/**
	 * Process {@link events.gameview.global.ERequestedLanguageSwitch} event:
	 * Fire new {@link events.logic.global.ELanguageSwitched} event to GameView
	 * 
	 * @param e
	 *            The {@link events.gameview.global.ERequestedLanguageSwitch}
	 *            event
	 */
	private void processGlobalRequestedLanguageSwitch(ERequestedLanguageSwitch e) {
		logicEG.fireNewLanguageSwitchedEvent(e.getSource(),
				cache.getLanguage(e.getLanguageName()));
	}

	/**
	 * Process {@link events.gameview.global.EPossibleThief} event.
	 * 
	 * @param e The {@link events.gameview.global.EPossibleThief} event
	 */
	private void processGlobalPossibleThiefEvent(EPossibleThief e) {
		logicEG.firePossibleThiefEvent(this, e);
	}

	// ----------------------------GAME VIEW
	// EVENTS----------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see logic.ILogic#processGVEvent(events.IAEvent)
	 */
	@Override
	public void processGVEvent(IAEvent e) {
		switch (e.getEventType()) {
		// StartState (sorted by name)
		case EventConstants.PLAYER_CHOSE_PLAYER_COUNT:
			this.processGvPlayerChosePlayerCount((EPlayerChosePlayerCount) e);
			break;
		case EventConstants.PLAYER_REQUESTED_MAP_DATA:
			this.processGvPlayerRequestedMapData((EPlayerRequestedMapData) e);
			break;
		case EventConstants.PLAYER_SET_PROPERTIES:
			this.processGvPlayerSetProperties((EPlayerSetProperties) e);
			break;
		case EventConstants.PLAYER_SWITCHED_TO_NEWGAME_VIEW:
			processGvPlayerSwitchedToNewGameView((EPlayerSwitchedToNewGameView) e);
			break;
		// GameState
		case EventConstants.PLAYER_REQUESTED_MOVE:
			processGvPlayerRequestedMove((EPlayerRequestedMove) e);
			break;
		case EventConstants.PLAYER_SURRENDER:
			processGvPlayerSurrender((EPlayerSurrender) e);
			break;
		// Other
		default:
			queueGVEvent(e);
		}
	}

	/**
	 * Process {@link events.gameview.startState.EPlayerChosePlayerCount} event:
	 * Fire new {@link events.logic.startState.EDataPlayerSettingsPanel} event
	 * to GameView with all data for player settings panel
	 * 
	 * @param e
	 *            The {@link events.gameview.startState.EPlayerChosePlayerCount}
	 *            event
	 */
	private void processGvPlayerChosePlayerCount(EPlayerChosePlayerCount e) {
		this.playerCount = e.getPlayerCount();
		msgPump.logInfoSource(this.getClass().getName(),
				"Player count:" + e.getPlayerCount());
		logicEG.fireDataPlayerSettingsPanelEvent(e.getSource(),
				e.getPlayerCount(), cache.getConfig().getStartat().size(),
				cache.getFigureIcons());

		Integer[] tempArray = e.getSpecialTicketArray();
		thiefdouble = tempArray[0];
		thiefblack = tempArray[1];
		policedouble = tempArray[2];
		policeblack = tempArray[3];
	}

	/**
	 * Process {@link events.gameview.startState.EPlayerSetProperties} event:
	 * Check data and create new player via playerManager. Add all figures to
	 * player. Check if a new game can be initialized.
	 * 
	 * @param e
	 *            The {@link events.gameview.startState.EPlayerSetProperties}
	 *            event
	 */
	void processGvPlayerSetProperties(EPlayerSetProperties e) {
		ISPlayer sPlayer = e.getSPlayer();
		BufferedImage icon = cache.getFigureIcons().get(sPlayer.getIcon());
		Map<String, Integer> tokens = cache.getConfig().getTokens(
				sPlayer.getType());
		if (sPlayer.isKiPlayer()) {
			actorCont.addPlayerWithFigures(sPlayer,
					addNewKI(sPlayer.getBotOrPlayer()), tokens, icon);
		} else {
			actorCont.addPlayerWithFigures(sPlayer, Constants.TYPE_PLAYER,
					tokens, icon);
		}

		if (playerCount == actorCont.getNumPlayer()) {
			addSpecialTickets();
			initGame();
		}
	}

	private void addSpecialTickets() {
		for (IAFigure figure : actorCont.getFigureList()) {
			if (figure.isPolice()) {
				figure.addTicket(Constants.TICKET_BLACK, policeblack);
				figure.addTicket(Constants.TICKET_DOUBLE, policedouble);
			} else {
				figure.addTicket(Constants.TICKET_BLACK, thiefblack);
				figure.addTicket(Constants.TICKET_DOUBLE, thiefdouble);
			}
		}
	}

	/**
	 * Check if a move is allowed.
	 * 
	 * @param e
	 *            EPlayerRequestedMove Event
	 * @return true, if move is allowed
	 */
	boolean checkMoveIsAllowed(EPlayerRequestedMove e) {

		if (currentReachableLinks == null) {
			return false;
		}
		// Check: figure can not move
		if (currentReachableLinks.isEmpty()) {
			return false;
		}
		// Check: tries to use double ticket, but has no double ticket
		else if (e.usedDoubleTicket() && !currentFigure.hasDoubleTicket()) {
			return false;
		}
		// Check: link exists and figure has ticket type
		else if (currentReachableLinks.containsKey(e.getDestination())
				&& currentReachableLinks.get(e.getDestination()).contains(
						e.getTicketType())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check show thief.
	 */
	void checkShowThief() {
		if (currentFigure != null && currentFigure.isThief()) {
			List<Integer> showat = cache.getConfig().getShowat();
			if (showat.contains(currentFigure.getNumMoves())) {
				showThieves.put(currentFigure.getId(),
						currentFigure.getPosition());
			}
		}
	}

	/**
	 * Process {@link events.gameview.gameState.EPlayerRequestedMove} event.
	 * 
	 * @param e
	 *            The {@link events.gameview.gameState.EPlayerRequestedMove}
	 *            event
	 */
	void processGvPlayerRequestedMove(EPlayerRequestedMove e) {
		String logVal = logPlayerMove(e, false);
		if (!checkMoveIsAllowed(e) && actorCont.getFigureList().isEmpty()) {
			return;
		}
		if (checkMoveIsAllowed(e)) {
			if (e.usedDoubleTicket()) {
				currentUsedDoubleTicket = true;
				currentFigure.usedDoubleTicket();
			}
			currentFigure.setPosition(e.getDestination(), e.getTicketType());

			if (currentFigure.isPolice()) {
				actorCont.processEvent(e);
			} else {
				log.updateThiefLog(logPlayerMove(e, true));
			}
			checkCollisions();
			checkShowThief();

			log.updateLog(logVal);
			logicEG.firePlayerMovedEvent(this, e.getDestination(),
					e.getTicketType());
		}

		if (!checkEndGame()) {
			nextMove();
		}
	}

	/**
	 * Helper method to generate logvalues
	 * 
	 * @param e
	 *            the passed eventobject with logdata
	 * @param thief
	 *            set true to return a thieflog, false for a normal log entry
	 * @return Returns the logvalue for a playermove
	 */
	private String logPlayerMove(EPlayerRequestedMove e, boolean thief) {
		try {
			if (thief) {
				return "<1> " + roundCounter + ", <2> "
						+ currentFigure.getOwner().getName() + ", <3> "
						+ currentFigure.getId() + ": " + "<4> "
						+ e.getTicketType();

			} else {
				return "Round " + roundCounter + ", Player "
						+ currentFigure.getOwner().getId() + " ("
						+ currentFigure.getOwner().getName() + ")"
						+ ", Figure " + currentFigure.getId() + ": "
						+ "moved from " + currentFigure.getPosition() + " to "
						+ e.getDestination() + " with ticket "
						+ e.getTicketType();
			}
		} catch (NullPointerException ex) {
			msgPump.logError("Nullpointer während der Logverarbeitung von move in "
					+ ex.getStackTrace().toString());
		}
		return "";
	}

	/**
	 * Process {@link events.gameview.gameState.EPlayerSurrender} event: Remove
	 * player and all his figures from figure manager.
	 * 
	 * @param e
	 *            The {@link events.gameview.gameState.EPlayerSurrender} event
	 */
	private void processGvPlayerSurrender(EPlayerSurrender e) {
		String value = "";
		try {
			value = "Round " + roundCounter + ", Player "
					+ currentFigure.getOwner().getId() + " ("
					+ currentFigure.getOwner().getName() + ")" + ", Figure "
					+ currentFigure.getId() + ": "
					+ "Surrender, Removing all players ("
					+ currentFigure.getOwner().getFigures().toString() + ")";
		} catch (NullPointerException ex) {
			msgPump.logError("Error during logging of surrender-event at"
					+ this.getClass());
		}
		log.updateLog(value);

		IAPlayer tempCurrentFigureOwner = currentFigure.getOwner();
		int tempFigureQueuePosition = 0;
		for (int i = 0; i < actorCont.getFigureList().size(); i++) {
			if (actorCont.getFigureList().get(i).getOwner()
					.equals(tempCurrentFigureOwner)) {
				tempFigureQueuePosition = i;
				break;
			}
		}
		List<IAFigure> tempList = tempCurrentFigureOwner.getFigures();
		while (!tempList.isEmpty()) {
			logicEG.fireNewFigureKilledEvent(this, tempList.get(0).getId());
			actorCont.removeFigure(tempList.get(0));
		}
		actorCont.setCurrentQueuePosition(tempFigureQueuePosition);

		if (!checkEndGame()) {
			nextMove();
		}

	}

	/**
	 * Process {@link events.gameview.startState.EPlayerRequestedMapData} event:
	 * Load map in cache and fire {@link events.logic.startState.EDataMapData}
	 * event to GameView
	 * 
	 * @param e
	 *            The {@link events.gameview.startState.EPlayerRequestedMapData}
	 *            event
	 */
	void processGvPlayerRequestedMapData(EPlayerRequestedMapData e) {
		IConfig config = cache.getConfigByName(e.getMapName());
		cache.loadMap();
		cache.loadCoordinates();
		cache.loadLinks();
		links = cache.getLinks();
		actorCont.setStartAt(cache.getConfig().getStartat());
		ImagePanel mapImage = new ImagePanel(cache.getMap());
		logicEG.fireNewDataMapDataEvent(e.getSource(), config, mapImage);
		e.processed();
	}

	/**
	 * Process {@link events.gameview.startState.EPlayerSwitchedToNewGameView}
	 * event: Fire new {@link events.logic.startState.EDataMapNames} event to
	 * GameView
	 * 
	 * @param e
	 *            The
	 *            {@link events.gameview.startState.EPlayerSwitchedToNewGameView}
	 *            event
	 */
	void processGvPlayerSwitchedToNewGameView(EPlayerSwitchedToNewGameView e) {
		logicEG.fireNewDataMapNamesEvent(e.getSource(), cache.getMapNames());
	}

	// ---------------------------------GAME
	// RULES--------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see logic.ILogic#checkCollisions()
	 */
	@Override
	public void checkCollisions() {
		List<IAFigure> tempFigureList = new LinkedList<IAFigure>();
		if (currentFigure.isPolice()) {
			tempFigureList.addAll(actorCont.getFigureThiefList());
		} else if (currentFigure.isThief()) {
			tempFigureList.addAll(actorCont.getFigurePoliceList());
		}

		for (IAFigure figure : tempFigureList) {
			if (currentFigure != null
					&& figure.getPosition().equals(currentFigure.getPosition())) {
				handleCollision(figure);
			}
		}

	}

	private void handleCollision(IAFigure figure) {
		String value = "";
		try {
			value = "Round " + roundCounter + ", Player "
					+ currentFigure.getOwner().getId() + ", Figure "
					+ currentFigure.getId() + ": "
					+ "Collision happened with Player "
					+ figure.getOwner().getName() + ", Figure "
					+ figure.getId() + ", deleting Figure "
					+ currentFigure.getId();
		} catch (NullPointerException e) {
			msgPump.logError("Error during logging collision at"
					+ this.getClass());
		}

		if (currentFigure.isThief() && figure.isPolice()) {
			actorCont.removeFigure(currentFigure);
			logicEG.fireNewFigureKilledEvent(this, currentFigure.getId());

			currentFigure = null;
			currentUsedDoubleTicket = false;
			log.updateLog(value);
		} else if (currentFigure.isPolice() && figure.isThief()) {
			actorCont.removeFigure(figure);
			logicEG.fireNewFigureKilledEvent(this, figure.getId());
		}
	}

	/**
	 * Check the end of game. If the game ended call method to handle game over.
	 * 
	 * @return True, if end of game
	 */
	boolean checkEndGame() {

		// Check (1): All thief figures removed from game (reason: caught by
		// police or surrender)
		if (actorCont.getFigureThiefList().isEmpty()) {
			this.handleGameOver(true, actorCont.getPlayerPoliceNameList());
			return true;
		}

		// Check (2): All police figures removed from game (reason: surrender)
		if (actorCont.getFigurePoliceList().isEmpty()) {
			this.handleGameOver(false, actorCont.getPlayerThiefNameList());
			return true;
		}
		try {
			// Check (3): No police figure can move
			for (IAFigure policeFigure : actorCont.getFigurePoliceList()) {
				if (!getReachableLinks(policeFigure).isEmpty()) {
					return false;
				}
			}
		} catch (NullPointerException e) {
			msgPump.logError("Nullpointer when trying to getreachableLinks for policefigure"
					+ e.getMessage() + " (cause : )" + e.getCause());
			return false;
		}
		this.handleGameOver(false, actorCont.getPlayerThiefNameList());
		return true;
	}

	/**
	 * Handle game over: Switch game state to endState and
	 * {@link events.logic.endState.EPlayerWon} to endState with the winners.
	 * 
	 * @param policeIsWinner
	 *            True, if police team is winner
	 * @param winningPlayers
	 *            {@link java.util.List} with all winning players names
	 */
	private void handleGameOver(boolean policeIsWinner,
			List<String> winningPlayers) {
		msgPump.switchToEndState();
		logicEG.firePlayerWonEvent(this, policeIsWinner, winningPlayers,
				log.returnLog());
	}

}
