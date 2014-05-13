package logic.figure;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import logic.Constants;
import logic.player.IAPlayer;

import events.IAEvent;

/**
 * This abstract class holds all gernal information about a figure in the game.
 * 
 * @author Fabian
 */
public abstract class AFigure implements IAFigure {

    /** The figure identification integer */
    private final int figureId;

    /** The owner of the figure */
    private final IAPlayer owner;

    /** The position on the map */
    private String position;

    /** The tokens, ticket types as identifier and number of this type as value */
    protected Map<String, Integer> tokens;

    /** The figure icon */
    private final BufferedImage icon;

    /** The number of moves */
    private int numMoves;

    /**
     * Creates a new figure object, which hold all gernal information about a
     * figure.
     * 
     * @param owner
     *            owner of the figure
     * @param figureID
     *            figure identification integer
     * @param position
     *            start position on the map
     * @param tokens
     *            start tokens, ticket types as identifier and number of this
     *            type as value
     * @param icon
     *            figure icon
     */
    public AFigure(IAPlayer owner, int figureID, String position,
	    Map<String, Integer> tokens, BufferedImage icon) {
	this.owner = owner;
	this.figureId = figureID;
	this.position = position;
	this.tokens = new HashMap<String, Integer>(tokens);
	this.icon = icon;
	this.numMoves = 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#getID()
     */
    @Override
    public int getId() {
	return figureId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#getOwner()
     */
    @Override
    public IAPlayer getOwner() {
	return this.owner;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#setPosition(int, java.lang.String)
     */
    @Override
    public void setPosition(String position, String ticket) {
	if (this.tokens.containsKey(ticket) && this.tokens.get(ticket) > 0) {
	    this.tokens.put(ticket, this.tokens.get(ticket) - 1);
	    this.position = position;
	    this.numMoves++;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#usedDoubleTicket()
     */
    @Override
    public void usedDoubleTicket() {
	if (tokens.containsKey(Constants.TICKET_DOUBLE)
		&& tokens.get(Constants.TICKET_DOUBLE) > 0) {
	    tokens.put(Constants.TICKET_DOUBLE,
		    tokens.get(Constants.TICKET_DOUBLE) - 1);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IAFigure#getPosition()
     */
    @Override
    public String getPosition() {
	return position;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IAFigure#getTicketsRemaining()
     */
    @Override
    public Map<String, Integer> getTicketsRemaining() {
	return this.tokens;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#getTicketTypesRemaining()
     */
    @Override
    public List<String> getTicketTypesRemaining() {
	List<String> ticketTypes = new LinkedList<String>();

	for (Map.Entry<String, Integer> entry : tokens.entrySet()) {
	    if (entry.getValue() > 0) {
		ticketTypes.add(entry.getKey());
	    }
	}

	return ticketTypes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IAFigure#getIcon()
     */
    @Override
    public BufferedImage getIcon() {
	return this.icon;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IAFigure#processEvent(events.IAEvent)
     */
    @Override
    public abstract void processEvent(IAEvent e);

    /*
     * (non-Javadoc)
     * 
     * @see logic.IAFigure#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	IAFigure f = (IAFigure) obj;
	if (figureId == f.getId() && position.equals(f.getPosition())
		&& tokens.equals(f.getTicketsRemaining())) {
	    return true;
	} else {
	    return false;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#addTicket(java.lang.String, int)
     */
    @Override
    public void addTicket(String ticketType, int count) {
	tokens.put(ticketType, count);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#getNumMoves()
     */
    @Override
    public int getNumMoves() {
	return numMoves;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#hasDoubleTicket()
     */
    @Override
    public boolean hasDoubleTicket() {
	if (tokens.containsKey(Constants.TICKET_DOUBLE)
		&& tokens.get(Constants.TICKET_DOUBLE) > 0) {
	    return true;
	} else {
	    return false;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "AFigure [id=" + figureId + ", owner=" + owner.getId()
		+ ", position=" + position + ", tokens=" + tokens + "]";
    }
}
