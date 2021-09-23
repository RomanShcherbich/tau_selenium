package tau.selenium.saucedemo.pages;

import tau.selenium.saucedemo.pages.base.BasePage;
import tau.selenium.saucedemo.utils.AllureUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

import static tau.selenium.saucedemo.constants.SauceLab.BASE_URL;

public class CartPage extends BasePage {

    public static final String CART_PAGE_URI = "/cart.html";
    public static final String CART_PAGE_URL = BASE_URL + CART_PAGE_URI;

    public static final By CHECKOUT_BUTTON_LOCATOR = By.cssSelector(".checkout_button");
    public static final By CONTINUE_SHOPPING_LOCATOR = By.xpath("//*[contains(text(),'Continue Shopping')]");
    public static final By PRODUCT_IN_SHOPPING_CART_LOCATOR = By.cssSelector(".cart_item");

    public String pricePattern = "//*[contains(text(),'%s')]/ancestor::*[@class='cart_item']" +
            "//div[@class='inventory_item_price']";
    public String quantityPattern = "//*[contains(text(),'%s')]/ancestor::*[@class='cart_item']" +
            "//div[@class='cart_quantity']";
    public String removeButtonPattern = "//*[contains(text(), '%s')]/../following-sibling::*" +
            "//button[contains(@class, 'cart_button')]";
    public String productInCartPattern = "//*[contains(text(), '%s')]/" +
            "ancestor::*[@class='cart_item']";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductPrice(String productName) {
        return driver.findElement(By.xpath(String.format(pricePattern, productName))).getText();
    }

    public String getProductQuantity(String productName) {
        return driver.findElement(By.xpath(String.format(quantityPattern, productName))).getText();
    }

    @Step("Click on check out button")
    public CheckoutInformationPage clickOnCheckOutButton() {
        driver.findElement(CHECKOUT_BUTTON_LOCATOR).click();
        AllureUtils.takeScreenshot(driver);
        return new CheckoutInformationPage(driver);
    }

    @Step("Click on continue shopping button")
    public ProductsPage clickOnContinueShoppingButton() {
        driver.findElement(CONTINUE_SHOPPING_LOCATOR).click();
        AllureUtils.takeScreenshot(driver);
        return new ProductsPage(driver);
    }

    @Step("Click on remove button for {productName}")
    public CartPage clickOnRemoveButton(String productName) {
        driver.findElement(By.xpath(String.format(removeButtonPattern, productName))).click();
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    public List<WebElement> getProductsInShoppingCart() {
        return driver.findElements(PRODUCT_IN_SHOPPING_CART_LOCATOR);
    }

    @Step("Open cart page")
    @Override
    public CartPage openPage() {
        driver.get(CART_PAGE_URL);
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Override
    public CartPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BUTTON_LOCATOR));
        } catch (TimeoutException ex) {
            Assert.fail("Cart page is not opened");
        } finally {
            return this;
        }
    }
}
