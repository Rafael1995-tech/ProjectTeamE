package edu.jsu.mcis.cs310.tas_sp21;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MINUTES;
import java.sql.Timestamp;

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
    
    private int interval;
    private int gracePeriod;
    private int dock;
    private LocalTime lunchStart;
    private LocalTime lunchStop;
    private long lunchDuration;
    private int lunchDeduct;
    private long shiftDuration;
    
    private LocalTime start;
    private LocalTime stop;
    

    public Shift(String id, String description, LocalTime start, LocalTime stop, int interval, int graceperiod, int dock, int lunchstart, int lunchstop, int lunchdeduct) {
        
        int starthour = 7;  // delete later!
        int startminute = 0; // delete later!
        
        this.start = LocalTime.of(starthour, startminute);
        
        this.shift = shift;
        this.id = id;
        this.description = description;
        this.start = start;
        this.stop = stop;
        this.interval = interval;
        this.gracePeriod = graceperiod;
        this.dock = dock;
        this.lunchStart = lunchStart;
        this.lunchStop = lunchStop;
        this.lunchDeduct = lunchDeduct;
        this.lunchDuration = MINUTES.between(lunchStart, lunchStop);
        this.shiftDuration = MINUTES.between(start, stop);
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }



    public LocalTime getStart() {
        return start;
    }

    public LocalTime getStop() {
        return stop;
    }

    public int getInterval() {
        return interval;
    }


    public int getGracePeriod() {
        return gracePeriod;
    }


    public int getDock() {
        return dock;
    }

    public LocalTime getLunchstart() {
        return lunchStart;
    }

    public LocalTime getLunchstop() {
        return lunchStop;
    }
 
    public int getLunchDeduct() {
        return lunchDeduct;
    }
    public long getLunchDuration() {
        return lunchDuration;
    }
@Override
 public String toString() {
        
        //REFERENCE: description: start time - stop time, 
        //EXAMPLE1: "Shift 1: 07:00 - 15:30 (510 minutes); Lunch: 12:00 - 12:30 (30 minutes)"
        //EXAMPLE2: "Shift Early Lunch: 07:00 - 15:30 (510 minutes); Lunch: 11:30 - 12:00 (30 minutes)"
        StringBuilder s = new StringBuilder();
        
        s.append(description).append(": ");
        s.append(start).append(" - ").append(stop);
        s.append(" (").append(shiftDuration).append(" minutes);");
        s.append(" Lunch: ").append(lunchStart).append(" - ").append(lunchStop);
        s.append(" (").append(lunchDuration).append(" minutes)");
        
        return s.toString();
    }
}

