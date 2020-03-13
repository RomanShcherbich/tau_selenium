package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{

  public HomePage(WebDriver driver) {
    super(driver);
  }

  private By formAuthenticationLink = By.xpath("//a[contains(@href,'/login')]");

  public LoginPage clickFormAuthentication(){
    WebElement authLink = getDriver().findElement(formAuthenticationLink);
    authLink.click();
    return new LoginPage(getDriver());
  }

  public InputPage clickInput(){
    WebElement inputLink = getDriver().findElement(By.xpath("//li/a[@href='/inputs']"));
    inputLink.click();
    return new InputPage(getDriver());
  }
}
