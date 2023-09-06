package com.eazy.stcbusiness.utils;


import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;


public class DateUtil {

    public static String getCurrentTime(String  dateTime){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm a", Locale.US);
        Date date = formatDateTime(dateTime);

        // Get Raw Offset
        Calendar mCalendar = new GregorianCalendar();
        TimeZone mTimeZone = mCalendar.getTimeZone();
        int mGMTOffset = mTimeZone.getRawOffset();

        return format.format(date.getTime() + mGMTOffset);
    }

    public static String formatDateUptoCurrentRegion(String dateTime){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Date date = formatDateTime(dateTime);

        // Get Raw Offset
        Calendar mCalendar = new GregorianCalendar();
        TimeZone mTimeZone = mCalendar.getTimeZone();
        int mGMTOffset = mTimeZone.getRawOffset();

        return format.format(date.getTime() + mGMTOffset);
    }

    public static Date formatDateTime(String dateTime){
        return formatDate(dateTime, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date formatDate(String dateTime, String form){
        SimpleDateFormat format = new SimpleDateFormat(form, Locale.US);
        Date newDate = null;
        try {
            newDate = format.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert newDate != null;
        return newDate;
    }


    public static String formatDate(String dateTime){
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        Date date = formatDateTime(dateTime);
        return format.format(date.getTime() );
    }

    public static String formatDateComplaint(String dateTime){
        SimpleDateFormat format = new SimpleDateFormat("dd, MMM yyyy", Locale.US);
        Date date = formatDateTime(dateTime);
        return format.format(date.getTime() );
    }

    //set time to local after get time from server
    public static String formatTimeZoneLocal(String dateTime){
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
        Date date = formatDateTime(dateTime);
        return format.format(date.getTime() + new GregorianCalendar().getTimeZone().getRawOffset());
    }

    public static String formatTimeZoneLocalDay(String dateTime){
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        Date date = formatDateTime(dateTime);
        return format.format(date.getTime() + new GregorianCalendar().getTimeZone().getRawOffset());
    }
    public static String formatTimeLocal(String dateTime){
        SimpleDateFormat format = new SimpleDateFormat(" hh:mm a", Locale.US);
        Date date = formatDateTime(dateTime);
        return format.format(date.getTime() + new GregorianCalendar().getTimeZone().getRawOffset());
    }

    public static String formatTimeServer(String dateTime){
        // Convert From "2022-01-24 09:48:56" To "09:48 PM"
        SimpleDateFormat format = new SimpleDateFormat(" hh:mm a", Locale.US);
        Date date = formatDateTime(dateTime);
        return format.format(date.getTime());
    }

    public static String formatDateToConvertTimeZone(String dateTime){
        SimpleDateFormat format = new SimpleDateFormat("dd, MMM yyyy hh:mm a", Locale.US);
        Date date = formatDateTime(dateTime);
        return format.format(date.getTime() +new GregorianCalendar().getTimeZone().getRawOffset());
    }

    public static String formatDateToConvertOnlyTimeZone(String dateTime){
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a", Locale.US);
        Date date = formatDateTime(dateTime);
        return format.format(date.getTime() +new GregorianCalendar().getTimeZone().getRawOffset());
    }

    public static String formatDateToConvertOnlyTimeZoneNoSecond(String dateTime){
        SimpleDateFormat format = new SimpleDateFormat("hh : mm a", Locale.US);
        Date date = formatDateTime(dateTime);
        return format.format(date.getTime() +new GregorianCalendar().getTimeZone().getRawOffset());
    }


    public static String formatOnlyDay(String dateTime){
        SimpleDateFormat format = new SimpleDateFormat("dd", Locale.US);
        Date date = formatDateTimeInspection(dateTime);
        return format.format(date.getTime() );
    }

    public static String formatOnlyMonth(String dateTime, boolean isMonthChar){
        SimpleDateFormat format;
        if (isMonthChar){
            format = new SimpleDateFormat("MMM", Locale.US);
        } else {
            format = new SimpleDateFormat("MM", Locale.US);
        }
        Date date = formatDateTimeInspection(dateTime);
        return format.format(date.getTime() );
    }

    public static String formatOnlyYear(String dateTime){
        SimpleDateFormat format = new SimpleDateFormat("yyyy", Locale.US);
        Date date = formatDateTimeInspection(dateTime);
        return format.format(date.getTime() );
    }

    public static Date formatDateTimeInspection(String dateTime){
        return formatDate(dateTime, "yyyy-MM-dd");
    }

    public static Date formatSimpleDateToTimeZone(String dateTime){
        // => Covert From 2018-09-27 05:39:41 To 2018-09-27T05:39:41.023987+00:00
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = format.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String formatSimpleDateToDayDate(String dateTime, String formatFrom, String formatTo){
        // => Covert From 2018-09-27 To Mon 2018-09-27
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(formatFrom);
        try {
            Date date = dateFormat.parse(dateTime);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormatTo = new SimpleDateFormat(formatTo);
            assert date != null;
            return dateFormatTo.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertMinutesToHours(String minutes){
        SimpleDateFormat sdf = new SimpleDateFormat("mm");

        try {
            Date dt = sdf.parse(minutes);
            sdf = new SimpleDateFormat("HH : mm");
            assert dt != null;
            return sdf.format(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String convertTimeByCalendar(int hours, int minutes){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        return timeFormat.format(calendar.getTime());
    }

    public static long milliseconds(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try
        {
            Date mDate = sdf.parse(date);
            assert mDate != null;
            return mDate.getTime();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @SuppressLint("SimpleDateFormat")
    public static SimpleDateFormat simpleDateFormat(String dfm){
        return new SimpleDateFormat(dfm);
    }

    public static String calculateDuring(String CurrentDate, String FinalDate, SimpleDateFormat dates){
        String dayDifference = "";
        Date date1;
        Date date2;
        try {
            date1 = dates.parse(CurrentDate);
            date2 = dates.parse(FinalDate);
            if (date1 != null && date2 != null) {
                long difference = Math.abs(date1.getTime() - date2.getTime());
                long differenceDates = difference / (24 * 60 * 60 * 1000);
                dayDifference = Long.toString(differenceDates);
            }
        } catch (ParseException exception) {
            AppLOGG.INSTANCE.d("hejklehjkjkl", Objects.requireNonNull(exception.getMessage()));
        }
        return dayDifference;
    }

    private static int convertDayToMonth(String CurrentDate, String FinalDate, SimpleDateFormat simpleDateFormat){
        String day = calculateDuring(CurrentDate, FinalDate, simpleDateFormat);
        int months = 0;
        try {
            if (Integer.parseInt(day) >= 30){
                months = Integer.parseInt(day) / 30;
            } else {
                months = Integer.parseInt(day);
            }
        } catch (Exception ignored){ }

        return months;
    }

    // Create Date time
    public static String appendFormatDateTime(String date){
        if (date != null){
            return String.format("%s %s", formatDateComplaint(date), formatDateToConvertOnlyTimeZoneNoSecond(date));
        } else  {
            return ". . .";
        }
    }
}
