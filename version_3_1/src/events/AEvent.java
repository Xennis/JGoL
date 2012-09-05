package events;

import java.util.EventObject;

/**
 * The class every event should inherited.
 * 
 * @author Jasper
 * @version 1.0
 */
public abstract class AEvent extends EventObject implements IAEvent {

	private static final long serialVersionUID = 1L;

	private Object source = null;
	private final int eventType;
	private boolean processed = false;

	/**
	 * Create a basic event.
	 * 
	 * @since 1.0
	 * @param source
	 *            Event source
	 * @param eventType
	 *            Event type as integer
	 */
	public AEvent(Object source, int eventType) {
		super(source);
		this.source = source;
		this.eventType = eventType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.IAEvent#getSource()
	 */
	@Override
	public Object getSource() {
		return source;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.IAEvent#getEventType()
	 */
	@Override
	public int getEventType() {
		return eventType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.IAEvent#processed()
	 */
	@Override
	public void processed() {
		processed = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.IAEvent#isProcessed()
	 */
	@Override
	public boolean isProcessed() {
		return processed;
	}

}
