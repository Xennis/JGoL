package logic.figure;

import events.IAEvent;

/**
 * The interface to FigurePolice
 * 
 * @author Fabian R.
 * @version 3.0
 * 
 */
public interface IFigurePolice extends IAFigure {

    /**
     * Process {@link events.IAEvent} for a figure.
     * 
     * @param e A event
     */
    public abstract void processEvent(IAEvent e);

}