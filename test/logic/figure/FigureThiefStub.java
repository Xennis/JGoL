/**
 * 
 */
package logic.figure;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import logic.player.IAPlayer;
import logic.player.PlayerThiefStub;
import events.IAEvent;

/**
 * A figure police stub: id=0, position=22, tokens={swim,walk}
 * 
 * @author Fabian
 * 
 */
public class FigureThiefStub implements IAFigure, IFigureThief {

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IFigureThief#processEvent(events.IAEvent)
     */
    @Override
    public void processEvent(IAEvent e) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#getOwner()
     */
    @Override
    public IAPlayer getOwner() {
	return new PlayerThiefStub();
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IFigureThief#addTicket(java.lang.String)
     */
    @Override
    public void addTicket(String ticket) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#getId()
     */
    @Override
    public int getId() {
	return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#setPosition(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void setPosition(String position, String ticket) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#getPosition()
     */
    @Override
    public String getPosition() {
	return "22";
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#getTicketsRemaining()
     */
    @Override
    public Map<String, Integer> getTicketsRemaining() {
	Map<String, Integer> tokens = new HashMap<String, Integer>();
	tokens.put("car", 0);
	tokens.put("swim", 7);
	tokens.put("walk", 18);
	return tokens;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#getTicketTypesRemaining()
     */
    @Override
    public List<String> getTicketTypesRemaining() {
	List<String> types = new LinkedList<String>();
	types.add("swim");
	types.add("walk");
	return types;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#getIcon()
     */
    @Override
    public BufferedImage getIcon() {
	BufferedImage icon = null;
	try {
	    icon = ImageIO.read(new File("./mediaTest/figures/sample.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return icon;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#isThief()
     */
    @Override
    public boolean isThief() {
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#isPolice()
     */
    @Override
    public boolean isPolice() {
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "AFigure [id=0, owner=0, position=22, tokens={car=0, swim=7, walk=18}]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#addTicket(java.lang.String, int)
     */
    @Override
    public void addTicket(String ticketType, int count) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#getNumMoves()
     */
    @Override
    public int getNumMoves() {
	// TODO Auto-generated method stub
	return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#hasDoubleTicket()
     */
    @Override
    public boolean hasDoubleTicket() {
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#usedDoubleTicket()
     */
    @Override
    public void usedDoubleTicket() {
	// TODO Auto-generated method stub

    }

}
