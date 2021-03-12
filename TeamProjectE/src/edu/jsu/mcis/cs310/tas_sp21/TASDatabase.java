package edu.jsu.mcis.cs310.tas_sp21;


/**
 *
 * @author adamparker
 */
public class TASDatabase {
    private static TASLogic tasLogic;

    public static Badge getBadge(String id) {
        return tasLogic.getBadge(id);
    }

    public static Punch getPunch(int id) {
        return tasLogic.getPunch(id);
    }

    public static Shift getShift(int id) {
        return tasLogic.getShift(id);
    }

    public static Shift getShift(Badge badge) {
        return tasLogic.getShift(badge);
    }
}