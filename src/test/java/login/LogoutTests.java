package login;

import base.BaseTests;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.SecuredPage;

import static org.testng.Assert.assertEquals;

public class LogoutTests extends BaseTests {

  protected static final Logger log = Logger.getLogger(LogoutTests.class);
  private static SecuredPage securedPage;
  private static SoftAssert softAssert = new SoftAssert();

  @BeforeClass
  public void beforeClass() {
    securedPage = new SecuredPage(driver);
  }

  @Test(dependsOnMethods = "login.LoginTests.testLogin")
  @Parameters({"alert"})
  public void testLogout() {
    try {
      LoginPage loginPage = securedPage.logout();
      softAssert.assertEquals(loginPage.getLoginButtonText(), "Login", "Login Button Text text is incorrect");
    } catch (Exception e) {
      softAssert.fail(e.getMessage());
    }
    softAssert.assertAll();
    log.info("Check success logout");

  }
}
