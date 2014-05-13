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
import logic.player.PlayerPoliceStub;
import events.IAEvent;

/**
 * A figure police stub: id=1, position=23
 * 
 * @author Fabian
 * 
 */
public class FigurePoliceStub implements IAFigure, IFigurePolice {

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
     * @see logic.figure.IAFigure#getId()
     */
    @Override
    public int getId() {
	return 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#getOwner()
     */
    @Override
    public IAPlayer getOwner() {
	return new PlayerPoliceStub();
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
	return "23";
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#getTicketsRemaining()
     */
    @Override
    public Map<String, Integer> getTicketsRemaining() {
	Map<String, Integer> tokens = new HashMap<String, Integer>();
	tokens.put("car", 2);
	tokens.put("swim", 0);
	tokens.put("walk", 22);

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

	types.add("car");
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
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#isPolice()
     */
    @Override
    public boolean isPolice() {
	return true;
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
	return false;
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
