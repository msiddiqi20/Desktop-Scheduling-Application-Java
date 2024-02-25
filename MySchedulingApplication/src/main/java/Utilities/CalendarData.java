package Utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**This is the abstract Calendar Data class.*/
public abstract class CalendarData {

    private static ArrayList<String> thisMonth = new ArrayList<>();
    private static ArrayList<String> thisWeek = new ArrayList<>();

    /**This is the This Month getter.
     * This method returns the ArrayList of the dates in the month.
     * @return Returns the ArrayList (String) of the dates in the month.*/
    public static ArrayList<String> getThisMonth(){
        return thisMonth;
    }

    /**This is the This Week getter.
     * This method returns the ArrayList of the dates in the week.
     * @return Returns the ArrayList (String) of the dates in the week.*/
    public static ArrayList<String> getThisWeek(){
        return thisWeek;
    }

    /**This is the This Week setter.
     * This method sets all the dates of this week.*/
    public static void setThisWeek(){

        Calendar calendar = Calendar.getInstance();

        for(int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
            calendar.set(Calendar.DAY_OF_WEEK, i);
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            thisWeek.add(myFormat.format(calendar.getTime()));
        }

    }

    /**This is the This Month setter.
     * This method sets all the dates of this month.*/
    public static void setThisMonth(){

        Calendar calendar = Calendar.getInstance();

        for(int i = 1; i <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {

            calendar.set(Calendar.DAY_OF_MONTH, i);
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            thisMonth.add(myFormat.format(calendar.getTime()));

        }

    }

}
