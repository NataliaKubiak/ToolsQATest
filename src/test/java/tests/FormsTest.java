package tests;

import io.qameta.allure.*;
import models.StartPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.testData.TestDataGenerator;

public class FormsTest extends BaseTest {

    @DataProvider(name = "randomDataFullForm")
    public Object[][] randomDataFullForm() {
        int numberOfDataSets = 5;
        Object[][] data = new Object[numberOfDataSets][5];

        for(int i = 0; i<numberOfDataSets; i++) {
            String firstName = TestDataGenerator.generateFirstName();
            String lastName = TestDataGenerator.generateLastName();
            String email = TestDataGenerator.generateEmail(firstName, lastName);
            String mobileNumber = TestDataGenerator.generateMobile();
            String address = TestDataGenerator.generateAddress();

            data[i][0] = firstName;
            data[i][1] = lastName;
            data[i][2] = email;
            data[i][3] = mobileNumber;
            data[i][4] = address;
        }
        return data;
    }
    @Test(dataProvider = "randomDataFullForm", groups = "smoke")
    @Description("This is e2e Form test")
    @Feature("Feature: Student Registration Form")
    @Story("Story number: 03.002")
    @Severity(SeverityLevel.CRITICAL)
    public void testFillInForm(String firstName, String lastName, String email, String mobile, String address) {
        boolean isConfirmationPopupDisplayed = new StartPage(getDriver())
                .clickFormsPageLink()
                .clickPracticeForm()
                .fillInFirstAndLastName(firstName, lastName)
                .fillInEmail(email)
                .chooseGenderRandom()
                .fillInMobile(mobile)
                .chooseDateOfBirthRandomClickOnPicker()
                .chooseHobbiesRandomly()
                .fillInAddress(address)
                .clickSubmitButton()
                .isConfirmationPopupAppear();

        Assert.assertTrue(isConfirmationPopupDisplayed);
    }

    @DataProvider(name = "randomDayMonthYearData")
    public Object[][] randomDayMonthYearData() {
        int numberOfDataSets = 5;
        Object[][] data = new Object[numberOfDataSets][3];

        for (int i = 0; i<numberOfDataSets; i++) {
            String month = TestDataGenerator.getRandomMonth();
            int year = TestDataGenerator.getRandomYear();
            String day = TestDataGenerator.getRandomDay(year, month);

            data[i][0] = day;
            data[i][1] = month;
            data[i][2] = year;
        }
        return data;
    }

    @Test(dataProvider = "randomDayMonthYearData", groups = "smoke")
    @Description("This test checks the Date Picker by choosing automatically generated random dates and clicks them")
    @Feature("Feature: Date Picker")
    @Story("Story number: 03.001")
    @Severity(SeverityLevel.BLOCKER)
    public void testDatePicker(String day, String month, int year) {
        String expectedDate = day + " " + month + " " + year;

        String actualDate = new StartPage(getDriver())
                .clickFormsPageLink()
                .clickPracticeForm()
                .chooseDateOfBirth(day, month, year)
                .getChosenDate();

        Assert.assertEquals(actualDate, expectedDate);
    }
}
