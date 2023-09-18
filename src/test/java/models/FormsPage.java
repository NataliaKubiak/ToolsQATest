package models;

import models.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import tests.testData.Gender;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class FormsPage extends BasePage {

    @FindBy(xpath = "//span[contains(text(), 'Practice Form')]")
    private WebElement practiceFormButton;
    @FindBy(id = "firstName")
    private WebElement firstNameInput;
    @FindBy(id = "lastName")
    private WebElement lastNameInput;
    @FindBy(id = "userEmail")
    private WebElement emailInput;
    @FindBy(xpath = "//label[@for='gender-radio-1']")
    private WebElement maleRadiobutton;
    @FindBy(xpath = "//label[@for='gender-radio-2']")
    private WebElement femaleRadiobutton;
    @FindBy(xpath = "//label[@for='gender-radio-3']")
    private WebElement otherRadiobutton;
    @FindBy(id = "userNumber")
    private WebElement phoneNumberInput;
    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthInput;
    @FindBy(className = "react-datepicker__month-select")
    private WebElement monthMenu;
    @FindBy(className = "react-datepicker__year-select")
    private WebElement yearMenu;
    @FindBy(id = "currentAddress")
    private WebElement currentAddressInput;
    @FindBy(id = "submit")
    private WebElement submitButton;

    public FormsPage(WebDriver driver) {
        super(driver);
    }

    public FormsPage clickPracticeForm() {
        practiceFormButton.click();
        return this;
    }
    public FormsPage fillInFirstAndLastName(String firstName, String lastName) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);

        return this;
    }

    public FormsPage fillInEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public FormsPage chooseGender(Gender gender) {
        int number;

        switch (gender) {
            case MALE -> number = 1;
            case FEMALE -> number = 2;
            case OTHER -> number = 3;
            default -> number = -1;
        }

        getDriver().findElement(By.xpath("//label[@for='gender-radio-" + number + "']")).click();
        return this;
    }

    public FormsPage chooseGenderRandom() {
        Random randomNumber = new Random();
        int genderNumber = randomNumber.nextInt(1, 4);

        getDriver().findElement(By.xpath("//label[@for='gender-radio-" + genderNumber + "']")).click();
        return this;
    }

    //date picker - variant 1
    public FormsPage chooseDateOfBirthRandomClickOnPicker() {
        dateOfBirthInput.click();

        Random randomNumber = new Random();
        String month = randomNumber.nextInt(13) + "";
        String year = randomNumber.nextInt(1900, 2023) + "";

        Select selectMonthMenu = new Select(monthMenu);
        selectMonthMenu.selectByValue(month);

        Select selectYearMenu = new Select(yearMenu);
        selectYearMenu.selectByValue(year);

        List<WebElement> weeksShowedInCalendar = getDriver().findElements(By.className("react-datepicker__week"));
        int amountOfWeeks = weeksShowedInCalendar.size();
        int randomWeekIndex = randomNumber.nextInt(1, amountOfWeeks + 1);
        int randomDayIndex = randomNumber.nextInt(1,8);

        getDriver().findElement(By
                .xpath("//div[@class='react-datepicker__week'][" + randomWeekIndex +
                        "]/descendant::div[" + randomDayIndex + "]")).click();

        return this;
    }

    //date picker - variant 2
    public FormsPage chooseDateOfBirthRandomInChosenMonth() {
        dateOfBirthInput.click();

        Random randomNumber = new Random();
        int month = randomNumber.nextInt(13);
        int year = randomNumber.nextInt(1900, 2024);

        Select selectMonthMenu = new Select(monthMenu);
        selectMonthMenu.selectByValue(month+ "");

        Select selectYearMenu = new Select(yearMenu);
        selectYearMenu.selectByValue(year + "");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);

        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        int randomPickedDay = randomNumber.nextInt(1, daysInMonth + 1);
        String daysInMontIndex;

        if (randomPickedDay < 10) {
            daysInMontIndex = "00" + randomPickedDay;
        } else {
            daysInMontIndex = "0" + randomPickedDay;
        }

        getDriver().findElement(By
                .xpath("//div[@class='react-datepicker__day react-datepicker__day--" +
                        daysInMontIndex + "']")).click();

        return this;
    }
}
