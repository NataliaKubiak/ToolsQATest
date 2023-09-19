package tests.testData;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {

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
        List<String> mounthList = new ArrayList<>(List.of("Jan", "Feb", "Mar",
                "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
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

    public static String generateFirstName() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    public static String generateLastName() {
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    public static String generateEmail(String firstName, String lastName) {
        Faker faker = new Faker();
        return faker.internet().emailAddress(firstName + lastName);
    }

    public static String generateMobile() {
        Faker faker = new Faker();
        return faker.phoneNumber().subscriberNumber(10);
    }

    public static String generateAddress() {
        Faker faker = new Faker();
        return faker.address().fullAddress();
    }
}
