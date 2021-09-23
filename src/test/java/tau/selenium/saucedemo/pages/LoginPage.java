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

public class LoginPage extends BasePage {

    public static final String LOGIN_PAGE_URI = "/index.html";
    public static final String LOGIN_PAGE_URL = BASE_URL + LOGIN_PAGE_URI;

    public static final By USERNAME_INPUT = By.id("user-name");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By LOGIN_BUTTON = By.id("login-button");
    public static final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Login with {username} and {password}")
    public ProductsPage loginValid(String username, String password) {
        login(username, password);
        System.out.println("username " + username);
        System.out.println("password " + password);
        AllureUtils.takeScreenshot(driver);
        return new ProductsPage(driver);
    }

    @Step("Login with {username} and {password}")
    public LoginPage tryLogin(String username, String password) {
        login(username, password);
        System.out.println("username " + username);
        System.out.println("password " + password);
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    public String getLockedUserMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    @Step("Open login page")
    @Override
    public LoginPage openPage() {
        driver.get(LOGIN_PAGE_URL);
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        } catch (TimeoutException ex) {
            Assert.fail("Login page is not opened");
        } finally {
            return this;
        }
    }

    private void login(String username, String password) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }
}
