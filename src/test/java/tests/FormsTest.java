package tests;

import com.github.javafaker.Faker;
import models.StartPage;
import org.testng.annotations.Test;
import tests.base.BaseTest;

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
                .chooseGenderRandom()
                .chooseDateOfBirthRandom();
    }
}
