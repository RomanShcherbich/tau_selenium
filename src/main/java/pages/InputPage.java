package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.apache.log4j.Logger;
import pages.base.BasePage;

public class InputPage extends BasePage {
  public InputPage(WebDriver driver) {
    super(driver);
  }
  private final static Logger log = Logger.getLogger(InputPage.class);

  private By numbersField = By.cssSelector(".example input");
  private By numbersFieldText = By.cssSelector(".example p");

  public void validateInputPage() {
    verifyElement(numbersField);
  }

  public String getNumberFieldValue() {
    return getDriver().findElement(numbersField).getAttribute("value");
  }

  public void sendValueToNumberField(Integer value) {
    log.info(sendText("Number", value.toString(), numbersField));
  }

  public void upNumberField(int x) {
    for (int j = 0; j < x; j++) {
      log.info(sendFromKeyboard("Number", Keys.ARROW_UP, numbersField));
    }
  }

  public void downNumberField(int x) {
    for (int j = 0; j < x; j++) {
      log.info(sendFromKeyboard("Number", Keys.ARROW_DOWN, numbersField));
    }
  }



//    sleepSec(1);
    /*
    driver.manage().window().maximize();
    sleepSec(1);
    List<WebElement> links = driver.findElements(By.xpath("//li/a[@href]"));
    log.info(links.size());
    WebElement inputLink = driver.findElement(By.xpath("//li/a[@href='/inputs']"));
    inputLink.click();
    driver.navigate().back();
    WebElement shiftingLink = driver.findElement(By.xpath("//a[@href='/shifting_content']"));
    shiftingLink.click();
    WebElement exampleOneLink = driver.findElement(By.xpath("//a[@href='/shifting_content/menu']"));
    exampleOneLink.click();
    List<WebElement> menus = driver.findElements(By.xpath("//a[contains(@href,'shifting')]"));
    log.info(menus.size());
    */
}
