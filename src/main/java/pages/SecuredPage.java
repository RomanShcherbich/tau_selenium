package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class SecuredPage extends BasePage {

  private By alert = new By.ById("flash");

  private By buttonLogout = new By.ByXPath("//a[contains(@class,'button')]/i");
  private String buttonLogoutText = "Logout";

  public SecuredPage(WebDriver driver) {
    super(driver);
  }

  public void validateSecurePage() {
    verifyElement(buttonLogout);
  }

  public String getAlertText() {
    return getElementText(alert);
  }

  public LoginPage logout() throws WebDriverException {
    verifyElementText(buttonLogoutText,buttonLogout);
    buttonClick(buttonLogout);
    return new LoginPage(getDriver());
  }
}
