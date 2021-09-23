package tau.selenium.saucedemo.pages;

import tau.selenium.saucedemo.pages.base.BasePage;
import tau.selenium.saucedemo.utils.AllureUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static tau.selenium.saucedemo.constants.SauceLab.BASE_URL;

public class FinishPage extends BasePage {

    public static final String FINISH_PAGE_URI = "/checkout-complete.html";
    public static final String FINISH_PAGE_URL = BASE_URL + FINISH_PAGE_URI;

    public static final By FINISH_PAGE_LOCATOR = By.id("checkout_complete_container");
    public static final By FINISH_LOCATOR = By.xpath("//*[text()='Finish']");

    public FinishPage(WebDriver driver) {
        super(driver);
    }

    public String getText() {
        return driver.findElement(FINISH_PAGE_LOCATOR).getText();
    }

    @Step("Open finish page")
    @Override
    public FinishPage openPage() {
        driver.get(FINISH_PAGE_URL);
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Override
    public FinishPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(FINISH_LOCATOR));
        } catch (TimeoutException ex) {
            Assert.fail("Finish page is not opened");
        } finally {
            return this;
        }
    }
}
