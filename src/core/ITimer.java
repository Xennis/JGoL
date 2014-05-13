package core;

/**
 * The Timer is used for tracking the time a mainloop and other specific tasks
 * take. with getDelta the time since the last function call is tracked,
 * getTime() returns the current time.
 * 
 * @author Jasper
 * 
 */
public interface ITimer {

    /**
     * get the current time in ms
     * 
     * @return current time in ms
     */
    public abstract long getTime();

    /**
     * get the time since the last getDelta() function call
     * 
     * @return time passed since the last call of getDelta()
     */
    public abstract int getDelta();

}