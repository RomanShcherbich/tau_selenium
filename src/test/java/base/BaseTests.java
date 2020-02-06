package base;

import config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.HomePage;
import pages.LoginPage;
import pages.SecuredPage;

public class BaseTests {

  protected static WebDriver driver;

  @BeforeClass
  public void setUp(){
    String webDriverProp = Config.WEB_CHROME_DRIVER;
    System.setProperty(webDriverProp, Config.getProperty(webDriverProp));
    driver = new ChromeDriver();
    driver.get("https://the-internet.herokuapp.com/");
    System.out.println(driver.getTitle());
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
  public void tearDown() {
    driver.quit();
  }

  private void sleepSec(int seconds) {
    try {
      Thread.sleep(seconds*1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
