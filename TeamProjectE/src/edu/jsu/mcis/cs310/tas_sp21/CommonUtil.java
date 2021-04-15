/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_sp21;

/**
 *
 * @author jaydoan
 */
public class CommonUtil {
    public static final String CLOCK_IN = "Clock In";
    public static final String CLOCK_OUT = "Clock Out";
    public static final String TIME_OUT = "Time Out";

    public static final String CLOCKED_IN = "CLOCKED IN";
    public static final String CLOCKED_OUT = "CLOCKED OUT";
    public static final String TIMED_OUT = "TIMED OUT";

    public static final String SHIFT_START = "Shift Start";
    public static final String SHIFT_STOP = "Shift Stop";
    public static final String LUNCH_START = "Lunch Start";
    public static final String LUNCH_STOP = "Lunch Stop";
    public static final String SHIFT_DOCK = "Shift Dock";
    public static final String INTERVAL_ROUND = "Interval Round";
    public static final String NONE = "None";

    public static String convertPunchTypeByDescription(String description) {
        String des = "";
        switch (description) {
            case CLOCK_IN:
                des = CLOCKED_IN;
                break;
            case CLOCK_OUT:
                des = CLOCKED_OUT;
                break;
            case TIME_OUT:
                des = TIMED_OUT;
                break;
        }

        return des;
    }
}
