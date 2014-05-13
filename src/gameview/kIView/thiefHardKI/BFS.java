package gameview.kIView.thiefHardKI;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import core.IStation;
import core.IStationMap;
import events.logic.gameState.EInitNewGame;
import events.logic.gameState.ENextFigure;
import gameview.kIView.BFSData;
import gameview.kIView.KIHardBrain;
import gameview.kIView.NoPathFoundException;

/**
 * The BFS for a hard ki thief figure.
 * 
 * @author Japser
 *
 */
public class BFS implements Runnable {

	private IStationMap links;
	private List<EInitNewGame> policeFigures;
	private String scanPosition;
	private boolean finished;
	private KIHardBrain brain = null;
	private int distance = Integer.MAX_VALUE;

	/**
	 * Create a new BFS Object.
	 * 
	 * @param links
	 *            Map with all map links
	 * @param policeFigures
	 *            List with all police figure
	 * @param currentFigure
	 *            The current figure
	 * @param currentFigureEventData
	 *            Data of the current figure
	 * @param scanPosition
	 *            Scan position
	 */
	public BFS(IStationMap links, List<EInitNewGame> policeFigures,
			EInitNewGame currentFigure, ENextFigure currentFigureEventData,
			String scanPosition) {
		this.links = links;
		this.policeFigures = policeFigures;
		this.scanPosition = scanPosition;
		finished = false;
	}

	/**
	 * Set the hard brain.
	 * 
	 * @param brain The {@link gameview.kIView.KIHardBrain}
	 */
	public void setBrain(KIHardBrain brain) {
		this.brain = brain;
	}

	/**
	 * This search includes ticketspecific path-finding. While processing it
	 * already keeps track of the tickets only ways the figure can go are
	 * returned.
	 * 
	 * @param from
	 *            from where?
	 * @param to
	 *            the target position
	 * @param tickets
	 *            initial tickets of the figure
	 * @return unfiltered map containing the path
	 * @throws NoPathFoundException
	 *             thrown if there is absolutly no way from 'from' to 'to' with
	 *             these tickets
	 */
	public Map<String, BFSData> bfs(String from, String to,
			Map<String, Integer> tickets) throws NoPathFoundException {
		// visitedStations with all needed Data of the Station in a BFSData
		// object
		Map<String, BFSData> visitedStations = new HashMap<String, BFSData>();
		// The queue for the search
		List<String> queue = new LinkedList<String>();
		// InitialData for the Map and the List
		visitedStations.put(from, new BFSData("", tickets));
		queue.add(from);
		// Searchloop, does only stop when a way was found or all paths scanned
		// and there is absolutly no way
		while (!queue.get(0).equals(to)) {
			// fetch the next Station and remove it from the queue
			String temp = queue.get(0);
			queue.remove(0);
			// brain.getKiGV().getMsgPump().logDebug("BFS Thief "+
			// Thread.currentThread().getName()+" Queue Info: " +
			// queue.toString());
			// take the data of that station
			BFSData tempData = visitedStations.get(temp);
			IStation tempStation = links.get(temp);
			// take all from temp reachable stations
			Map<String, Set<String>> tempLinks = tempStation.getLinkMap();
			// Iterate over the reachale Stations
			Map<String, Set<String>> rightOrderedTickets = orderTickets(tempLinks);
			// Iterate over the reachale Stations
			for (Map.Entry<String, Set<String>> entry : rightOrderedTickets
					.entrySet()) {
				if (!visitedStations.containsKey(entry.getKey())) {
					int remTicketCount = 0;
					String chosenTicketType = "";
					// Chose the ticket i have most of
					for (String ticketType : entry.getValue()) {
						if (tempData.getRemainingTickets().get(ticketType) > 0 && tempData.getRemainingTickets().get(ticketType) > remTicketCount) {
							remTicketCount = tempData.getRemainingTickets().get(ticketType);
							chosenTicketType = ticketType;
						}
					}
					// .getKiGV().getMsgPump().logInfo("Das günstigste Ticket ist: "
					// + chosenTicketType);
					// IfI have a ticket to go that way put all data into my
					// maps. and fetch the next Station from the queue
					if (!chosenTicketType.isEmpty()) {
						Map<String, Integer> newTickets = new HashMap<>(tempData.getRemainingTickets());
						newTickets.put(chosenTicketType, remTicketCount - 1);
						visitedStations.put(entry.getKey(), new BFSData(temp, newTickets));
						queue.add(entry.getKey());
					}
				}
			}
			// Whenever there is absolutely no way to go, this exception will be
			// fired
			if (queue.isEmpty()) {
				throw new NoPathFoundException();
			}
		}
		// return a unsorted map with all data, which should be filtered by
		// filterBFSResults()
		// brain.getKiGV().getMsgPump().logDebug("BFS Thief " +
		// Thread.currentThread().getName() + " hat einen Weg gefunden " +
		// visitedStations.toString());
		return visitedStations;
	}
	
