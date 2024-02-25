package Utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**This is the abstract Time Data class.*/
public abstract class TimeData {

    private static ObservableList<String> allTime = FXCollections.observableArrayList();
    private static ArrayList<String> businessHours = new ArrayList<String>();

    /**This is the All Time getter.
     * This method returns All Time in the day in the intervals of 15 minutes.
     * @return Returns all time (intervals of 15 minutes, 24-hour clock) in the form of ObservableList (String).*/
    public static ObservableList<String> getAllTime(){
        return allTime;
    }

    /**This is the Business Hours getter.
     * This method returns all the time during business hours.
     * @return Returns all business time (intervals of 15 minutes, 24-hour clock) in the form of ObservableList (String).*/
    public static ArrayList<String> getBusinessHours(){
        return businessHours;
    }

    /**This is the All Time setter.
     * This method sets All Time.*/
    public static void setAllTime(){

        for (int hour = 0; hour < 24; hour++) {

            for (int minute = 0; minute < 60; minute += 15) {

                String time = String.format("%02d:%02d", hour, minute);
                allTime.add(time);

            }

        }

        setBusinessHours();

    }

    /**This is the Business Hours setter.
     * This method sets the Business Hours.*/
    public static void setBusinessHours(){

        for (int hour = 8; hour < 23; hour++) {

            for (int minute = 0; minute < 60; minute += 15) {

                String time = String.format("%02d:%02d", hour, minute);
                businessHours.add(time);

            }

        }
    }

}
