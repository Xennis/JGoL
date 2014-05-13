package events;

import java.util.LinkedList;
import java.util.List;

/**
 * Generates the events and fire them. The logic and game view generator are
 * subclasses.
 * 
 * @author Jasper S.
 * 
 * @param <T>
 *            listener interface
 */
public abstract class EventGenerator<T> implements IEventGenerator<T> {

	protected List<T> listeners;

	/**
	 * Create a new event generator.
	 */
	public EventGenerator() {
		listeners = new LinkedList<T>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.IEventGenerator#fireEvent(events.IAEvent)
	 */
	@Override
	public abstract void fireEvent(IAEvent event);

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.IEventGenerator#addListener(T)
	 */
	@Override
	public void addListener(T l) {
		listeners.add(l);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.IEventGenerator#removeListener(java.lang.Object)
	 */
	@Override
	public void removeListener(T l) {
		listeners.remove(l);
	}

	/**
	 * Remove all listeners.
	 */
	public void removeAllListener() {
		listeners.clear();
	}
}
