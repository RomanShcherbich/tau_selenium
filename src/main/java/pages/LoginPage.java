package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import org.apache.log4j.Logger;

public class LoginPage extends BasePage {
  private final static Logger log =  Logger.getLogger(LoginPage.class);

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  private By usernameField = By.xpath("//input[contains(@name,'username')]");
  private By passwordField = By.xpath("//input[contains(@name,'password')]");

  private By loginButton = By.xpath("//button[contains(@class,'radius')]/i");
  private String loginButtonText = "Login1";

  private By alert = new By.ById("flash");

  public SecuredPage loginValid(String username, String password) {
    login(username, password);
    return new SecuredPage(getDriver());
  }

  public void login(String username, String password) {
    log.info(putText(username, usernameField));
    log.info(putText(password, passwordField));
    log.info(buttonClick(loginButton));
  }

  public String getAlertText() {
    return getElementText(alert);
  }

  public void verifyLoginButtonText() throws WebDriverException {
    try {
      verifyElementText(loginButtonText, loginButton);
    } catch (WebDriverException e) {
      throw new WebDriverException("Login button: " + e.getMessage());
    }
  }

  public void verifyLoginPage() {
    verifyElement(usernameField);
  }
}
