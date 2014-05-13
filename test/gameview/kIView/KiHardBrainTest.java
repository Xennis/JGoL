package gameview.kIView;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import logic.Constants;
import logic.figure.AFigure;
import logic.figure.FigureThief;
import logic.player.APlayer;
import logic.player.PlayerThief;

import org.junit.Before;
import org.junit.Test;

import gameview.KIGameViewStubHard;
import core.msgpump.MsgPumpStub;
import events.EventConstants;
import events.logic.gameState.EInitNewGame;
import events.logic.gameState.ENextFigure;
import events.logic.gameState.NextFigureEventData;

public class KiHardBrainTest {

	KIHardBrain brain;
	KIGameViewStubHard viewStub;
	List<APlayer> playerList;
	List<AFigure> figureList;

	@Before
	public void init() {
		core.RessourceCache cache = new core.RessourceCache(new MsgPumpStub());
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
		int count2 = 0;
		for (int count1 = 0; count1 < 3; count1++) {
			if (count1 % 2 == 0) {
				APlayer tempPlayer = new PlayerThief("Player" + count1, "red",
						count1, Constants.THIEF_PLAYER_ID, viewStub.getViewID());
				playerList.add(tempPlayer);
				for (int i = count2; i < count2 + 3; i++) {
					AFigure tempFigure = new FigureThief(tempPlayer, i,
							Integer.toString(i), tokens, null);
					brain.processEvent(new EInitNewGame(this,
							EventConstants.INIT_NEW_GAME, tempPlayer,
							tempFigure));
					figureList.add(tempFigure);
				}
			} else {
				APlayer tempPlayer = new PlayerThief("Player" + count1, "red",
						count1, Constants.POLICE_PLAYER_ID, viewStub.getViewID());
				playerList.add(tempPlayer);
				for (int i = count2; i < count2 + 3; i++) {
					AFigure tempFigure = new FigureThief(tempPlayer, i,
							Integer.toString(i), tokens, null);
					brain.processEvent(new EInitNewGame(this,
							EventConstants.INIT_NEW_GAME, tempPlayer,
							tempFigure));
					figureList.add(tempFigure);
				}

			}
			count2 += 3;
		}

	}

	@Test
	public void testStartUp() {
		assertNotNull(brain.getFigurePolice());
	}
	@Test
	public void testStartUp1() {
		assertNotNull(brain.getFigurePolice().get(0));
	}
	@Test
	public void testStartUp2() {
		assertEquals(brain.getFigurePolice().size(), 3);
	}
	
	@Test
	public void testTicketCounter() {
		NextFigureEventData data = new NextFigureEventData();
		data.setFigure(figureList.get(0));
		int i = brain.calculateTicketCount(new ENextFigure(this,data));
		assertEquals(18, i);
	}
	
	

}
