package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecuredPage extends BasePage {

  private By alert = new By.ById("flash");

  private By buttonLogout = new By.ByXPath("//a[contains(@class,'button')]/i");
  private String buttonLogoutText = "Logout";

  public SecuredPage(WebDriver driver) {
    super.driver = driver;
  }

  public void validateSecurePage(String alertSecureText) {
    verifyText(alertSecureText,alert);
    verifyElement(buttonLogout);
  }

  public String getAlertText() {
    return getElementText(alert);
  }

  public LoginPage logout() {
    buttonClick(buttonLogoutText,buttonLogout);
    return new LoginPage(driver);
  }
}
