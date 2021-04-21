/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_sp21;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.sql.Time;
import java.time.LocalDateTime;

/**
 *
 * @author jaydoan
 */
public class DateTimeUtils {
    public static final String DATE_TIME_PATTERN = "MM/dd/yyyy HH:mm:ss";
    public static final String DATE_PATTERN = "MM/dd/yyyy";
    public static final String DB_DATE_PATTERN = "yyyy-MM-dd";
    public static final String DB_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DAYOFWEEK_PATTERN = "EEEE";

    public static String convertTimestampToDateTimeString(Timestamp timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        return timestamp.toLocalDateTime().format(formatter);
    }

    public static String convertTimestampToDateString(Timestamp timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return timestamp.toLocalDateTime().format(formatter);
    }

    public static String convertTimeToString(Time time) {
        return time.toString().substring(0,5);
    }

    public static String getDayOfWeekFromTimestamp(Timestamp timestamp) {
        String dayOfWeek = (new SimpleDateFormat(DAYOFWEEK_PATTERN)).format(timestamp.getTime());

        return dayOfWeek.substring(0,3).toUpperCase();
    }

    public static Time getTimeFromTimestamp(Timestamp timestamp) {
        Time time = new Time(timestamp.getTime());
        return time;
    }

    public static long timeDifference(Time startTime, Time stopTime) {
        Duration d = Duration.between(startTime.toLocalTime(), stopTime.toLocalTime());

        return d.toMinutes();
    }

    public static String convertMilliSecondsToFormattedDate(long milliSeconds){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DB_DATE_PATTERN);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static Time roundTimeNearestInterval(Time time, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        if (calendar.get(Calendar.SECOND) >= 30) {
            calendar.add(Calendar.MINUTE, 1);
        }

        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        int mod = unroundedMinutes % interval;
        calendar.add(Calendar.MINUTE, mod < (interval/2 + 1) ? -mod : (interval-mod));
        calendar.set(Calendar.SECOND, 0);
        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());

        return getTimeFromTimestamp(timestamp);
    }

    public static String convertTimestampToString(Timestamp timestamp){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DB_DATE_PATTERN);
        return timestamp.toLocalDateTime().format(formatter).toString();
    }

    public static Timestamp convertStringToTimestamp(String timeString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DB_DATE_TIME_PATTERN);
        return Timestamp.valueOf(LocalDateTime.parse(timeString, formatter));
    }
}
