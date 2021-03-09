package edu.jsu.mcis.cs310.tas_sp21;

import java.sql.*;

/**
 *
 * @author adamparker
 */
public class TASDatabase {
    Connection conn = null;
    PreparedStatement pstSelect = null, pstUpdate = null;
    ResultSet resultset = null;
    ResultSetMetaData metadata = null;
    String query;
    public TASDatabase(){
        try{
            String server = ("jdbc:mysql://localhost/TAS.sql");
            String username = "root";
            String password = "root";
        }
    }
    public void close(){
        try{
            conn.close();
        }
        catch(Exception e){};
    }
    public Punch getPunch(int punchID){
        Punch newPunch = new Punch();
        
        return newPunch;
    }
    public Badge getBadge(int badgeID){
        Badge newBadge = new Badge();
        
        return newBadge;
    }
    public Shift getShift(int databaseID){
        Shift newShift = new Shift();
        
        return newShift;
    }
    public Shift getShift(Badge userBadge){
        Shift newShift = new Shift();
        
        return newShift;
    }
}
