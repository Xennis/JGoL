package logic;

import java.util.Map;

/**
 * 
 * @author Felix K.
 * @version 2.0
 * 
 */
public interface IGameLog {

    /*
     * ----- Formats ------ Round 42, Player 5, Figure 13 ==> moved from 14 to
     * 15 with ticket: walk Round 42, Player 5, Figure 13 ==> Collision happened
     * with Player 3, Figure 1, deleting Figure 13 Round 42, Player 5, Figure 14
     * ==> moved from 1 to 2 with ticket: fly
     */
    public abstract void updateLog(String value);

    public abstract Map<Integer, String> returnLog();

    public abstract Map<Integer, String> returnThiefLog();

    public abstract void updateThiefLog(String value);

    public abstract void clearLog();

}