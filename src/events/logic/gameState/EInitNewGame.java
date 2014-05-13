/**
 * 
 */
package events.logic.gameState;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Map;

import logic.figure.IAFigure;
import logic.player.IAPlayer;
import events.AEvent;

/**
 * Delivers all Player informations.
 * 
 * @author Fabian
 * @version 2.0
 */
public class EInitNewGame extends AEvent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String name;
    private Color color;
    private int type;
    private int id;

    private int figureId;
    private BufferedImage figureIcon;
    private String figurePosition;
    private Map<String, Integer> figureTokens;
    private int ownerGV;

    /**
     * The initNewGame Event-class has a reference to all data the GState needs.
     * 
     * @param source
     *            event source
     * @param eventType
     *            the event Type
     * @param player
     *            the APlayer used to parse the relevant data into the event
     * @param figure
     *            the AFigure used to parse the relevant data into the event
     */
    public EInitNewGame(Object source, int eventType, IAPlayer player,
	    IAFigure figure) {
	super(source, eventType);
	this.name = player.getName();
	this.color = player.getColor();
	this.type = player.getType();
	this.id = player.getId();
	this.ownerGV = player.getOwnerGV();
	this.figureId = figure.getId();
	this.figureIcon = figure.getIcon();
	this.figurePosition = figure.getPosition();
	this.figureTokens = figure.getTicketsRemaining();
    }

    public int getOwnerGV() {
	return ownerGV;
    }

    public String getName() {
	return name;
    }

    public Color getColor() {
	return color;
    }

    public int getType() {
	return type;
    }

    public int getId() {
	return id;
    }

    public int getFigureId() {
	return figureId;
    }

    public BufferedImage getFigureIcon() {
	return figureIcon;
    }

    public String getFigurePosition() {
	return figurePosition;
    }

    public Map<String, Integer> getFigureTokens() {
	return figureTokens;
    }

    public void setPosition(String pos) {
	figurePosition = pos;
    }

    @Override
    public String toString() {
	return "EInitNewGame [name=" + name + ", color=" + color + ", type="
		+ type + ", id=" + id + ", figureId=" + figureId
		+ ", figureIcon=" + figureIcon + ", figurePosition="
		+ figurePosition + ", figureTokens=" + figureTokens
		+ ", ownerGV=" + ownerGV + "]";
    }
}
