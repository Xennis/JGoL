package gameview.kIView;

import events.EventConstants;
import events.logic.gameState.EInitNewGame;
import events.logic.gameState.ENextFigure;
import events.logic.gameState.NextFigureEventData;
import gameview.KIGameViewStubHard;

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

public class KIHardBrainTest3 {

	KIHardBrain brain;
	KIGameViewStubHard viewStub;
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
		viewStub = new KIGameViewStubHard();
		brain = new KIHardBrain(cache.getLinks(), viewStub);
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
							"1", tokens, null);
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
							"2", tokens, null);
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
	public void testThiefMove() {
		NextFigureEventData data = new NextFigureEventData();
		data.setFigure(figureList.get(0));
		Set<String> tempSet = new HashSet<String>();
		tempSet.add("2");
		data.setAllPossiblePositions(tempSet);
		data.setReachableLinks(((StationMap)cache.getLinks()).get("2").getReachableLinks(figureList.get(0).getTicketTypesRemaining()));
		data.setShowThieves(null);
		data.setUseDoubleIsAllowed(true);
		ENextFigure e = new ENextFigure(this,data);
		brain.processEvent(e);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			System.out.println("klappt nicht");
		}
		assertEquals(brain.getMove().isUseDouble(),true);
	}
}