package login;

import base.BaseTests;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SecuredPage;

import static org.testng.Assert.assertEquals;

public class LoginTests extends BaseTests {

  protected static final Logger log = Logger.getLogger(LoginTests.class);

  private static LoginPage loginPage;
  protected static SecuredPage securedPage;

  @BeforeClass
  public void beforeClass() {
    loginPage = new HomePage(driver).clickFormAuthentication();
    loginPage.validateLoginPage();
    log.info("Open Form Authentication");
  }

  @Test (priority = 1)
  public void checkLoginButton() {
    assertEquals(loginPage.getButtonLoginText(),loginPage.buttonLoginText);
  }

  @Test (priority = 2)
  @Parameters({"caseType","username", "password","alert"})
  public void testLogin(String caseType,String username, String password, String expectedAlert) {

    String actualAlert;

    if(caseType.equals("invalid data")) {
      loginPage.loginValid(username, password);
      actualAlert = loginPage.getAlertText();
    } else {
      securedPage = loginPage.loginValid(username, password);
      actualAlert = securedPage.getAlertText();
      securedPage.validateSecurePage();
      log.info("Check success login");
    }

    softAssert.assertEquals(expectedAlert, actualAlert.substring(0, actualAlert.indexOf("!") + 1)
        , "Alert text is incorrect");
  }
}
