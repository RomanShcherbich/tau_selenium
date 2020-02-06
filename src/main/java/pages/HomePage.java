package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{

  public HomePage(WebDriver driver) {
    super.driver = driver;
  }

  private By formAuthenticationLink = By.xpath("//a[contains(@href,'/login')]");

  public LoginPage clickFormAuthentication(){
    WebElement authLink = driver.findElement(formAuthenticationLink);
    authLink.click();
    return new LoginPage(driver);
  }
}
