package login;

import base.BaseTests;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.SecuredPage;

public class LoginTests extends BaseTests {

  protected static final Logger log = Logger.getLogger(LoginTests.class);

  private static LoginPage loginPage;
  protected static SecuredPage securedPage;
  private static SoftAssert softAssert = new SoftAssert();

  @BeforeClass
  public void beforeClass() {
    loginPage = new HomePage(driver).clickFormAuthentication();
    log.info("Open Form Authentication");
  }

  @Test
  @Parameters({"username", "password","alert"})
  public void testLogin(String username, String password, String expectedAlert) {
    loginPage.validateLoginPage();
    try {
      securedPage = loginPage.loginValid(username, password);
    } catch (Exception e) {
      softAssert.fail(e.getMessage());
    }
    log.info("Sign In");

    String actualAlert = securedPage.getAlertText();
    softAssert.assertEquals(actualAlert.substring(0, actualAlert.indexOf("!") + 1), expectedAlert
        , "Alert text is incorrect");
    softAssert.assertAll();

    log.info("Check success login");
  }
}
