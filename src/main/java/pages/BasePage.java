package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class BasePage extends AbstractPage {

  private static final Logger log = Logger.getLogger(BasePage.class);

  public BasePage(WebDriver driver) {
    super(driver);
  }

  private void waitElementIsVisible(By by) {
    getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
  }

  protected String sendText(String elementName, String text, By textField) {
    waitElementIsVisible(textField);
    WebElement webElement = getDriver().findElement(textField);
    webElement.sendKeys(text);

    return "Element "+ elementName + " input field -> send keys: " + text;
  }

  protected String sendFromKeyboard(String elementName, Keys keys, By textField) {
    waitElementIsVisible(textField);
    WebElement webElement = getDriver().findElement(textField);
    webElement.sendKeys(keys);

    return "Element "+ elementName + " input field -> send keys: " + keys.toString();
  }

  protected String buttonClick(String buttonName, By by) {
    waitElementIsVisible(by);
    WebElement button = getDriver().findElement(by);
    button.click();
    return "Button "+ buttonName + " -> click";
  }

  protected String linkClick(String linkName, By by) {
    waitElementIsVisible(by);
    WebElement link = getDriver().findElement(by);
    String href = link.getAttribute("href");
    link.click();
    return "Link "+ linkName + " -> click \nhref:" + href;
  }

  protected void verifyElementText(String text, By locator) throws Exception {
    waitElementIsVisible(locator);
    String textElement = getDriver().findElement(locator).getText();
    if (!textElement.equals(text)) {
      String message = "invalid text of element.\nExpected:" + text
          + "\nActual:" + textElement;
      throw new Exception(message);
    }
  }

  protected void verifyElement(By locator) {
    waitElementIsVisible(locator);
    WebElement textElement = getDriver().findElement(locator);
    if (!textElement.isDisplayed()) {
      String message = "there is no element by locator:" + locator.toString();
      log.error(message);
      throw new WebDriverException(message);
    }
  }

  protected String getElementText(By locator) {
    waitElementIsVisible(locator);
    return getDriver().findElement(locator).getText();
  }
}
