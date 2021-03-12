package edu.jsu.mcis.cs310.tas_sp21;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jay Doan
 */

public class TASLogic {
  
  // init database constants
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/tas";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    // init connection object
    private static Connection connection;

    // connect database
    public static Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // disconnect database
    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Badge getBadge(String id) {
        String query = "select * from badge WHERE id = '" + id + "'";
        Badge badge = new Badge();
        try {
            ResultSet rs = connect().createStatement().executeQuery(query);
            while (rs.next()) {
                badge.setId(rs.getString("id"));
                badge.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return badge;
    }

    public static Punch getPunch(int id) {
        String query = "SELECT p.badgeid, pt.description, p.originaltimestamp FROM punch P INNER JOIN punchtype PT ON p.punchtypeid = pt.id WHERE p.id=" + id;
        Punch punch = new Punch();
        try {
            ResultSet rs = connect().createStatement().executeQuery(query);
            while (rs.next()) {
                punch.setBadge(new Badge(rs.getString("badgeid")));
                punch.setPunchType(new PunchType(rs.getString("description")));
                punch.setOriginalTimeStamp(rs.getTimestamp("originaltimestamp"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return punch;
    }

    public static Shift getShift(int id) {
        String query = "select * from shift WHERE id = " + id;
        return getShiftByQuery(query);
    }

    public static Shift getShift(Badge badge) {
        String query = "SELECT s.* FROM employee E INNER JOIN shift S ON e.shiftid = s.id WHERE e.badgeid = '" + badge.getId() + "' ";

        return getShiftByQuery(query);
    }

    private static Shift getShiftByQuery(String query) {
        Shift shift = new Shift();
        try {
            ResultSet rs = connect().createStatement().executeQuery(query);
            while (rs.next()) {
                shift.setDescription(rs.getString("description"));
                shift.setStart(rs.getTimestamp("start"));
                shift.setStop(rs.getTimestamp("stop"));
                shift.setLunchStart(rs.getTimestamp("lunchstart"));
                shift.setLunchStop(rs.getTimestamp("lunchstop"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return shift;
    }
}
