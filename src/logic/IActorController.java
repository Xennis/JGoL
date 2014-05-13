package logic;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import logic.figure.IAFigure;
import logic.figure.IFigurePolice;
import logic.figure.IFigureThief;
import logic.player.IAPlayer;
import events.IAEvent;
import gameview.sView.ISPlayer;

/**
 * The actorcontroller has the control over every figure and player
 * 
 * @author Raphael A., Jasper S.
 * @version 3.0
 */
public interface IActorController {

    public abstract void addPlayerWithFigures(ISPlayer playerDesc,
	    int gameViewID, Map<String, Integer> tokens, BufferedImage icon);

    public abstract void destroyCurrentGameState();

    public abstract IAFigure getCurrentFigure();

    public abstract List<IAFigure> getFigureList();

    public abstract List<Integer> getFigureQueue();

    public abstract IAFigure getNextFigure();

    public abstract int getNumPlayer();

    public abstract List<IAPlayer> getPlayerList();

    public abstract List<String> getPlayerPoliceNameList();

    public abstract List<String> getPlayerThiefNameList();

    public abstract void processEvent(IAEvent e);

    public abstract void removeFigure(IAFigure figure);

    public abstract void removePlayer(int playerId);

    public abstract void setCurrentQueuePosition(int currentQueuePosition);

    public abstract void setStartAt(List<String> startAt);

    public abstract List<IFigureThief> getFigureThiefList();

    public abstract List<IFigurePolice> getFigurePoliceList();

}