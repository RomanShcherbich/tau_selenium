package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class BasePage extends AbstractPage{

  private static final Logger log = Logger.getLogger(BasePage.class);

  public BasePage(WebDriver driver) {
    super(driver);
  }

  private void waitElementIsVisible(By by) {
    getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
  }

  protected void putText(String text, By textField) {
    waitElementIsVisible(textField);
    getDriver().findElement(textField).sendKeys(text);
  }

  protected void buttonClick(By button) {
    waitElementIsVisible(button);
    getDriver().findElement(button).click();
  }

  protected void verifyElementText(String text, By locator) throws Exception {
    String textElement = getDriver().findElement(locator).getText();
    if (!textElement.equals(text)) {
      String message = "invalid text of element.\nExpected:" + text
          + "\nActual:" + textElement;
      log.error(message);
      throw new Exception(message);
    }
  }

  protected void verifyElement(By locator) {
    WebElement textElement = getDriver().findElement(locator);
    if (!textElement.isDisplayed()) {
      String message = "there is no element by locator:" + locator.toString();
      log.error(message);
      throw new WebDriverException(message);
    }
  }

  protected String getElementText(By locator) {
    return getDriver().findElement(locator).getText();
  }
}
