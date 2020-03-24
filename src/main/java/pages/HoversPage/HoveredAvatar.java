package pages.HoversPage;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class HoveredAvatar {

  private static final Logger log = Logger.getLogger(HoveredAvatar.class);

  private By header = new By.ByXPath(".//h5");
  private By profileLink = new By.ByXPath(".//a");
  private static SoftAssert softAssert1;

  private WebElement avatar;

  public HoveredAvatar(WebElement element) {
    this.avatar = element;
  }

  public boolean isHeaderDisplayed() {
    boolean isDisplayed = avatar.findElement(header).isDisplayed();
    log.info("Header is displayed - " + isDisplayed);
    return isDisplayed;
  }

  public boolean isProfileLinkDisplayed() {
    boolean isDisplayed = avatar.findElement(profileLink).isDisplayed();
    log.info("Profile link is displayed - " + isDisplayed);
    return isDisplayed;
  }

  public String getHeaderText() {
    avatar.findElement(header).getText();
    String text = avatar.findElement(header).getText();
    log.info("Avatar Header text = " + text);
    return text;
  }

  public String getProfileLinkText() {
    String text = avatar.findElement(profileLink).getText();
    log.info("Avatar Link text = " + text);
    return text;
  }

  public String getProfileHref() {
    String url = avatar.findElement(profileLink).getAttribute("href");
    log.info("Avatar href = " + url);
    return url;
  }
}
