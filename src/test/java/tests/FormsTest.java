package tests;

import com.github.javafaker.Faker;
import models.StartPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.testData.TestDataGenerator;

public class FormsTest extends BaseTest {

    private Faker faker = new Faker();

    @Test
    public void testFillInForm() throws InterruptedException {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress(firstName + lastName);

        new StartPage(getDriver())
                .clickFormsPageLink()
                .clickPracticeForm()
                .fillInFirstAndLastName(firstName, lastName)
                .fillInEmail(email)
                .chooseGenderRandom();
//                .chooseDateOfBirthRandomInChosenMonth();
//TODO finish writting e2e test for entire form
        Thread.sleep(2000);
    }

    @Test(invocationCount = 5)
    public void testDatePicker() {
        String month = TestDataGenerator.getRandomMonth();
        int year = TestDataGenerator.getRandomYear();
        String day = TestDataGenerator.getRandomDay(year, month);
        String expectedDate = day + " " + month + " " + year;

        String actualDate = new StartPage(getDriver())
                .clickFormsPageLink()
                .clickPracticeForm()
                .chooseDateOfBirth(day, month, year)
                .getChosenDate();

        Assert.assertEquals(actualDate, expectedDate);
    }
}
