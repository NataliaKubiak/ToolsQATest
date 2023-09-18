package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import models.StartPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.testData.TestDataGenerator;

public class FormsTest extends BaseTest {

    private Faker faker = new Faker();

    @Test
//    public void testFillInForm() throws InterruptedException {
//        String firstName = faker.name().firstName();
//        String lastName = faker.name().lastName();
//        String email = faker.internet().emailAddress(firstName + lastName);
//
//        new StartPage(getDriver())
//                .clickFormsPageLink()
//                .clickPracticeForm()
//                .fillInFirstAndLastName(firstName, lastName)
//                .fillInEmail(email)
//                .chooseGenderRandom();
////                .chooseDateOfBirthRandomInChosenMonth();
////TODO finish writting e2e test for entire form
//        Thread.sleep(2000);
//    }

    @DataProvider(name = "randomDayMonthYearData")
    public Object[][] randomDayMonthYearData() {
        int numberOfDataSets = 5;
        Object[][] data = new Object[numberOfDataSets][3];

        for (int i = 0; i<numberOfDataSets; i++) {
            String month = TestDataGenerator.getRandomMonth();
            int year = TestDataGenerator.getRandomYear();
            String day = TestDataGenerator.getRandomDay(year, month);

            data[i][0] = month;
            data[i][1] = year;
            data[i][2] = day;
        }
        return data;
    }
    @Test(dataProvider = "randomDayMonthYearData")
    @Description("This test checks the Date Picker by choosing automatically generated random dates and clicks them")
    @Feature("Feature: Student Registration Form")
    @Story("Story number: 03.001")
    @Severity(SeverityLevel.CRITICAL)
    public void testDatePicker(String month1, int year1, String day1) {
        String expectedDate = day1 + " " + month1 + " " + year1;

        String actualDate = new StartPage(getDriver())
                .clickFormsPageLink()
                .clickPracticeForm()
                .chooseDateOfBirth(day1, month1, year1)
                .getChosenDate();

        Assert.assertEquals(actualDate, expectedDate);
    }
}
