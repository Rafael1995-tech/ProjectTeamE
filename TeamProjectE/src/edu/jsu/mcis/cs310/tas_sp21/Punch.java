/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_sp21;

import java.sql.Timestamp;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author jaydoan
 */
public class Punch {
    int id;
    String badgeid;
    int terminalid;
    int punchtypeid;
    String adjustmenttype;
    Time adjustedtimestamp;
    Timestamp originaltimestamp;

    Terminal terminal;
    Badge badge;
    PunchType punchType;

    public Punch() {}

    public Punch(Badge badge, int terminalid, int punchtypeid) {
        this.badgeid = badge.getId();
        this.terminalid = terminalid;
        this.punchtypeid = punchtypeid;
        this.originaltimestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBadgeid() {
        return badgeid;
    }

    public void setBadgeid(String badgeid) {
        this.badgeid = badgeid;
    }

    public int getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(int terminalid) {
        this.terminalid = terminalid;
    }

    public int getPunchtypeid() {
        return punchtypeid;
    }

    public void setPunchtypeid(int punchtypeid) {
        this.punchtypeid = punchtypeid;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public PunchType getPunchType() {
        return punchType;
    }

    public void setPunchType(PunchType punchType) {
        this.punchType = punchType;
    }

    public long getOriginaltimestamp() {
        return originaltimestamp.getTime();
    }

    public Timestamp getTimestamp() {
        return originaltimestamp;
    }

    public void setOriginaltimestamp(Timestamp originaltimestamp) {
        this.originaltimestamp = originaltimestamp;
    }

    public Time getAdjustedtimestamp() {
        return adjustedtimestamp;
    }

    public void setAdjustedtimestamp(Time adjustedtimestamp) {
        this.adjustedtimestamp = adjustedtimestamp;
    }

    public String getAdjustmenttype() {
        return adjustmenttype;
    }

    public void setAdjustmenttype(String adjustmenttype) {
        this.adjustmenttype = adjustmenttype;
    }

    public String printOriginalTimestamp() {
        return "#" + badge.getId() + " " + CommonUtil.convertPunchTypeByDescription(punchType.getDescription())
                + ": " + DateTimeUtils.getDayOfWeekFromTimestamp(originaltimestamp) + " " + DateTimeUtils.convertTimestampToDateTimeString(originaltimestamp);
    }


    public void adjust(Shift shift) {
        Time originalTime = DateTimeUtils.getTimeFromTimestamp(this.originaltimestamp);
        LocalTime originalLocalTime = originalTime.toLocalTime();
        LocalTime lunchStartLocalTime = shift.lunchStart.toLocalTime();
        LocalTime lunchStopLocalTime = shift.lunchStop.toLocalTime();

        if (this.punchtypeid == 1) {
            if (DateTimeUtils.timeDifference(originalTime, shift.start) < shift.interval
                    && DateTimeUtils.timeDifference(shift.start, originalTime) < shift.gracePeriod) {
                this.adjustmenttype = CommonUtil.SHIFT_START;
                this.adjustedtimestamp = shift.start;
            } else if (originalLocalTime.isAfter(lunchStartLocalTime)
                    && originalLocalTime.isBefore(lunchStopLocalTime)) {
                this.adjustmenttype = CommonUtil.LUNCH_STOP;
                this.adjustedtimestamp = shift.lunchStop;
            } else if (DateTimeUtils.timeDifference(shift.start, originalTime) >= shift.gracePeriod
                    && DateTimeUtils.timeDifference(shift.start, originalTime) < shift.dock) {
                this.adjustmenttype = CommonUtil.SHIFT_DOCK;
                this.adjustedtimestamp = Time.valueOf(shift.start.toLocalTime().plusMinutes(shift.dock));
            } else if (DateTimeUtils.timeDifference(DateTimeUtils.getTimeFromTimestamp(this.originaltimestamp), shift.start) < shift.interval) {
                this.adjustmenttype = CommonUtil.INTERVAL_ROUND;
                this.adjustedtimestamp = shift.start;
            } else if (DateTimeUtils.timeDifference(DateTimeUtils.getTimeFromTimestamp(this.originaltimestamp), shift.start) < shift.interval) {
                this.adjustmenttype = CommonUtil.NONE;
                this.adjustedtimestamp = shift.start;
            } else {
                this.adjustmenttype = CommonUtil.INTERVAL_ROUND;
                this.adjustedtimestamp = DateTimeUtils.roundTimeNearestInterval(originalTime, shift.interval);
            }
        } else if (this.punchtypeid == 0) {
            if (originalLocalTime.isAfter(lunchStartLocalTime)
                    && originalLocalTime.isBefore(lunchStopLocalTime)
                    && DateTimeUtils.timeDifference(TASLogic.getTimeFirstByDateAndBadge(this.originaltimestamp, this.badgeid), shift.start) < shift.interval) {
                this.adjustmenttype = CommonUtil.LUNCH_START;
                this.adjustedtimestamp = shift.lunchStart;
            } else if (DateTimeUtils.timeDifference(shift.stop, originalTime) < shift.interval
                    && DateTimeUtils.timeDifference(originalTime, shift.stop) < shift.gracePeriod) {
                this.adjustmenttype = CommonUtil.SHIFT_STOP;
                this.adjustedtimestamp = shift.stop;
            } else if (DateTimeUtils.timeDifference(originalTime, shift.stop) >= shift.gracePeriod
                    && DateTimeUtils.timeDifference(originalTime, shift.stop) <= shift.dock) {
                this.adjustmenttype = CommonUtil.SHIFT_DOCK;
                this.adjustedtimestamp = Time.valueOf(shift.stop.toLocalTime().minusMinutes(shift.dock));
            } else if (DateTimeUtils.timeDifference(shift.stop, originalTime) > shift.interval) {
                this.adjustmenttype = CommonUtil.NONE;
                this.adjustedtimestamp = Time.valueOf(originalLocalTime.withSecond(0));
            } else {
                this.adjustmenttype = CommonUtil.INTERVAL_ROUND;
                this.adjustedtimestamp = DateTimeUtils.roundTimeNearestInterval(originalTime, shift.interval);
            }
        }
    }

    public String printAdjustedTimestamp() {
        return "#" + badge.getId() + " " + CommonUtil.convertPunchTypeByDescription(punchType.getDescription())
                + ": " + DateTimeUtils.getDayOfWeekFromTimestamp(originaltimestamp)
                + " " + DateTimeUtils.convertTimestampToDateString(originaltimestamp)
                + " " + adjustedtimestamp
                + " (" + adjustmenttype + ")";
    }
}
