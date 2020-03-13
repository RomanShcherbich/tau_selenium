package base;

import config.Config;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import pages.BasePage;

public class BaseTests {

  protected static final Logger log = Logger.getLogger(BaseTests.class);

  protected static WebDriver driver;

  @BeforeTest
  public void beforeTest()
  {
    String webDriverProp = Config.WEB_CHROME_DRIVER;
    System.setProperty(webDriverProp, Config.getProperty(webDriverProp));
    log.info("Chrome - Set Up System Property");
    driver = new ChromeDriver();
    log.info("Open Chrome");
    driver.manage().window().maximize();
    driver.get("https://the-internet.herokuapp.com/");
    log.info("Open Test Application");
  }


  @BeforeClass
  public void beforeClass(){
//    sleepSec(1);
    /*
    driver.manage().window().maximize();
    sleepSec(1);
    List<WebElement> links = driver.findElements(By.xpath("//li/a[@href]"));
    log.info(links.size());
    WebElement inputLink = driver.findElement(By.xpath("//li/a[@href='/inputs']"));
    inputLink.click();
    driver.navigate().back();
    WebElement shiftingLink = driver.findElement(By.xpath("//a[@href='/shifting_content']"));
    shiftingLink.click();
    WebElement exampleOneLink = driver.findElement(By.xpath("//a[@href='/shifting_content/menu']"));
    exampleOneLink.click();
    List<WebElement> menus = driver.findElements(By.xpath("//a[contains(@href,'shifting')]"));
    log.info(menus.size());
    */
  }

  @AfterClass
  public void afterClass()
  {
    log.info("Close Test Application");
  }

  @AfterTest
  public void afterTest()
  {
    log.info("Close Chrome");
    driver.close();
    log.info("Chrome - Clean Up All Cookies");
    driver.quit();
  }

//  @AfterSuite
//  public void afterSuite() {
//  }


  private void sleepSec(int seconds) {
    try {
      Thread.sleep(seconds*1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
