/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_sp21;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jaydoan
 */
public class DateTimeUtils {
    public static final String DATE_TIME_PATTERN = "MM/dd/yyyy HH:mm:ss";
    public static final String DB_TIME_PATTERN = "HH:mm:ss";
    public static final String TIME_PATTERN = "HH:mm";

    public static String convertTimestampToDateTimeString(Timestamp timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        return timestamp.toLocalDateTime().format(formatter);
    }

    public static String convertTimestampToString(Timestamp timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        return timestamp.toLocalDateTime().format(formatter);
    }

    public static String getDayOfWeekFromTimestamp(Timestamp timestamp) {
        String day = (new SimpleDateFormat("EEEE")).format(timestamp.getTime());

        return (new SimpleDateFormat("EEEE")).format(timestamp.getTime()).substring(0,3).toUpperCase();
    }

//    public static Timestamp convertStringToTimestamp(String localTime) {
//        try {
//            DateFormat formatter = new SimpleDateFormat(DB_TIME_PATTERN);
//            Date date = (Date) formatter.parse(DB_TIME_PATTERN);
//            Timestamp timeStampDate = new Timestamp(date.getTime());
//
//            return timeStampDate;
//        } catch (ParseException e) {
//            System.out.println("Exception :" + e);
//            return null;
//        }
//    }

    public static long timeStampDifference(Timestamp startTime, Timestamp stopTime) {

        return (stopTime.getTime() - startTime.getTime()) / (60 * 1000);
    }
    
}
