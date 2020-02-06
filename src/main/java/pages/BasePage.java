package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import validation.WebDriverLog;

public class BasePage {
  WebDriver driver;
  public WebDriverLog log;

  public BasePage() {
    if(log==null) {

    }
  }

  protected void putText(String text, By textField) {
    driver.findElement(textField).sendKeys(text);
  }

  protected void buttonClick(String buttonText, By button) {
    verifyText(buttonText, button);
    driver.findElement(button).click();
  }

  protected void verifyText(String text, By locator) {
    String textElement = driver.findElement(locator).getText();
    if (!textElement.equals(text)) {
      System.out.println("ERROR invalid text of element.\nExpected:" + text
          + "\nActual:" + textElement);
    }
  }

  protected void verifyElement(By locator) {
    WebElement textElement = driver.findElement(locator);
    if (!textElement.isDisplayed()) {
      System.out.println("ERROR there is no element by locator:" + locator.toString());
    }
  }

  protected String getElementText(By locator) {
    return driver.findElement(locator).getText();
  }
}
