package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class SecuredPage extends BasePage {

  private final static Logger log =  Logger.getLogger(SecuredPage.class);

  private By buttonLogout = new By.ByXPath("//a[contains(@class,'button')]/i");
  public String buttonLogoutText = "Logout";

  private By alert = new By.ById("flash");


  public SecuredPage(WebDriver driver) {
    super(driver);
  }

  public void validateSecurePage() {
    verifyElement(buttonLogout);
  }

  public String getAlertText() {
    return getElementText(alert);
  }

  public String getLogoutButtonText() {
    return getElementText(buttonLogout);
  }

  public LoginPage logout() {
    log.info(buttonClick(buttonLogoutText, buttonLogout));
    return new LoginPage(getDriver());
  }
}
