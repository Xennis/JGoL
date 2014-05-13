package gameview.kIView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import logic.Constants;

import core.IStation;
import core.IStationMap;
import events.EventConstants;
import events.IAEvent;
import events.logic.gameState.EInitNewGame;
import events.logic.gameState.ENextFigure;
import gameview.IKiGameView;
import gameview.kIView.thiefHardKI.BFS;
/**
 * The Brain of the hard KI, does all stuff required to do a good move i the game.
 * @author Jasper S.
 * @version 4.0.0
 *
 */
public class KIHardBrain extends AKIBrain implements Runnable {

	private Map<Integer, EInitNewGame> figureList;

	private int bfslength = 0;
	private ENextFigure nextFigEventData = null;
	private Thread computingThread;
	private int longestDistance = Integer.MAX_VALUE;

	/**
	 * Just another constructor does nothing special
	 * @since 4.0
	 * @param links the linkmap
	 * @param kiGV the kiGV used to communicate with the msgPump and eventGenerator
	 */
	public KIHardBrain(IStationMap links, IKiGameView kiGV) {
		super(links, kiGV);
		figureList = new HashMap<Integer, EInitNewGame>();
	}

	/**
	 * Get the move the KI should do
	 * @return the move the KI should do
	 */
	@Override
	public synchronized Move getMove() {
		stopComputing();
		return move;
	}


	/* (non-Javadoc)
	 * @see gameView.kIView.AKIBrain#setMove(gameView.kIView.Move)
	 */
	@Override
	protected synchronized void setMove(Move move) {
		this.move = move;
	}

	/**
	 * Process the next event
	 * @param e an JGoL event
	 */
	@Override
	public void processEvent(IAEvent e) {
		if (e.getEventType() == EventConstants.NEXT_FIGURE_EVENT
				&& figureList.get(((ENextFigure) e).getFigureId()).getOwnerGV() == kiGV
						.getViewID()) {
			ENextFigure temp = (ENextFigure) e;
			kiGV.setActive(true);
			nextFigEventData = temp;
			randomMove();
			if (temp.getReachableLinks().isEmpty()) {
				move = new Move("none", temp.getPosition(), false);
				return;
			}
			computingThread = new Thread(this);
			computingThread.start();

		} else if (e.getEventType() == EventConstants.INIT_NEW_GAME) {
			Integer figureID = ((EInitNewGame) e).getFigureId();

			figureList.put(figureID, (EInitNewGame) e);
		}

	}

	/**
	 * Take the reachableLinks and do a randomMove
	 */
	public void randomMove() {
		if (nextFigEventData.getReachableLinks().keySet().isEmpty()) {
			move = new Move("none", nextFigEventData.getPosition(), false);
			return;
		}
		Random rand = new Random();
		List<String> keyList = new LinkedList<>(nextFigEventData
				.getReachableLinks().keySet());
		int randomKey = rand.nextInt(keyList.size());

		String station = keyList.get(randomKey);

		List<String> tickets = new LinkedList<>(nextFigEventData
				.getReachableLinks().get(station));
		int randomTicket = rand.nextInt(tickets.size());
		String ticket = tickets.get(randomTicket);
		boolean usedouble = false;
		if (nextFigEventData.useDoubleTicketIsAllowed()) {
			int randomDouble = rand.nextInt(1);
			if (randomDouble == 1) {
				usedouble = true;
			}
		}
		move = new Move(ticket, station, usedouble);
	}

	/**
	 * Stop all threads
	 */
	@Override
	public synchronized void stopComputing() {
		computingThread.interrupt();

	}

	// -----------------------------------------------ALGORITHMS

