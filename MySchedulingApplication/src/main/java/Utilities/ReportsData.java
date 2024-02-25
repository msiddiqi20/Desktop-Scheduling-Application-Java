package Utilities;

import Model.Appointments;
import Model.Reports;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**This is the abstract Reports Data class.*/
public abstract class ReportsData {

    private static ObservableList<String> monthsOfYear = FXCollections.observableArrayList();
    private static ObservableList<Reports> reportsByMonth = FXCollections.observableArrayList();
    private static ObservableList<Reports> reportsByType = FXCollections.observableArrayList();

    /**This is the Months of Year getter.
     * This method returns all the Months of the Year.
     * @return This returns all the months of the year in the form of an ObservableList (String)*/
    public static ObservableList<String> getMonthsOfYear(){
        return monthsOfYear;
    }

    /**This is the Reports By Month getter.
     * This method returns the Reports By the Month.
     * @return This returns all the Reports By the Month in the form of a  ObservableList (Reports).*/
    public static ObservableList<Reports> getReportsByMonth(){
        return reportsByMonth;
    }

    /**This is the Reports By Type getter.
     * This method returns the Reports By the Type.
     * @return This returns all the Reports By the Type in the form of an ObservableList (Reports).*/
    public static ObservableList<Reports> getReportsByType(){
        return reportsByType;
    }

    /**This is the Months of Year setter.
     * This method sets all the Months of the Year.*/
    public static void setMonthsOfYear(){

        monthsOfYear.add("January");
        monthsOfYear.add("February");
        monthsOfYear.add("March");
        monthsOfYear.add("April");
        monthsOfYear.add("May");
        monthsOfYear.add("June");
        monthsOfYear.add("July");
        monthsOfYear.add("August");
        monthsOfYear.add("September");
        monthsOfYear.add("October");
        monthsOfYear.add("November");
        monthsOfYear.add("December");

    }

    /**This is the All Reports setter.
     * This method sets all the reports data.
     * This method uses a lambda expression. For each month in the given year, the appointments are sorted.
     * Taking each list of appointments in each month, a Type Hash Map is created calculating how many occurrences of each Type occurs in that specific month.
     * After creating the Type Hash Map, a Report object is created with the month, type of appointment, and the total number of appointments of that type in that month.
     * The lambda expression is used with the combination of the Hash Map ForEach method. This allows to pass the key-value pair into the reports.add method.*/
    public static void setAllReports(){

        ArrayList<ArrayList<Appointments>> firstSorted = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {

            firstSorted.add(new ArrayList<Appointments>());

        }

        for (Appointments appointment : AppointmentsData.getAllAppointments()) {

            String[] splitDateTime = appointment.getStartDateTime().split(" ");
            LocalDate date = LocalDate.parse(splitDateTime[0]);

            if(date.getYear() == 2023){

                int month = date.getMonthValue() - 1;
                firstSorted.get(month).add(appointment);

            }

        }

        for (int i = 0; i <= 11; i++) {

            String month = monthsOfYear.get(i);
            int totalAppointmentsThisMonth = firstSorted.get(i).size();

            reportsByMonth.add(new Reports(2023, month, totalAppointmentsThisMonth));

        }

        for (int i = 0; i <= 11; i++) {

            HashMap<String, Integer> typeHM = new HashMap<>();

            for (Appointments appointment : firstSorted.get(i)) {

                String typeCheck = appointment.getType().toLowerCase();

                if(!typeHM.containsKey(typeCheck)){

                    typeHM.put(typeCheck, 1);

                } else {

                    typeHM.put(typeCheck, typeHM.get(typeCheck) + 1);

                }

            }

            String month = monthsOfYear.get(i);

            typeHM.forEach((Key, Value) -> {

                reportsByType.add(new Reports(month, Key, Value));

            });

        }

    }

}
