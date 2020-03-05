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

  public SecuredPage loginValid(String username, String password) {
    putText(username,usernameField);
    putText(password,passwordField);
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
