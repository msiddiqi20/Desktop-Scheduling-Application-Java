package Helper;

import Model.Appointments;
import Utilities.CustomerData;
import Utilities.TimeData;

import java.time.*;

/**This is the abstract Time Zone Conversion class.*/
public abstract class TimeZoneConversion {

    /**This is the UTC to Local method.
     * This method changes the Date-Time in UTC (String) to a Date-Time in the systems local Date-Time (String).
     * @param UTCDateTime This is the UTC Date-Time in String Format (YYYY-MM-DD HH:MM:SS)
     * @return Returns the local Date-Time as a String (YYYY-MM-DD HH:MM:SS).*/
    public static String UTCtoLocal(String UTCDateTime) {

        String[] splitDateTime = UTCDateTime.split(" ");

        LocalDate Date = LocalDate.parse(splitDateTime[0]);
        LocalTime Time = LocalTime.parse(splitDateTime[1]);
        ZoneId utc = ZoneId.of("UTC");

        ZonedDateTime zonedUTCDateTime = ZonedDateTime.of(Date, Time, utc);
        ZonedDateTime zonedLocalDateTime = ZonedDateTime.ofInstant(zonedUTCDateTime.toInstant(), ZoneId.systemDefault());
        String localDateTime = zonedLocalDateTime.toLocalDate() + " " + zonedLocalDateTime.toLocalTime();

        return localDateTime;

    }

    /**This is the Local to UTC method.
     * This method changes the Local Date-Time (String) to a UTC Date-Time (String).
     * @param localDate This is the local Date passed as a String (YYYY-MM-DD)
     * @param localTime This is the local Time passed as a String (HH:MM:SS)
     * @return Returns the UTC Date-Time as a String (YYYY-MM-DD HH:MM:SS)*/
    public static String LocaltoUTC(String localDate, String localTime) {

        LocalDate Date = LocalDate.parse(localDate);
        LocalTime Time = LocalTime.parse(localTime);
        ZoneId myZoneID = ZoneId.systemDefault();

        ZonedDateTime zonedLocalDateTime = ZonedDateTime.of(Date, Time, myZoneID);
        ZonedDateTime zonedUTCDateTime = ZonedDateTime.ofInstant(zonedLocalDateTime.toInstant(), ZoneId.of("UTC"));
        String utcDateTime = zonedUTCDateTime.toLocalDate() + " " + zonedUTCDateTime.toLocalTime();

        return utcDateTime;

    }

    /**This is the Check If Business Hours method.
     * This method checks if the Local Date-Time String is within the business hours (EST).
     * @param localDate This is the local Date passed as a String (YYYY-MM-DD)
     * @param localTime This is the local Time passed as a String (HH:MM:SS)
     * @return Returns a Boolean (True if within business hours, False if not within business hours).*/
    public static boolean checkIfBusinessHours(String localDate, String localTime) {

        LocalDate Date = LocalDate.parse(localDate);
        LocalTime Time = LocalTime.parse(localTime);
        ZoneId myZoneID = ZoneId.systemDefault();

        ZonedDateTime zonedLocalDateTime = ZonedDateTime.of(Date, Time, myZoneID);
        ZonedDateTime zonedUTCDateTime = ZonedDateTime.ofInstant(zonedLocalDateTime.toInstant(), ZoneId.of("EST5EDT"));

        if (TimeData.getBusinessHours().contains(zonedUTCDateTime.toLocalTime().toString())) {

            return true;

        } else {

            return false;

        }

    }

    /**This is the Within 15 Minutes method.
     * This method checks if the Local Date-Time String is within 15 minutes of now.
     * @param dateTime This is the local Date-Time passed as a String (YYYY-MM-DD HH:MM:SS)
     * @return Returns a Boolean (True if within 15 minutes, False if not within 15 Minutes).*/
    public static boolean within15Minutes(String dateTime) {

        String[] splitDateTime = dateTime.split(" ");
        LocalTime checkTime = LocalTime.parse(splitDateTime[1]);
        LocalTime localNow = LocalTime.now();
        LocalTime sixteenFromNow = localNow.plusMinutes(16);

        if ((checkTime.isAfter(localNow) || checkTime == localNow) && checkTime.isBefore(sixteenFromNow)) {

            return true;

        } else {

            return false;

        }

    }

    /**This is the Time Overlap method.
     * This method checks if the following start Date-Time and end Date-Time of an appointment overlap with an exiting appointment Date-Times.
     * @param startDate This is the desired Start Date (YYYY-MM-DD).
     * @param startTime This is the desired Start Time (HH:MM:SS).
     * @param endDate This is the desired End Date (YYYY-MM-DD).
     * @param endTime This is the desired End Time (HH:MM:SS)
     * @param customerID This is the customer ID of the desired appointment.
     * @param appointmentID This is the appointment ID of the existing appointment (Enter -1 for new appointment).
     * @return Returns a Boolean (True if time does overlap, False if it does not overlap).*/
    public static boolean timeOverlap(String startDate, String startTime, String endDate, String endTime, int customerID, int appointmentID) {

        LocalDate sDate = LocalDate.parse(startDate);
        LocalTime sTime = LocalTime.parse(startTime);
        LocalDate eDate = LocalDate.parse(endDate);
        LocalTime eTime = LocalTime.parse(endTime);

        LocalDateTime attemptedStartDateTime = LocalDateTime.of(sDate, sTime);
        LocalDateTime attemptedEndDateTime = LocalDateTime.of(eDate, eTime);

        for (Appointments appointment : CustomerData.getCustomersHM().get(customerID).getMyAppointments()) {

            if (appointment.getAppointmentID() != appointmentID) {

                LocalDateTime checkStartDateTime = LocalDateTime.parse(appointment.getStartDateTime().replace(" ", "T"));
                LocalDateTime checkEndDateTime = LocalDateTime.parse(appointment.getEndDateTime().replace(" ", "T"));

                if (

                        (attemptedStartDateTime.isAfter(checkStartDateTime) && attemptedStartDateTime.isBefore(checkEndDateTime))
                                                                        ||
                        (attemptedEndDateTime.isAfter(checkStartDateTime) && attemptedEndDateTime.isBefore(checkEndDateTime))
                                                                        ||
                                            (attemptedStartDateTime.isEqual(checkStartDateTime))

                ) {

                    return true;

                }

            }

        }

        return false;

    }

}
