package pages.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class BasePage extends AbstractPage {

  private static final Logger log = Logger.getLogger(BasePage.class);

  public BasePage(WebDriver driver) throws WebDriverException{
    super(driver);
  }

  private void waitElementIsDisplayed(By by) {
    getWait().until(ExpectedConditions.visibilityOfElementLocated(by));

    WebElement element = getDriver().findElement(by);

    if (!element.isDisplayed()) {
      String message = "Element is not displayed";
      try {
        throw new Exception(message);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  protected String sendText(String elementName, String text, By textField) {
    waitElementIsDisplayed(textField);
    WebElement webElement = getDriver().findElement(textField);
    webElement.sendKeys(text);

    return "Element "+ elementName + " input field -> send keys: " + text;
  }

  protected String sendFromKeyboard(String elementName, Keys keys, By textField) {
    waitElementIsDisplayed(textField);
    WebElement webElement = getDriver().findElement(textField);
    webElement.sendKeys(keys);

    return "Element "+ elementName + " input field -> send keys: " + keys.toString();
  }

  protected String buttonClick(String buttonName, By by) {
    waitElementIsDisplayed(by);
    WebElement button = getDriver().findElement(by);
    button.click();
    return "Button "+ buttonName + " -> click";
  }

  protected String linkClick(String linkName, By by) {
    waitElementIsDisplayed(by);
    WebElement link = getDriver().findElement(by);
    String href = link.getAttribute("href");
    link.click();
    return "Link "+ linkName + " -> click \nhref:" + href;
  }

  protected void verifyElementText(String text, By locator) throws Exception {
    waitElementIsDisplayed(locator);
    String textElement = getDriver().findElement(locator).getText();
    if (!textElement.equals(text)) {
      String message = "invalid text of element.\nExpected:" + text
          + "\nActual:" + textElement;
      throw new Exception(message);
    }
  }

  protected void verifyElement(By locator) {
    waitElementIsDisplayed(locator);
    WebElement textElement = getDriver().findElement(locator);
    if (!textElement.isDisplayed()) {
      String message = "there is no element by locator:" + locator.toString();
      log.error(message);
      throw new WebDriverException(message);
    }
  }

  protected boolean isElementDisplayed(By locator) {
    WebElement textElement = getDriver().findElement(locator);
    return textElement.isDisplayed();
  }

  protected String getElementText(By locator) {
    waitElementIsDisplayed(locator);
    return getDriver().findElement(locator).getText();
  }
}
