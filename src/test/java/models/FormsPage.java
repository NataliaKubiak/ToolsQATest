package models;

import models.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormsPage extends BasePage {

    @FindBy(xpath = "//span[contains(text(), 'Practice Form')]")
    private WebElement practiceFormButton;

    public FormsPage(WebDriver driver) {
        super(driver);
    }

    public FormsPage clickPracticeForm() {
        practiceFormButton.click();
        return this;
    }
}
