package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import org.apache.log4j.Logger;
import pages.base.BasePage;

public class LoginPage extends BasePage {

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  private final static Logger log = Logger.getLogger(LoginPage.class);

  private By usernameField = By.xpath("//input[contains(@name,'username')]");
  private By passwordField = By.xpath("//input[contains(@name,'password')]");

  private By buttonLogin = By.xpath("//button[contains(@class,'radius')]/i");
  public String buttonLoginText = "Login";

  private By alert = new By.ById("flash");

  public SecuredPage loginValid(String username, String password) {
    login(username, password);
    return new SecuredPage(getDriver());
  }

  private void login(String username, String password) {
    sendTextLoginPage("username", username, usernameField);
    sendTextLoginPage("password", password, passwordField);
    log.info(buttonClick(buttonLoginText, buttonLogin));
  }

  public String getAlertText() {
    return getElementText(alert);
  }

  public void verifyLoginButtonText() throws WebDriverException {
    try {
      verifyElementText(buttonLoginText, buttonLogin);
    } catch (Exception e) {
      throw new WebDriverException("Login button: " + e.getMessage());
    }
  }

  public String getButtonLoginText() {
    return getElementText(buttonLogin);
  }

  public void validateLoginPage() {
    verifyElement(usernameField);
    log.info("It is login page. Validated by username field");
  }

  private void sendTextLoginPage(String username, String password, By by) {
    log.info(sendText(username, password, by));
  }
}
