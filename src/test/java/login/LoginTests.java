package login;

import base.BaseTests;
import org.testng.annotations.BeforeClass;
import pages.HomePage;
import pages.LoginPage;
import pages.SecuredPage;

import static org.testng.Assert.assertEquals;

public class LoginTests extends BaseTests {


  public static void main(String[] args) {
    LoginTests test = new LoginTests();

    test.setUp();
    test.testSuccessfulLogin();
    driver.quit();

//    test.setUp();
//    test.testSuccessfulLogout();
//    driver.quit();
  }

  public void testSuccessfulLogin() {
    LoginPage loginPage = new HomePage(driver).clickFormAuthentication();
    loginPage.validateLoginPage();
    SecuredPage securedPage = loginPage.loginValid();
//    securedPage.validateSecurePage();
    assertEquals(securedPage.getAlertText(), "You logged into a secure area!\n×", "Alert text is incorrect");
  }

  public void testSuccessfulLogout() {
    LoginPage loginPage = new HomePage(driver).clickFormAuthentication();
    loginPage.validateLoginPage();

    SecuredPage securedPage = loginPage.loginValid();

    securedPage.validateSecurePage();
    loginPage = securedPage.logout();
    loginPage.validateLoginPage();
  }
}
