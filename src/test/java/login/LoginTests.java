package login;

import base.BaseTests;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SecuredPage;

public class LoginTests extends BaseTests {

  protected static final Logger log = Logger.getLogger(LoginTests.class);

  private static LoginPage loginPage;
  protected static SecuredPage securedPage;

  @BeforeClass
  public void beforeClass() {
    loginPage = new HomePage(driver).clickFormAuthentication();
    log.info("Open Form Authentication");
  }

  @Test
  @Parameters({"casetype","username", "password","alert"})
  public void testLogin(String casetype,String username, String password, String expectedAlert) {
    loginPage.verifyLoginPage();

    try {
      loginPage.verifyLoginButtonText();
    } catch (WebDriverException e) {
      softAssert.fail(e.getMessage());
    }
    String actualAlert;

    if(casetype.equals("invalid data")) {
      actualAlert = loginPage.getAlertText();
    } else {
      securedPage = loginPage.loginValid(username, password);
      actualAlert = securedPage.getAlertText();
    }

    softAssert.assertEquals(expectedAlert, actualAlert.substring(0, actualAlert.indexOf("!") + 1)
        , "Alert text is incorrect");
    securedPage.validateSecurePage();
    log.info("Check success login");
  }
}
