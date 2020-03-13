package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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

  private String getElementDescription(WebElement webElement) {
    String description = "";
    String id = webElement.getAttribute("id");

    if (id.isEmpty() || id == null) {
      description = "tag: " + webElement.getTagName();
    } else {
      description = "id: " + id;
    }
    return description;
  }

  protected String putText(String text, By textField) {
    waitElementIsVisible(textField);
    WebElement webElement = getDriver().findElement(textField);
    webElement.sendKeys(text);

    return "Element with "+ getElementDescription(webElement) + " -> send keys: " + text;
  }

  protected String buttonClick(By by) {
    waitElementIsVisible(by);
    WebElement button = getDriver().findElement(by);
    String buttonDescription = getElementDescription(button);
    button.click();
    return "Element with "+ buttonDescription + " -> click";
  }

  protected void verifyElementText(String text, By locator) throws WebDriverException {
    waitElementIsVisible(locator);
    String textElement = getDriver().findElement(locator).getText();
    if (!textElement.equals(text)) {
      String message = "invalid text of element.\nExpected:" + text
          + "\nActual:" + textElement;
      log.error(message);
      throw new WebDriverException(message);
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
    return getDriver().findElement(locator).getText();
  }
}
