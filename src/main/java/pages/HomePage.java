package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

  public HomePage(WebDriver driver) {
    super(driver);
  }

  private static Logger log = Logger.getLogger(HomePage.class);

  private By formAuthenticationLink = By.xpath("//a[contains(@href,'/login')]");
  private By inputLink = By.xpath("//li/a[@href='/inputs']");
  private By dropdownLink = By.xpath("//li/a[@href='/dropdown']");

  public LoginPage clickFormAuthentication(){
    clickHomePage("Form Authentication",formAuthenticationLink);
    return new LoginPage(getDriver());
  }

  public InputPage clickInput(){
    clickHomePage("Inputs", inputLink);
    return new InputPage(getDriver());
  }

  public DropdownPage clickDropdown(){
    clickHomePage("Dropdown", dropdownLink);
    return new DropdownPage(getDriver());
  }

  private void clickHomePage(String name, By by) {
    log.info(linkClick(name, by));
  }
}
