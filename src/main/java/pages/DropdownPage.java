package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class DropdownPage extends HomePage {
  private static final Logger log = Logger.getLogger(DropdownPage.class);

  private final String dropdownName = "dropdown";
  private final By dropdownField = new By.ById(dropdownName);

  private final By selectedOption = new By.ByCssSelector("option[selected]");

  public DropdownPage(WebDriver driver) {
    super(driver);
  }

  public void selectVisibleText(String option) {
    selectDropdown().selectByVisibleText(option);
  }

  public String selectedOption() {
    return selectDropdown().getAllSelectedOptions().stream()
        .map(WebElement::getText)
        .collect(Collectors.toList()).get(0);
  }

  private Select selectDropdown() {
    return new Select(getDriver().findElement(dropdownField));
  }

  public void upNumberField(int x) {
    for (int j = 0; j < x; j++) {
      log.info(sendFromKeyboard(dropdownName, Keys.ARROW_UP, dropdownField));
    }
  }

  public void downNumberField(int x) {
    for (int j = 0; j < x; j++) {
      log.info(sendFromKeyboard(dropdownName, Keys.ARROW_DOWN, dropdownField));
    }
  }

  public void validateDropdownPage() {
    verifyElement(dropdownField);
    log.info("It is dropdown page. Validated by dropdown field");
  }

  public String getSelectedOptionText() {
    return getElementText(selectedOption);
  }
}
