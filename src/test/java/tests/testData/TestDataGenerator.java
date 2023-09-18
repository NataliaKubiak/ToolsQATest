package tests.testData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {

//    public static int getRandomMonth() {
//        Random randomNumber = new Random();
//        int month = randomNumber.nextInt(13);
//        return month;
//    }

    public static String getRandomMonth() {
        String[] monthName = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        Random randomNumber = new Random();
        int monthIndex = randomNumber.nextInt(12);

        return monthName[monthIndex];
    }

    public static int getRandomYear() {
        Random randomNumber = new Random();
        int year = randomNumber.nextInt(1900, 2024);
        return year;
    }

    public static String getRandomDay(int year, String month) {
        List<String> mounthList = new ArrayList<>(List.of("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
        int monthIndex = mounthList.indexOf(month);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthIndex);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        Random randomNumber = new Random();
        int randomPickedDay = randomNumber.nextInt(1, daysInMonth + 1);

        String daysInMontIndex;
        if (randomPickedDay < 10) {
            daysInMontIndex = "0" + randomPickedDay;
        } else {
            daysInMontIndex = "" + randomPickedDay;
        }
        return daysInMontIndex;
    }

}
