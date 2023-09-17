package tests;

import models.StartPage;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class FormsTest extends BaseTest {

    @Test
    public void testFillInForm() throws InterruptedException {
        new StartPage(getDriver())
                .clickFormsPageLink()
                .clickPracticeForm();

        Thread.sleep(2000);
    }
}