	/**
	 * This method is an easy BFS, it scan's the Map for the shortest way,
	 * regardless which Tickets are used. This is only limited by the depth,
	 * which should be the amout of all tickets a figure has.
	 * 
	 * @param from
	 *            from where?
	 * @param set
	 *            the targetedPositions
	 * @param depth
	 *            maximal depth
	 * @return a sorted LinkedList with the shortest way on the map
	 * @throws NoPathFoundException
	 *             If there is no Path from 'from' to 'to' this exception is
	 *             thrown
	 * @since 2.9
	 */
	public List<String> bfsAllThiefs(String from, Set<String> set, int depth)
			throws NoPathFoundException {
		// The map with the visited Stations and the used depth. Depth is not
		// realy used yet
		Map<String, Integer> visited = new HashMap<>();
		// The map which is used to track the path
		Map<String, String> paths = new HashMap<>();
		// the queue of the bfs
		List<String> queue = new LinkedList<>();
		String targetPosition = "";
		// initial data for the queue List and the two Maps;
		queue.add(from);
		paths.put(from, "");
		visited.put(from, 0);
		// depth counter
		int currentDepth = 0;
		// main while loop, continues until a path is found, or current depth
		// has maxed out
		while (depth >= currentDepth) {
			// System.out.println("Externer Zyklus: " + currentDepth);
			// how many stations are at the current depth
			int iterationlength = queue.size();
			currentDepth++;
			// loop over all stations of current depth before increasing the
			// currentDepth counter
			for (int i = 0; i < iterationlength; i++) {
				// take the first Element of the queue and remove it from the
				// queue
				String currentStation = queue.get(0);
				queue.remove(0);
				// get all children of the currentStation, if there is no
				// shorter way add them to the Maps and queue;
				IStation temp = links.get(currentStation);
				for (Map.Entry<String, Set<String>> entry : temp.getLinkMap()
						.entrySet()) {
					for (String station : entry.getValue()) {
						if (!visited.containsKey(station)) {
							visited.put(station, currentDepth);
							paths.put(station, currentStation);
							queue.add(station);
						}
						if (set.contains(station)) {
							targetPosition = station;
							return filterNewBFSAllThiefs(targetPosition, paths,
									visited);
						}
					}
				}
			}
		}
		// throw exception in case of unreachability
		throw new NoPathFoundException();
	}

	/**
	 * Filter the results of the search to get a linkedList with the shortest
	 * move.
	 * 
	 * @param targetPosition
	 *            the position desired
	 * @param paths
	 *            the paths Map from BFSAllThiefs
	 * @param visited
	 *            the links visited
	 * @return the desired path
	 */
	private List<String> filterNewBFSAllThiefs(String targetPosition,
			Map<String, String> paths, Map<String, Integer> visited) {
		// Filter paths for the best way, sorted and as List
		List<String> tempList = new LinkedList<>();
		String temp = targetPosition;
		tempList.add(temp);
		while (true) {
			String temp2 = new String(temp);
			temp = paths.get(temp);
			if (temp == null || temp.isEmpty()) {
				break;
			}
			paths.remove(temp2);
			tempList.add(0, temp);
		}
		bfslength = visited.get(targetPosition);
		return tempList;
	}

	/**
	 * take the filtered path data and join it with ticketinformations resulting
	 * in a map with a position as key and a move as value
	 * 
	 * @param path
	 *            the pathlist from filterNewBFSAllThiefs
	 * @param figureData
	 *            the figure data
	 * @return BFS result as map
	 * @throws NoPathFoundException
	 *             whenever the figure does not have enough tickets to go the
	 *             way
	 */
	private Map<String, Move> processNewBFSResults(List<String> path,
			EInitNewGame figureData) throws NoPathFoundException {
		// result
		Map<String, Move> result = new HashMap<>();
		// copy the tickets from the figure
		Map<String, Integer> tickets = new HashMap<>(
				figureData.getFigureTokens());
		// go along the path
		for (int i = 0; i < path.size() - 1; i++) {
			// ticket and temp to decide which ticket to use. The one with the
			// largest amount remaining is chosen.
			String ticket = "";
			int temp = 0;
			// get all tickets you can use for the station
			Set<String> ticketTyps = links.get(path.get(i)).getTicketTypes(
					path.get(i + 1));
			//
			for (String ticketName : ticketTyps) {
				if (figureData.getFigureTokens().get(ticketName) > temp) {
					temp = figureData.getFigureTokens().get(ticketName);
					ticket = ticketName;
				}
			}
			if (ticket.isEmpty()) {
				throw new NoPathFoundException();
			}
			int tempp = tickets.get(ticket);
			tickets.put(ticket, tempp - 1);
			result.put(path.get(i), new Move(ticket, path.get(i + 1), false));
		}

		return result;
	}

