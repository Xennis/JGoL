/**
 * 
 */
package logic.player;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import logic.Constants;
import logic.figure.FigureThiefStub;
import logic.figure.IAFigure;
import events.IAEvent;

/**
 * A player thief stub: name=Hans, id=0, figures{0}
 * 
 * @author Fabian
 * 
 */
public class PlayerThiefStub implements IAPlayer, IPlayerThief {

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IPlayerThief#processEvent(events.IAEvent)
     */
    @Override
    public void processEvent(IAEvent e) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#getType()
     */
    @Override
    public int getType() {
	return Constants.THIEF_PLAYER_ID;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#destroyFigure(int)
     */
    @Override
    public void destroyFigure(int figureID) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#getName()
     */
    @Override
    public String getName() {
	return "Hans";
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#getColor()
     */
    @Override
    public Color getColor() {
	javax.swing.text.html.StyleSheet parser = new javax.swing.text.html.StyleSheet();
	return parser.stringToColor(getColorName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#getId()
     */
    @Override
    public int getId() {
	return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#getFigures()
     */
    @Override
    public List<IAFigure> getFigures() {
	List<IAFigure> figureList = new LinkedList<IAFigure>();
	figureList.add(new FigureThiefStub());
	return figureList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#getColorName()
     */
    @Override
    public String getColorName() {
	return "blue";
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#isThief()
     */
    @Override
    public boolean isThief() {
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#isPolice()
     */
    @Override
    public boolean isPolice() {
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#hasFigures()
     */
    @Override
    public boolean hasFigures() {
	// TODO Auto-generated method stub
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "APlayer [NAME=Hans, COLOR=blue, ID=0, TYPE=0]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#addFigure(logic.figure.IAFigure)
     */
    @Override
    public void addFigure(IAFigure figure) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#getOwnerGV()
     */
    @Override
    public int getOwnerGV() {
	// TODO Auto-generated method stub
	return 0;
    }

}
