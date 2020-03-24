package base;

import config.Config;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

public class BaseTests {

  private static final Logger log = Logger.getLogger(BaseTests.class);
  protected static SoftAssert softAssert = new SoftAssert();
  protected static WebDriver driver;

  @BeforeSuite
  public void beforeSuite() {
    String webDriverProp = Config.WEB_CHROME_DRIVER;
    System.setProperty(webDriverProp, Config.getProperty(webDriverProp));
    log.info("Chrome - Set Up System Property");
    driver = new ChromeDriver();
    log.info("Open Chrome");
//    driver.manage().window().maximize();
  }

  @BeforeTest
  public void beforeTest() {
    driver.get("https://the-internet.herokuapp.com/");
    log.info("Open Test Application");
//    driver.manage().window().maximize();
  }


  @BeforeClass
  public void openApplicationUrl(){
  }

  @AfterClass
  public void afterClass()
  {
  }

  @AfterTest
  public void afterTest() {
  }

  @AfterSuite
  public void afterSuite() {
    log.info("Close Test Application");
    log.info("Close Chrome");
    driver.close();
    log.info("Chrome - Clean Up All Cookies");
    driver.quit();
    softAssert.assertAll();
  }


  private void sleepSec(int seconds) {
    try {
      Thread.sleep(seconds*1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
