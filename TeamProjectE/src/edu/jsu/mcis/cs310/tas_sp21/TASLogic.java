package edu.jsu.mcis.cs310.tas_sp21;


//import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.*;
import java.util.TimeZone;
import java.sql.Timestamp;

/**
 *
 * @author Jay Doan
 */

public class TASLogic {
    // init database constants
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/tas?useSSL=false&useUnicode=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=GMT-5";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    // init connection object
    private static Connection connection;

    // connect database
    public static Connection connect() {
        if (connection == null) {
            try {
                TimeZone timeZone = TimeZone.getTimeZone("GMT-5");
                TimeZone.setDefault(timeZone);

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
        String query = "SELECT p.id, p.badgeid, pt.description, p.originaltimestamp, p.terminalid, p.punchtypeid" +
                        " FROM punch P INNER JOIN punchtype PT ON p.punchtypeid = pt.id WHERE p.id=" + id;
        Punch punch = new Punch();
        try {
            ResultSet rs = connect().createStatement().executeQuery(query);
            while (rs.next()) {
                punch = processRowPunch(rs, punch);
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
                shift.setId(rs.getInt("id"));
                shift.setDescription(rs.getString("description"));
                shift.setStart(rs.getTime("start"));
                shift.setStop(rs.getTime("stop"));
                shift.setLunchStart(rs.getTime("lunchstart"));
                shift.setLunchStop(rs.getTime("lunchstop"));
                shift.setInterval(rs.getInt("interval"));
                shift.setGracePeriod(rs.getInt("graceperiod"));
                shift.setDock(rs.getInt("dock"));
                shift.setLunchDeduct(rs.getInt("lunchdeduct"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return shift;
    }

    public static int insertPunch(Punch punch) {
        String query = "INSERT INTO punch (terminalid, badgeid, originaltimestamp, punchtypeid) VALUES ("
                            + punch.getTerminalid() + ", '"
                            + punch.getBadgeid() + "', '"
                            + punch.getTimestamp() + "', "
                            + punch.getPunchtypeid() + ")";
        try {
            PreparedStatement statement = connect().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                punch.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return punch.getId();
    }

    public static ArrayList<Punch> getDailyPunchList(Badge badge, long timeInMillis) {
        String date = DateTimeUtils.convertMilliSecondsToFormattedDate(timeInMillis);
        ArrayList<Punch> punches = new ArrayList<>();
        String query = "SELECT p.id, p.badgeid, pt.description, p.originaltimestamp, p.terminalid, p.punchtypeid" +
                        " FROM punch P INNER JOIN punchtype PT ON p.punchtypeid = pt.id " +
                        "WHERE p.badgeid = '" + badge.getId() + "' AND DATE(originaltimestamp) = '" + date + "' ORDER BY originaltimestamp";

    try {
            ResultSet rs = connect().createStatement().executeQuery(query);
            while (rs.next()) {
                Punch punch = new Punch();
                punch = processRowPunch(rs, punch);
                punches.add(punch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return punches;
    }

    private static Punch processRowPunch(ResultSet rs, Punch punch) throws SQLException {
        punch.setId(rs.getInt("id"));
        punch.setBadgeid(rs.getString("badgeid"));
        punch.setTerminalid(rs.getInt("terminalid"));
        punch.setPunchtypeid(rs.getInt("punchtypeid"));
        punch.setBadge(new Badge(rs.getString("badgeid")));
        punch.setPunchType(new PunchType(rs.getString("description")));
        punch.setOriginaltimestamp(rs.getTimestamp("originaltimestamp"));

        return punch;
    }

    public static Time getTimeFirstByDateAndBadge(Timestamp timestamp, String badgeid) {
        Time timeFirst = null;
        String query = "SELECT min(originaltimestamp) FROM punch " +
                "WHERE DATE_FORMAT(originaltimestamp , '%Y %m %d') = DATE_FORMAT('" + timestamp + "', '%Y %m %d') and badgeid = '"+ badgeid +"'";

        try {
            ResultSet rs = connect().createStatement().executeQuery(query);
            while (rs.next()) {
                timeFirst = rs.getTime(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return timeFirst;
    }
}