package edu.jsu.mcis.cs310.tas_sp21;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MINUTES;
import java.sql.Timestamp;
import java.sql.Time;

public class Shift {
    int id;
    String description;
    int interval;
    int gracePeriod;
    int dock;

    Time start;
    Time stop;
    Time lunchStart;
    Time lunchStop;

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

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getStop() {
        return stop;
    }

    public void setStop(Time stop) {
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

    public Time getLunchStart() {
        return lunchStart;
    }

    public void setLunchStart(Time lunchStart) {
        this.lunchStart = lunchStart;
    }

    public Time getLunchStop() {
        return lunchStop;
    }

    public void setLunchStop(Time lunchStop) {
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
        return description + ": " + DateTimeUtils.convertTimeToString(start)
                + " - "
                + DateTimeUtils.convertTimeToString(stop)
                + " ("
                + DateTimeUtils.timeDifference(start, stop)
                + " minutes); Lunch: "
                + DateTimeUtils.convertTimeToString(lunchStart)
                + " - "
                + DateTimeUtils.convertTimeToString(lunchStop)
                + " ("
                + DateTimeUtils.timeDifference(lunchStart, lunchStop)
                + " minutes)";
    }
}
