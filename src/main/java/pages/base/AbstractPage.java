package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/** @noinspection ALL*/
public abstract class AbstractPage {
  private final static int TIMEOUT = 5;

  private WebDriver driver;
  private WebDriverWait wait;

  public AbstractPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, TIMEOUT);
  }

  public WebDriver getDriver() {
    return driver;
  }

  public WebDriverWait getWait() {
    return wait;
  }
}