		/**
		 * Order the tickets.
		 * 
		 * @param tempLinks Map with the links
		 * @return Map with ordered tickets
		 */
		private Map<String, Set<String>> orderTickets(Map<String, Set<String>> tempLinks) {
		Map<String, Set<String>> rightOrderedTickets = new HashMap<String, Set<String>>();
		// Take the Map<Ticket,reachablepositions> and return a
		// Map<reachablePosition, ticket>
		for (Map.Entry<String, Set<String>> entry : tempLinks.entrySet()) {
			for (String station : entry.getValue()) {
				if (rightOrderedTickets.containsKey(station)) {
					rightOrderedTickets.get(station).add(entry.getKey());
				} else {
					rightOrderedTickets.put(station, new HashSet<String>());
					rightOrderedTickets.get(station).add(entry.getKey());
				}
			}
		}
		return rightOrderedTickets;
	}

	/**
	 * take the result from the bfs and process it to a Map with stations as
	 * keys and moves to the next station as BFSData with no overhead
	 * 
	 * @param bFSResults
	 *            the unfiltered results of the BFS
	 * @param target
	 *            the targeted position
	 * @return only BFSData needed are returned.
	 */
	public Map<String, BFSData> filterBFSResults(
			Map<String, BFSData> bFSResults, String target) {
		Map<String, BFSData> filteredResults = new HashMap<String, BFSData>();
		BFSData tempData = bFSResults.get(target);
		String currentStation = target;
		while (!tempData.getFrom().isEmpty()) {
			filteredResults.put(currentStation, tempData);
			currentStation = tempData.getFrom();
			tempData = bFSResults.get(currentStation);
		}
		brain.getKiGV()
				.getMsgPump()
				.logInfo(
						"BFS Thief " + Thread.currentThread().getName()
								+ " hat die Ergebnisse gefiltert "
								+ filteredResults.toString());
		return filteredResults;
	}

	/**
	 * Process the data of the move as asynchronous thread
	 */
	@Override
	public void run() {
		int minDistance = Integer.MAX_VALUE;
		for (EInitNewGame e : policeFigures) {
			try {
				Map<String, BFSData> temp = bfs(e.getFigurePosition(),
						scanPosition, e.getFigureTokens());
				Map<String, BFSData> temp2 = filterBFSResults(temp,
						scanPosition);
				if (temp2.size() < minDistance) {
					minDistance = temp2.size();
				}
			} catch (NoPathFoundException e1) {
				minDistance = 1000;
			}
		}
		distance = minDistance;
		finished = true;
		Thread.currentThread().interrupt();
		return;
	}

	/**
	 * Check is finished.
	 * 
	 * @return True, if finished
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * Get distance.
	 * @return distance
	 */
	public int getDistance() {
		if (!finished) {
			return Integer.MAX_VALUE;
		}
		return distance;
	}

	/**
	 * Get scan position.
	 * @return scan position
	 */
	public String getScanPosition() {
		return scanPosition;
	}

}
