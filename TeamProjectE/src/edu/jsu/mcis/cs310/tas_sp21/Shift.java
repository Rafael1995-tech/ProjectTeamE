package edu.jsu.mcis.cs310.tas_sp21;

public class Shift {

    //There should also be " getShift() " methods
    //UNIX_TIMESTAMP()
    //To retrieve the hours and minutes from the TIME fields of the 
    //Shift table as separate values, use the HOUR() and MINUTE() functions of MySQL.
    //"Shift Start” and “Shift Stop"
    //“Lunch Start” and “Lunch Stop” 
    private String shift;
    private String id;
    private String description;    
    private int start;
    private int stop;
    private int interval;
    private int graceperiod;
    private int dock;
    private int lunchstart;
    private int lunchstop;
    private int lunchduration;
    private int lunchdeduct;

    public Shift(String id, String description, int start, int stop, int interval, int graceperiod, int dock, int lunchstart, int lunchstop, int lunchdeduct) {
        this.shift = shift;
        this.id = id;
        this.description = description;
        this.start = start;
        this.stop = stop;
        this.interval = interval;
        this.graceperiod = graceperiod;
        this.dock = dock;
        this.lunchstart = lunchstart;
        this.lunchstop = lunchstop;
        this.lunchdeduct = lunchdeduct;
    }

        public String getShift() {
            //long startTime = "";
            //long stopTime = "";
        return shift;
    }
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getStart() {
        return start;
    }

    public int getStop() {
        return stop;
    }

    public int getInterval() {
        return interval;
    }

    public int getGraceperiod() {
        return graceperiod;
    }

    public int getDock() {
        return dock;
    }

    public int getLunchstart() {
        return lunchstart;
    }

    public int getLunchstop() {
        return lunchstop;
    }

    public int getLunchdeduct() {
        return lunchdeduct;
    }
    
    @Override
    public String toString() {
        //"#07901755, (Terrell, Kenneth R)"
        StringBuilder s = new StringBuilder();
        s.append("#").append(id).append(" ");
        s.append("(").append(description).append(")");
        
        return ( s.toString() );
    }

}
