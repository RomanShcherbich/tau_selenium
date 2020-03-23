package dropdown;

import base.BaseTests;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.DropdownPage;
import pages.HomePage;

public class DropdownTests extends BaseTests {
  protected static final Logger log = Logger.getLogger(DropdownTests.class);
  private static DropdownPage dropdownPage;
  private static SoftAssert softAssert1;

  private final String option1 = "Option 1";
  private final String option2 = "Option 2";

  @BeforeClass
  public void beforeClass() {
    dropdownPage = new HomePage(driver).clickDropdown();
    dropdownPage.validateDropdownPage();
  }

  @BeforeMethod
  public void beforeMethod() {
    softAssert1 = new SoftAssert();
    driver.navigate().refresh();
  }

  @Test(priority = 1)
  public void checkListOfOptions() {
    softAssert1.assertEquals(dropdownPage.selectedOption(),"Please select an option","All options don't match expected list");
    softAssert1.assertAll();
  }

  @Test(priority = 2)
  public void checkSelection() {

    dropdownPage.selectVisibleText(option1);
    softAssert1.assertEquals(dropdownPage.selectedOption(),option1,"The first value from drop down list is invalid");

    dropdownPage.selectVisibleText(option2);
    softAssert1.assertEquals(dropdownPage.selectedOption(),option2,"The second value from drop down list is invalid");

    softAssert1.assertAll();
  }

  @Test(priority = 3)
  public void checkSendingKeys() {
    dropdownPage.downNumberField(1);
    softAssert1.assertEquals(dropdownPage.getSelectedOptionText(),option1,"V - The first value from drop down list is invalid");
    dropdownPage.downNumberField(1);
    softAssert1.assertEquals(dropdownPage.getSelectedOptionText(),option2,"V + V - The second value from drop down list is invalid");

    dropdownPage.upNumberField(1);
    softAssert1.assertEquals(dropdownPage.getSelectedOptionText(),option1,"V + V + ^ - The first value from drop down list is invalid");

    softAssert1.assertAll();
  }
}
