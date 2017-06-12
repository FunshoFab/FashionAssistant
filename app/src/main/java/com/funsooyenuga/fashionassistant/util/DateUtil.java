package com.funsooyenuga.fashionassistant.util;

import android.support.annotation.NonNull;
import android.text.format.DateUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by FAB THE GREAT on 11/06/2017.
 */

public class DateUtil {

    public static final int ONE_DAY  = 86400000; // milliseconds in one day

    public static String formatDate(@NonNull Date date) {
        String pattern = "EEE, d MMM yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static String formatDateWithoutYear(@NonNull Date date) {
        String pattern = "EEE, d MMM";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static Date stringToDate(@NonNull String formattedDate) {
        String pattern = "EEE, d MMM yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        Date date = null;
        try {
            date = dateFormat.parse(formattedDate);
        } catch (ParseException e) {
            Log.d("Util", e.toString());
        }
        return date;
    }

    public static String formatToRelativeDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateToDisplay;

        Calendar tomorrowCal = Calendar.getInstance();
        Calendar yesterdayCal = Calendar.getInstance();

        tomorrowCal.add(Calendar.DAY_OF_YEAR, 1);
        yesterdayCal.add(Calendar.DAY_OF_YEAR, -1);

        Date tomorrow = tomorrowCal.getTime();
        Date yesterday = yesterdayCal.getTime();

        boolean isToday = DateUtils.isToday(date.getTime());
        boolean isYesterday = dateFormat.format(date).equals(dateFormat.format(yesterday));
        boolean isTomorrow = dateFormat.format(date).equals(dateFormat.format(tomorrow));

        if (isToday) {
            dateToDisplay = "Today";
        } else if (isYesterday) {
            dateToDisplay = "Yesterday";
        } else if (isTomorrow) {
            dateToDisplay = "Tomorrow";
        } else {
            dateToDisplay = DateUtil.formatDateWithoutYear(date);
        }
        return dateToDisplay;
    }

}
