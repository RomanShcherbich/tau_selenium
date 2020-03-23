package login;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import base.BaseTests;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.SecuredPage;

public class LogoutTests extends BaseTests {

  protected static final Logger log = Logger.getLogger(LogoutTests.class);
  private static SecuredPage securedPage;
  private static SoftAssert softAssert = new SoftAssert();

  @BeforeClass
  public void openApplicationUrl() {
    securedPage = new SecuredPage(driver);
  }

  @Test(dependsOnMethods = "login.LoginTests.testLogin", priority = 3)
  public void checkLogoutButton() {
    assertEquals(securedPage.getLogoutButtonText(),securedPage.buttonLogoutText);
  }

  @Test(dependsOnMethods = "login.LoginTests.testLogin", priority = 4)
  @Parameters({"alert"})
  public void testLogout() {
    LoginPage loginPage = securedPage.logout();
    loginPage.validateLoginPage();
    log.info("Check success logout");
  }
}
