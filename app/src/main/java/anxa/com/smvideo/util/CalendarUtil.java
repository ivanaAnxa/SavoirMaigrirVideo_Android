package anxa.com.smvideo.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by aprilanxa on 11/08/2017.
 */

public class CalendarUtil {

    public static Date toDate(Long timestamplong) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamplong * 1000);
        return calendar.getTime();

    }

    public static int getCurrentWeekNumber(long coachingStartDate, Date endTime) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTimeInMillis(coachingStartDate * 1000);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endTime);

        long diff = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis(); //result in millis

        double days = diff / (24 * 60 * 60 * 1000);
        double modWeeks = Math.ceil((days + 1) / 7.0);

//        if (modWeeks==0){
//            return 1;
//        }
        return (int) modWeeks;
    }

    public static int getDaysDiffToCurrent(long coachingStartDate) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTimeInMillis(coachingStartDate * 1000);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(new Date());

        long diff = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis(); //result in millis

        double days = diff / (24 * 60 * 60 * 1000);

        System.out.println("getDaysDiff: start: " + startCalendar.getTimeInMillis());
        System.out.println("getDaysDiff: end: " + endCalendar.getTimeInMillis());
        System.out.println("getDaysDiff: diff: " + diff + "- --" + days);


        return (int) days;
    }

    public static int getCurrentDayNumber() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        int dayNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        if (dayNumber == 0) {
            dayNumber = 7;
        }
        return dayNumber;
    }

    public static int getDayNumber(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        if (dayNumber == 0) {
            dayNumber = 7;
        }
        return dayNumber;
    }
}
