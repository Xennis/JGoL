package core;

/**
 * The timer used for the main loop.
 * 
 * @author Jasper S.
 * @version 3.0
 * 
 */
public class Timer implements ITimer {

    private long lastFrame;

    public Timer() {
	lastFrame = 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.ITimer#getTime()
     */
    @Override
    public long getTime() {
	// system time in milliseconds.
	// mac fix: System.nanotime produces inaccurate results on mac, thus
	// resulting in 6,2 million ms to sleep, current time millis should do
	// the job for now (+ better for threads)
	return System.nanoTime() / 1000000;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.ITimer#getDelta()
     */
    @Override
    public int getDelta() {
	long time = getTime();
	int delta = (int) (time - lastFrame);
	lastFrame = time;

	return delta;
    }

    public void reset() {
	lastFrame = getTime();
    }
}
