package models;

import io.qameta.allure.Step;
import models.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import tests.testData.Gender;

import java.util.ArrayList;
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
    @FindBy(id = "hobbies-checkbox-1")
    private WebElement sportCheckbox;
    @FindBy(id = "hobbies-checkbox-2")
    private WebElement readingCheckbox;
    @FindBy(id = "hobbies-checkbox-3")
    private WebElement musicCheckbox;
    @FindBy(id = "currentAddress")
    private WebElement addressInput;
    @FindBy(id = "state")
    private WebElement selectStateDropdownMenu;
    @FindBy(id = "city")
    private WebElement selectCityDropdownMenu;
    @FindBy(id = "submit")
    private WebElement submitButton;
    @FindBy(className = "modal-content")
    private WebElement confirmationPopup;

    public FormsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Clicking Practice Form button")
    public FormsPage clickPracticeForm() {
        practiceFormButton.click();
        return this;
    }

    @Step("Clicking Submit button")
    public FormsPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    @Step("Checking if Pop-up appeared")
    public boolean isConfirmationPopupAppear() {
        return getWait5().until(ExpectedConditions.visibilityOf(confirmationPopup)).isDisplayed();
    }

    @Step("Filling in First Name field and Last Name field")
    public FormsPage fillInFirstAndLastName(String firstName, String lastName) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);

        return this;
    }

    @Step("Filling in Email field")
    public FormsPage fillInEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Choosing Gender")
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

    @Step("Choosing Gender")
    public FormsPage chooseGenderRandom() {
        Random randomNumber = new Random();
        int genderNumber = randomNumber.nextInt(1, 4);

        getDriver().findElement(By.xpath("//label[@for='gender-radio-" + genderNumber + "']")).click();
        return this;
    }

    @Step("Filling in Mobile number")
    public FormsPage fillInMobile(String mobile) {
        phoneNumberInput.sendKeys(mobile);
        return this;
    }

    //date picker - variant 1
    @Step("Choosing Date Of Birth")
    public FormsPage chooseDateOfBirthRandomClickOnPicker() {
        dateOfBirthInput.click();

        Random randomNumber = new Random();
        String month = randomNumber.nextInt(12) + "";
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
    @Step("Choosing Date Of Birth")
    public FormsPage chooseDateOfBirth(String day, String month, int year) {
        dateOfBirthInput.click();

        List<String> mounthList = new ArrayList<>(List.of("Jan", "Feb", "Mar", "Apr",
                "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
        int monthIndex = mounthList.indexOf(month);

        Select selectMonthMenu = new Select(monthMenu);
        selectMonthMenu.selectByValue(monthIndex + "");

        Select selectYearMenu = new Select(yearMenu);
        selectYearMenu.selectByValue(year + "");

        getWait5().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//div[@class='react-datepicker__day react-datepicker__day--0" +
                        day + "' or @class='react-datepicker__day react-datepicker__day--0" +
                        day + " react-datepicker__day--weekend']" ))).click();
        return this;
    }

    @Step("Retrieve chosen date of birth from the field")
    public String getChosenDate() {
        return dateOfBirthInput.getAttribute("value");
    }

    @Step("Checking Hobby checkbox")
    public FormsPage chooseHobbiesRandomly() {
        Random randomNumber = new Random();
        int checkboxNumber = randomNumber.nextInt(1, 4);

        getDriver().findElement(By.xpath("//label[@for='hobbies-checkbox-" +
                checkboxNumber + "']")).click();

        return this;
    }

    @Step("Filling in Address")
    public FormsPage fillInAddress(String address) {
        addressInput.sendKeys(address);
        return this;
    }

    //TODO rewrite or delete
//    @Step("Choosing State and City from dropdown menu")
//    public FormsPage chooseStateAndCity() {
//        List<String> statesList = new ArrayList<>(List.of("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"));
//        List<String> ncrCitiesList = new ArrayList<>(List.of("Delhi", "Gurgaon", "Noida"));
//        List<String> uttarPradeshCitiesList = new ArrayList<>(List.of("Agra", "Lucknow"));
//        List<String> haryanaCitiesList = new ArrayList<>(List.of("Karnal", "Panipat"));
//        List<String> rajasthanCitiesList = new ArrayList<>(List.of("Jaipur", "Jaiselmer"));
//
//        Random randomNumber = new Random();
//        String state = statesList.get(randomNumber.nextInt(4));
//        String city = "";
//
//        switch (state) {
//            case "NCR" -> city = ncrCitiesList.get(randomNumber.nextInt(3));
//            case "Uttar Pradesh" -> city = uttarPradeshCitiesList.get(randomNumber.nextInt(2));
//            case "Haryana" -> city = haryanaCitiesList.get(randomNumber.nextInt(2));
//            case "Rajasthan" -> city = rajasthanCitiesList.get(randomNumber.nextInt(2));
//        }
//
////        clickByJSExecutor(this, selectStateDropdownMenu);
//
//        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
//        executor.executeScript("arguments[0].value = '" + state + "'", selectStateDropdownMenu);
////        getWait5().until(ExpectedConditions.elementToBeClickable(selectStateDropdownMenu)).sendKeys(state, Keys.ENTER);
//        getWait5().until(ExpectedConditions.elementToBeClickable(selectCityDropdownMenu)).sendKeys(city, Keys.ENTER);
//
//        return this;
//    }
}
