package login;

import base.BaseTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.SecuredPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTests {

  private static LoginPage loginPage;
  private static SecuredPage securedPage;
  private static SoftAssert softAssert = new SoftAssert();

  @BeforeClass
  public void beforeClass() {
    super.beforeClass();
    loginPage = new HomePage(driver).clickFormAuthentication();
    System.out.println("Open Form Authentication");
  }

  @BeforeMethod
  public void beforeMethod() {
  }

  @Test
  @Parameters({"username", "password","alert"})
  public void testLogin(String username, String password, String expectedAlert) {
    loginPage.validateLoginPage();
    securedPage = loginPage.loginValid(username, password);
    System.out.println("Sign In");

    String actualAlert = securedPage.getAlertText();
//    securedPage.validateSecurePage();
    softAssert.assertEquals(actualAlert.substring(0, actualAlert.indexOf("!") + 1), expectedAlert
        , "Alert text is incorrect");
    softAssert.assertAll();

    System.out.println("Check success login");
  }

  @Test(dependsOnMethods = "testLogin")
  @Parameters({"alert"})
  public void testLogout(String alert) {
    if(alert.equals("You logged into a secure area!")) {
      securedPage.logout();
      System.out.println("Sign Out");
      assertEquals(loginPage.getLoginButtonText(), "Login", "Login Button Text text is incorrect");
      System.out.println("Check success logout");
    } else {
      System.out.println("Logout test skipped");
    }
  }

  @AfterMethod
  public void afterMethod() {
  }
}
