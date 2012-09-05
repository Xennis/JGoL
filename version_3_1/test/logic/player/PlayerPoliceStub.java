/**
 * 
 */
package logic.player;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import logic.Constants;
import logic.figure.FigurePoliceNoTicketsStub;
import logic.figure.FigurePoliceStub;
import logic.figure.IAFigure;
import events.IAEvent;

/**
 * A police player stub: name=Herbert, id=1, figures={1,2}
 * 
 * @author Fabian
 * 
 */
public class PlayerPoliceStub implements IAPlayer, IPlayerPolice {

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
	return Constants.POLICE_PLAYER_ID;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#getName()
     */
    @Override
    public String getName() {
	return "Herbert";
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
	return 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#getFigures()
     */
    @Override
    public List<IAFigure> getFigures() {
	List<IAFigure> figureList = new LinkedList<IAFigure>();
	figureList.add(new FigurePoliceStub());
	figureList.add(new FigurePoliceNoTicketsStub());
	return figureList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#getColorName()
     */
    @Override
    public String getColorName() {
	return "red";
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#isThief()
     */
    @Override
    public boolean isThief() {
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#isPolice()
     */
    @Override
    public boolean isPolice() {
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.player.IAPlayer#hasFigures()
     */
    @Override
    public boolean hasFigures() {
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "APlayer [NAME=Herbert, COLOR=red, ID=1, TYPE=1]";
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
	return 0;
    }
}
