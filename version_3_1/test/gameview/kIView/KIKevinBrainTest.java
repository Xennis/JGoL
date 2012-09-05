package gameview.kIView;

import events.EventConstants;
import events.logic.gameState.EInitNewGame;
import events.logic.gameState.ENextFigure;
import events.logic.gameState.NextFigureEventData;
import gameview.KIGameViewStubEasy;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import logic.Constants;
import logic.figure.AFigure;
import logic.figure.FigureThief;
import logic.player.APlayer;
import logic.player.PlayerThief;

import org.junit.Before;
import org.junit.Test;

import core.StationMap;
import core.msgpump.MsgPumpStub;

public class KIKevinBrainTest {

	KIKevinBrain brain;
	KIGameViewStubEasy viewStub;
	List<APlayer> playerList;
	List<AFigure> figureList;
	core.RessourceCache cache;

	@Before
	public void init() {
		cache = new core.RessourceCache(new MsgPumpStub());
		cache.scanDir();
		cache.loadConfig(0);
		cache.loadLinks();
		playerList = new LinkedList<>();
		figureList = new LinkedList<>();
		viewStub = new KIGameViewStubEasy();
		brain = new KIKevinBrain(cache.getLinks(), viewStub);
		Map<String, Integer> tokens = new HashMap<>();
		tokens.put("walk", 5);
		tokens.put("car", 10);
		tokens.put("black", 2);
		tokens.put("swim", 1);
		tokens.put("double", 100);
		int count2 = 0;
		for (int count1 = 0; count1 < 2; count1++) {
			if (count1 % 2 == 0) {
				APlayer tempPlayer = new PlayerThief("Player" + count1, "red",
						count1, Constants.THIEF_PLAYER_ID, viewStub.getViewID());
				playerList.add(tempPlayer);
				for (int i = count2; i < count2 + 1; i++) {
					AFigure tempFigure = new FigureThief(tempPlayer, i,
							"10", tokens, null);
					brain.processEvent(new EInitNewGame(this,
							EventConstants.INIT_NEW_GAME, tempPlayer,
							tempFigure));
					figureList.add(tempFigure);
				}
			} else {
				APlayer tempPlayer = new PlayerThief("Player" + count1, "red",
						count1, Constants.POLICE_PLAYER_ID, viewStub.getViewID());
				playerList.add(tempPlayer);
				for (int i = count2; i < count2 + 1; i++) {
					AFigure tempFigure = new FigureThief(tempPlayer, i,
							"11", tokens, null);
					brain.processEvent(new EInitNewGame(this,
							EventConstants.INIT_NEW_GAME, tempPlayer,
							tempFigure));
					figureList.add(tempFigure);
				}
			}
			count2 += 1;
		}

	}
	
	@Test
	public void testPoliceRandomMove() {
		NextFigureEventData data = new NextFigureEventData();
		data.setFigure(figureList.get(0));
		Set<String> tempSet = new HashSet<String>();
		data.setAllPossiblePositions(tempSet);
		data.setReachableLinks(((StationMap)cache.getLinks()).get("30").getReachableLinks(figureList.get(0).getTicketTypesRemaining()));
		data.setShowThieves(new HashMap<Integer,String>());
		data.setUseDoubleIsAllowed(true);
		ENextFigure e = new ENextFigure(this,data);
		brain.processEvent(e);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			System.out.println("klappt nicht");
		}
		assertNotNull(brain.getMove());
	}
	@Test
	public void testPoliceMove() {
		NextFigureEventData data = new NextFigureEventData();
		data.setFigure(figureList.get(1));
		Set<String> tempSet = new HashSet<String>();
		tempSet.add("1");
		tempSet.add("128");
		data.setAllPossiblePositions(tempSet);
		data.setReachableLinks(((StationMap)cache.getLinks()).get("11").getReachableLinks(figureList.get(0).getTicketTypesRemaining()));
		data.setShowThieves(new HashMap<Integer,String>());
		data.setUseDoubleIsAllowed(true);
		ENextFigure e = new ENextFigure(this,data);
		brain.processEvent(e);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			System.out.println("klappt nicht");
		}
		assertNotNull(brain.getMove());
	}
	
	@Test
	public void testPoliceMoveIntoTheWilderness() {
		NextFigureEventData data = new NextFigureEventData();
		data.setFigure(figureList.get(1));
		Set<String> tempSet = new HashSet<String>();
		tempSet.add("1000");
		data.setAllPossiblePositions(tempSet);
		data.setReachableLinks(((StationMap)cache.getLinks()).get("11").getReachableLinks(figureList.get(0).getTicketTypesRemaining()));
		Map<Integer,String> showThiefs = new HashMap<Integer,String>();
		showThiefs.put(0, "30");
		data.setShowThieves(showThiefs);
		data.setUseDoubleIsAllowed(true);
		ENextFigure e = new ENextFigure(this,data);
		brain.processEvent(e);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			System.out.println("klappt nicht");
		}
		assertNotNull(brain.getMove());
	}
	
	@Test
	public void testPoliceMoveIntoTheWilderness2() {
		NextFigureEventData data = new NextFigureEventData();
		data.setFigure(figureList.get(1));
		Set<String> tempSet = new HashSet<String>();
		tempSet.add("1000");
		data.setAllPossiblePositions(tempSet);
		data.setReachableLinks(((StationMap)cache.getLinks()).get("11").getReachableLinks(figureList.get(0).getTicketTypesRemaining()));
		Map<Integer,String> showThiefs = new HashMap<Integer,String>();
		showThiefs.put(0, "10");
		data.setShowThieves(showThiefs);
		data.setUseDoubleIsAllowed(true);
		ENextFigure e = new ENextFigure(this,data);
		brain.processEvent(e);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			System.out.println("klappt nicht");
		}
		assertNotNull(brain.getMove());
	}
	
	@Test
	public void testPoliceMoveIntoTheWilderness3() {
		NextFigureEventData data = new NextFigureEventData();
		data.setFigure(figureList.get(1));
		Set<String> tempSet = new HashSet<String>();
		tempSet.add("1000");
		data.setAllPossiblePositions(tempSet);
		data.setReachableLinks(((StationMap)cache.getLinks()).get("10").getReachableLinks(figureList.get(1).getTicketTypesRemaining()));
		Map<Integer,String> showThiefs = new HashMap<Integer,String>();
		showThiefs.put(0, "10");
		data.setShowThieves(showThiefs);
		data.setUseDoubleIsAllowed(true);
		ENextFigure e = new ENextFigure(this,data);
		brain.processEvent(e);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			System.out.println("klappt nicht");
		}
		assertNotNull(brain.getMove());
	}
}
