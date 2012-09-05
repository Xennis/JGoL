package logic.player;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import logic.figure.IAFigure;

import events.IAEvent;

/**
 * This abstract class hold all general information about a player in the game.
 * 
 * @author Jasper S., Fabian R.
 * @version 3.0
 * 
 */
public abstract class APlayer implements IAPlayer {

    /** The player name */
    final String name;

    /** The player color */
    final String color;

    /** The player identification integer */
    final int id;

    /** The player type */
    final int type;

    /** List with all figures */
    List<IAFigure> figureList;

    /** The game view integer */
    private int ownerGV;

    /**
     * Create a new player object, which holds all general information about a
     * player.
     * 
     * @param name
     *            Player name
     * @param color
     *            Player color as String
     * @param id
     *            Player identification integer
     * @param type
     *            Player type
     * @param ownerGV
     *            Game view integer
     */
    public APlayer(String name, String color, int id, int type, int ownerGV) {
	this.name = name;
	this.color = color;
	this.id = id;
	this.type = type;
	this.figureList = new LinkedList<IAFigure>();
	this.ownerGV = ownerGV;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#addFigure(logic.figure.IAFigure)
     */
    @Override
    public void addFigure(IAFigure figure) {
	figureList.add(figure);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IAPlayer#destroyFigure(int)
     */
    @Override
    public void destroyFigure(int figureID) {
	for (IAFigure figure : figureList) {
	    if (figure.getId() == figureID) {
		figureList.remove(figure);
		break;
	    }
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IAPlayer#processEvent(events.IAEvent)
     */
    @Override
    public abstract void processEvent(IAEvent e);

    /*
     * (non-Javadoc)
     * 
     * @see logic.IAPlayer#getNAME()
     */
    @Override
    public String getName() {
	return name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IAPlayer#getCOLOR()
     */
    @Override
    public String getColorName() {
	return color;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IAPlayer#getID()
     */
    @Override
    public int getId() {
	return id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IAPlayer#getTYPE()
     */
    @Override
    public int getType() {
	return type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IAPlayer#getFigureMap()
     */
    @Override
    public List<IAFigure> getFigures() {
	return figureList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "APlayer [NAME=" + name + ", COLOR=" + color + ", ID=" + id
		+ ", TYPE=" + type + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#getColor()
     */
    @Override
    public Color getColor() {
	javax.swing.text.html.StyleSheet parser = new javax.swing.text.html.StyleSheet();
	return parser.stringToColor(color);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IAPlayer#hasFigures()
     */
    @Override
    public boolean hasFigures() {
	if (figureList.isEmpty()) {
	    return false;
	} else {
	    return true;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#getOwnerGV()
     */
    @Override
    public int getOwnerGV() {
	return ownerGV;
    }

}
