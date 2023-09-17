package models;

import models.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage {

    @FindBy(xpath = "//h5[contains(text(), 'Forms')]")
    private WebElement formsButton;

    public StartPage(WebDriver driver) {
        super(driver);
    }

    public FormsPage clickFormsPageLink() {
        formsButton.click();
        return new FormsPage(getDriver());
    }
}