	/**
	 * Compute the move of a police figure We know this method has 84 lines of
	 * code and therefore 44 lines of code too much. But due to it's total
	 * awesomeness we left it like it is. It's possimpible.
	 * 
	 * @param e
	 *            the ENextFigure event with all data needed
	 */
	private void computePoliceMove(ENextFigure e) {
		// set the currentDistance to a max Value
		int currentDistance = Integer.MAX_VALUE;
		// There is no Target
		String currentTarget = "";
		// Get the possiblePositions
		Set<String> possiblePositions = new HashSet<>(e.getAllPossiblePositions());
		possiblePositions.remove(e.getPosition());
		// Make a List with the path
		List<String> tempPath = new LinkedList<String>();
		// Loop as long as you havn't tried all possiblePositions
		while (!possiblePositions.isEmpty()) {
			// Count the tickets
			int ticketCounter = calculateTicketCount(e);
			// Try a BFS for all ThiefPositions
			try {
				tempPath = bfsAllThiefs(e.getPosition(), possiblePositions, ticketCounter);
			} catch (NoPathFoundException e1) {
				bfslength = Integer.MAX_VALUE;
				return;
				// if the BFS fails, there is absolutly no move to any possible
				// thief position. So let's do a random move.
			}
			// Look for the BFS with the shortest length
			if (bfslength < currentDistance) {
				currentDistance = bfslength;
				currentTarget = tempPath.get(tempPath.size() - 1);
				try {
					// Process the BFS, convert the path to an Array of
					// String,Move, and check the tickettypes
					Map<String, Move> moves = processNewBFSResults(tempPath, figureList.get(e.getFigureId()));
					// If only a short way to go, take a doubleticket;
					if (bfslength < 3
							&& figureList.get(e.getFigureId()).getFigureTokens().get(Constants.TICKET_DOUBLE) > 0
							&& e.useDoubleTicketIsAllowed()) {
						moves.get(e.getPosition()).setUseDouble(true);
					}
					// make that move
					if (moves.containsKey(e.getPosition())
							&& e.getReachableLinks().containsKey(moves.get(e.getPosition()).getStation())) {
						setMove(moves.get(e.getPosition()));
					} else {
						randomMove();
					}
					return;
					// If there is no shortest way to the current target, then
					// remove it from the possiblePositions and compute again.
				} catch (NoPathFoundException e1) {
					possiblePositions.remove(currentTarget);
					currentDistance = Integer.MAX_VALUE;
					currentTarget = "";
					tempPath = null;
				}
			}
		}
	}

	/**
	 * This method is used to compute a thief move * We know this method has 79
	 * lines of code and therefore 44 lines of code too much. But due to it's
	 * total awesomeness we left it like it is. It's possimpible.
	 * 
	 * @param e
	 *            the ENextFigure event with all relevant and fresh data.
	 */
	private void computeThiefMove(ENextFigure e) {
		// Whenever you can't do move, don't do a move (seems legit)
		// Variables and maps used for algorithms
		// fill the policeList with all police figures
		List<EInitNewGame> policeList = getFigurePolice();
		Map<String, BFS> threadMap = new HashMap<String, BFS>();
		Map<String, Integer> resultsMap = new HashMap<>();
		// get the tickettyps you can use for your next move.
		List<String> ticketTyps = getTicketTypesRemaining(e.getTokens());
		// get all positions you can move to
		List<String> positions = new LinkedList<>(links.get(e.getPosition()).getReachableStations(ticketTyps));
		/* For every position around the thief start a new Thread to determine
		 * the shortest distance of a policeman to that position
		 */
		for (String position : positions) {
			BFS policeThread = new BFS(links, policeList, figureList.get(e.getFigureId()), e, position);
			policeThread.setBrain(this);
			threadMap.put(position, policeThread);
			Thread temp = new Thread(policeThread);
			kiGV.getMsgPump().logInfo("Starte Thread  " + temp.getName());
			temp.start();
		}
		/*
		 * Wait until the threads have processed and ready to share their
		 * knowledge
		 */
		for (Map.Entry<String, BFS> entry : threadMap.entrySet()) {

			while (!entry.getValue().isFinished()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// Trololol was ist eine InterruptedException? :P Ignorieren
				}
			}
			// Take the data from the threads to process it
			resultsMap.put(entry.getValue().getScanPosition(), entry.getValue().getDistance());
		}
		// find the shortest distance.
		Map.Entry<String, Integer> temp = findBestPosition(resultsMap);

		// Some variables used to determine the best ticket to use and to
		// construct the move

