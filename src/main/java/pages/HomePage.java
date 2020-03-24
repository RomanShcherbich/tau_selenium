package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HoversPage.HoversPage;
import pages.base.BasePage;

public class HomePage extends BasePage {

  public HomePage(WebDriver driver) {
    super(driver);
  }

  private static Logger log = Logger.getLogger(HomePage.class);

  public LoginPage clickFormAuthentication(){
    clickHomePage("Form Authentication", byLinkValue("login"));
    return new LoginPage(getDriver());
  }

  public HoversPage clickHovers(){
    String pageName = "Hovers";
    clickHomePage(pageName, byLinkValue(pageName.toLowerCase()));
    return new HoversPage(getDriver());
  }

  public InputPage clickInput(){
    String pageName = "Inputs";
    clickHomePage(pageName, byLinkValue(pageName.toLowerCase()));
    return new InputPage(getDriver());
  }

  public DropdownPage clickDropdown(){
    String pageName = "Dropdown";
    clickHomePage(pageName, byLinkValue(pageName.toLowerCase()));
    return new DropdownPage(getDriver());
  }

  private By byLinkValue(String href) {
    String xpathLocator = String.format("//a[@href='/%s']", href);
    return By.xpath(xpathLocator);
  }

  private void clickHomePage(String name, By by) {
    log.info(linkClick(name, by));
  }
}
