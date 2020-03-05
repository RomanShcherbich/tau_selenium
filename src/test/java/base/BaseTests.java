package base;

import config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import pages.HomePage;
import pages.LoginPage;
import pages.SecuredPage;

public class BaseTests {

  protected static WebDriver driver;

//  @BeforeSuite
//  public void OpenChromeDriver()
//  {
//  }


  @BeforeTest
  public void beforeTest()
  {
    String webDriverProp = Config.WEB_CHROME_DRIVER;
    System.setProperty(webDriverProp, Config.getProperty(webDriverProp));
    System.out.println("Chrome - Set Up System Property");
    driver = new ChromeDriver();
    System.out.println("Open Chrome");
    driver.get("https://the-internet.herokuapp.com/");
    System.out.println("Open Test Application");
  }


  @BeforeClass
  public void beforeClass(){
//    sleepSec(1);
    /*
    driver.manage().window().maximize();
    sleepSec(1);
    List<WebElement> links = driver.findElements(By.xpath("//li/a[@href]"));
    System.out.println(links.size());
    WebElement inputLink = driver.findElement(By.xpath("//li/a[@href='/inputs']"));
    inputLink.click();
    driver.navigate().back();
    WebElement shiftingLink = driver.findElement(By.xpath("//a[@href='/shifting_content']"));
    shiftingLink.click();
    WebElement exampleOneLink = driver.findElement(By.xpath("//a[@href='/shifting_content/menu']"));
    exampleOneLink.click();
    List<WebElement> menus = driver.findElements(By.xpath("//a[contains(@href,'shifting')]"));
    System.out.println(menus.size());
    */
  }

  @AfterClass
  public void afterClass()
  {
    System.out.println("Close Test Application");
  }

  @AfterTest
  public void afterTest()
  {
    System.out.println("Close Chrome");
    driver.close();
    System.out.println("Chrome - Clean Up All Cookies");
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
