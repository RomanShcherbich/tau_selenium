package pages.HoversPage;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.base.BasePage;

public class HoversPage extends BasePage {

  public HoversPage(WebDriver driver) {
    super(driver);
  }

  private static final Logger log = Logger.getLogger(HoversPage.class);

  private By byAvatar = new By.ByCssSelector(".figure");
  private By byFigureCaption = new By.ByClassName("figcaption");

  /**
   *
   * @param index starts at 1
   * @return Captured element under the cursor
   */
  public HoveredAvatar hoverOverFigure(int index) {
    WebElement avatar = getDriver().findElements(byAvatar).get(index-1);

    Actions actions = new Actions(getDriver());
    actions.moveToElement(avatar).perform();

    log.info("Cursor is hovered over avatar " + index);

    WebElement hoveredElement = avatar.findElement(byFigureCaption);

    return new HoveredAvatar(hoveredElement);
  }
}
