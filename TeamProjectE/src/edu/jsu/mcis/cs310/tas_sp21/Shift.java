package edu.jsu.mcis.cs310.tas_sp21;

import java.sql.Timestamp;

public class Shift {

    //There should also be " getShift() " methods
    //UNIX_TIMESTAMP()
    //To retrieve the hours and minutes from the TIME fields of the 
    //Shift table as separate values, use the HOUR() and MINUTE() functions of MySQL.
    //"Shift Start” and “Shift Stop"
    //“Lunch Start” and “Lunch Stop” 
    int id;

    String description;

    Timestamp start;

    Timestamp stop;

    int interval;

    int gracePeriod;

    int dock;

    Timestamp lunchStart;

    Timestamp lunchStop;

    int lunchDeduct;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getStop() {
        return stop;
    }

    public void setStop(Timestamp stop) {
        this.stop = stop;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public int getDock() {
        return dock;
    }

    public void setDock(int dock) {
        this.dock = dock;
    }

    public Timestamp getLunchStart() {
        return lunchStart;
    }

    public void setLunchStart(Timestamp lunchStart) {
        this.lunchStart = lunchStart;
    }

    public Timestamp getLunchStop() {
        return lunchStop;
    }

    public void setLunchStop(Timestamp lunchStop) {
        this.lunchStop = lunchStop;
    }

    public int getLunchDeduct() {
        return lunchDeduct;
    }

    public void setLunchDeduct(int lunchDeduct) {
        this.lunchDeduct = lunchDeduct;
    }

    @Override
    public String toString() {
        return description + ": " + DateTimeUtils.convertTimestampToString(start)
                + " - "
                + DateTimeUtils.convertTimestampToString(stop)
                + " ("
                + DateTimeUtils.timeStampDifference(start, stop)
                + " minutes); Lunch: "
                + DateTimeUtils.convertTimestampToString(lunchStart)
                + " - "
                + DateTimeUtils.convertTimestampToString(lunchStop)
                + " ("
                + DateTimeUtils.timeStampDifference(lunchStart, lunchStop)
                + " minutes)";
    }

}
