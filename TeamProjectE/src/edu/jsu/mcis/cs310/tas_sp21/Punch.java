/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_sp21;

import java.sql.Timestamp;

/**
 *
 * @author jaydoan
 */
public class Punch {
    int id;

    Terminal terminal;

    Badge badge;

    Timestamp originalTimeStamp;

    PunchType punchType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Timestamp getOriginalTimeStamp() {
        return originalTimeStamp;
    }

    public void setOriginalTimeStamp(Timestamp originalTimeStamp) {
        this.originalTimeStamp = originalTimeStamp;
    }

    public PunchType getPunchType() {
        return punchType;
    }

    public void setPunchType(PunchType punchType) {
        this.punchType = punchType;
    }

    public String printOriginalTimestamp() {
        return "#" + badge.getId() + " " + CommonUtil.convertPunchTypeByDescription(punchType.getDescription())
                + ": " + DateTimeUtils.getDayOfWeekFromTimestamp(originalTimeStamp) + " " + DateTimeUtils.convertTimestampToDateTimeString(originalTimeStamp);
    }
}