		boolean useDouble = false;
		Set<String> ticketTypsen = null;
		try {
			ticketTypsen = links.get(e.getPosition()).getTicketTypes(temp.getKey());
		} catch (NullPointerException ex) {
			kiGV.getMsgPump().logError("Dafuk? " + e.toString());
			try {
				Thread.sleep(200);
			} catch (InterruptedException exc) {
				System.exit(0);
			}
			System.exit(0);
		}
		// find the best ticket to use
		String ticket = findBestTicketforThief(e, ticketTypsen);
		// should me use double master?
		useDouble = longestDistance < 3 ? true : false;
		// i do da move maaan
		setMove(new Move(ticket, temp.getKey(), useDouble));
		// and coz i can, kill all references to any thread.
		threadMap.clear();
	}
	/**
	 * sum up the tickets a figure has.
	 * @param e ENextFigure event from processEvent
	 * @return the amount of tickets a figure has
	 */
	public int calculateTicketCount(ENextFigure e) {
		int ticketCounter = 0;
		Map<String, Integer> temp = figureList.get(e.getFigureId()).getFigureTokens();
		for (Map.Entry<String, Integer> count : temp.entrySet()) {
			ticketCounter += count.getValue();
		}
		return ticketCounter;
	}
	
	/**
	 * get all policefiguredata as List.
	 * @return a list of all EInitNewGame policeFigure data
	 * @since 4.0.0
	 */
	public List<EInitNewGame> getFigurePolice() {
		List<EInitNewGame> policeList = new LinkedList<>();
		for (Map.Entry<Integer, EInitNewGame> entry : figureList.entrySet()) {
			if (entry.getValue().getType() == Constants.POLICE_PLAYER_ID) {
				policeList.add(entry.getValue());
			}
		}
		return policeList;
	}
	
	/**
	 * Find best ticket type for a thief figure.
	 * 
	 * @since 4.0.0
	 * @param e
	 *            the nextFigureEvent needed for current tokens
	 * @param ticketTypsen
	 *            which tickets can be used for the way.
	 * @return Ticket type
	 */
	private String findBestTicketforThief(ENextFigure e, Set<String> ticketTypsen) {
		int count = 0;
		String ticket = "";
		for (Map.Entry<String, Integer> entry : e.getTokens().entrySet()) {
			// kiGV.getMsgPump().logInfo(
			// "Teste auf ticket: " + entry.getKey() + " in der Map  "
			// + ticketTypsen + " Aktueller Wert in count: "
			// + count);
			if (ticketTypsen.contains(entry.getKey()) && entry.getValue() > 0) {
				if (count < entry.getValue()) {
					count = entry.getValue();
					ticket = entry.getKey();
					// kiGV.getMsgPump().logInfo(
					// "Habe ticket gesetzt auf: " + ticket
					// + " zum Vergleich: " + entry.getKey());
				}
			}
		}
		return ticket;
	}

	/**
	 * find the best position for the move of a thief
	 * 
	 * @param resultsMap
	 *            the map which is to be filtered
	 * @return the best position
	 */
	private Map.Entry<String, Integer> findBestPosition(
			Map<String, Integer> resultsMap) {
		Map.Entry<String, Integer> temp = null;
		int longestDistance = -1;
		for (Map.Entry<String, Integer> entry : resultsMap.entrySet()) {
			kiGV.getMsgPump().logInfo(
					" Die Länge des Weges nach: " + entry.getKey()
							+ " beträgt: " + entry.getValue());
			if (entry.getValue() > longestDistance
					&& entry.getValue() != Integer.MAX_VALUE) {
				longestDistance = entry.getValue();
				temp = entry;
			}
		}
		this.longestDistance = longestDistance;
		return temp;
	}

	/**
	 * get a list of all ticket types that have at least 1 ticket remaining
	 * 
	 * @param tokens
	 *            the tokens of a figure
	 * @return the tokens that have at least one remaining
	 */
	public List<String> getTicketTypesRemaining(Map<String, Integer> tokens) {
		List<String> ticketTypes = new LinkedList<String>();
		for (Map.Entry<String, Integer> entry : tokens.entrySet()) {
			if (entry.getValue() > 0) {
				ticketTypes.add(entry.getKey());
			}
		}

		return ticketTypes;
	}

	/**
	 * The thread is used to compute the move of the KI asynchronously
	 */
	@Override
	public void run() {
		kiGV.getMsgPump().logDebug(
				"beginne zu verarbeiten: Thread ID: "
						+ Thread.currentThread().getId()
						+ " Aktive Threadzahl: " + Thread.activeCount()
						+ " FigurenInfo: " + nextFigEventData.toString());

		if (figureList.get(nextFigEventData.getFigureId()).getType() == logic.Constants.POLICE_PLAYER_ID) {
			if (nextFigEventData.getAllPossiblePositions().isEmpty()) {
				randomMove();
				return;
			}
			computePoliceMove(nextFigEventData);
		} else {
			computeThiefMove(nextFigEventData);
		}
	}
}
