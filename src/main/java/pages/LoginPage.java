package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

  public LoginPage(WebDriver driver) {
    super.driver = driver;
  }

  private By usernameField = By.xpath("//input[contains(@name,'username')]");
  private By passwordField = By.xpath("//input[contains(@name,'password')]");

  private By loginButton = By.xpath("//button[contains(@class,'radius')]/i");
  private String loginButtonText = "Login";

  private String usernameValid = "tomsmith";
  private String passwordValid = "SuperSecretPassword!";

  public SecuredPage loginValid() {
    putText(usernameValid,usernameField);
    putText(passwordValid,passwordField);
    buttonClick(loginButtonText, loginButton);
    return new SecuredPage(driver);
  }

  public String getLoginButtonText() {
    return getElementText(loginButton);
  }

  public void validateLoginPage() {
    verifyElement(usernameField);
  }
}
