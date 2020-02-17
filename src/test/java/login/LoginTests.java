package login;

import base.BaseTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SecuredPage;

import java.security.SecureRandom;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTests {

  private static LoginPage loginPage;
  private static SecuredPage securedPage;

  @BeforeClass
  public void beforeClass()
  {
    super.beforeClass();
    loginPage = new HomePage(driver).clickFormAuthentication();
    System.out.println("Open Form Authentication");
  }

  @BeforeMethod
  public void beforeMethod()
  {
  }

 @Test
  public void testSuccessfulLogin() {
   loginPage.validateLoginPage();
   securedPage = loginPage.loginValid();
   System.out.println("Sign In");
//    securedPage.validateSecurePage();
    assertTrue(securedPage.getAlertText().contains("You logged into a secure area!"), "Alert text is incorrect");
   System.out.println("Check success login");
  }

  @Test
  public void testSuccessfulLogout() {
    securedPage.logout();
    System.out.println("Sign Out");
    assertEquals(loginPage.getLoginButtonText(), "Login", "Login Button Text text is incorrect");
    System.out.println("Check success logout");
  }

  @AfterMethod
  public void afterMethod()
  {
  }
}
