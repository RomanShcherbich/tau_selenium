package tau.selenium.saucedemo.tests;

import tau.selenium.saucedemo.matchers.CartMatcher;
import tau.selenium.saucedemo.matchers.FinishMatcher;
import tau.selenium.saucedemo.matchers.LoginMatcher;
import tau.selenium.saucedemo.matchers.ProductsMatcher;
import tau.selenium.saucedemo.pages.CartPage;
import tau.selenium.saucedemo.pages.LoginPage;
import tau.selenium.saucedemo.pages.ProductsPage;
import tau.selenium.saucedemo.tests.listeners.TestListener;
import tau.selenium.saucedemo.utils.CapabilitiesGenerator;
import tau.selenium.saucedemo.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;

import static tau.selenium.saucedemo.constants.SauceLab.SAUCE_LAB_PASS_PROPERTY;
import static tau.selenium.saucedemo.constants.SauceLab.SAUCE_LAB_USER_PROPERTY;

@Listeners(TestListener.class)
public class BaseTest {

    protected WebDriver driver;

    protected LoginPage loginPage;
    protected CartPage cartPage;
    protected CartMatcher cartAssert;
    protected ProductsMatcher productsAssert;
    protected LoginMatcher loginAssert;
    protected FinishMatcher finishAssert;

    @BeforeMethod
    public void setUp(ITestContext context) {
        if (System.getProperty("browser", "chrome").equals("chrome")) {
            driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        setDriverAttribute(context);
        initTestObjects();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public ProductsPage loginValid() {
        return loginPage
                .openPage()
                .isPageOpened()
                .loginValid(getEnvOrReadProperty(SAUCE_LAB_USER_PROPERTY),
                        getEnvOrReadProperty(SAUCE_LAB_PASS_PROPERTY))
                .isPageOpened();
    }

    protected String getEnvOrReadProperty(String key) {
        return System.getenv().getOrDefault(key, PropertyReader.getProperty(key));
    }

    private void initTestObjects() {
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        cartAssert = new CartMatcher();
        productsAssert = new ProductsMatcher();
        loginAssert = new LoginMatcher();
        finishAssert = new FinishMatcher();
    }

    private void setDriverAttribute(ITestContext context) {
        String variable = "driver";
        System.out.println("Setting driver into context with variable name " + variable);
        context.setAttribute(variable, driver);
    }
}
