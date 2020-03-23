package inputs;

import base.BaseTests;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.InputPage;

import static org.testng.Assert.assertEquals;

public class InputTests extends BaseTests {

  protected static final Logger log = Logger.getLogger(InputTests.class);
  private static InputPage inputPage;
  private static SoftAssert softAssert1;

  @BeforeClass
  public void beforeClass() {
    inputPage = new HomePage(driver).clickInput();
    inputPage.validateInputPage();
  }

  @BeforeMethod
  public void beforeMethod() {
    softAssert1 = new SoftAssert();
    driver.navigate().refresh();
  }

  @Test
  public void checkInputFromKeyboard() {
    Integer value = 999;
    inputPage.sendValueToNumberField(value);
    assertEquals(value.toString(), inputPage.getNumberFieldValue());
  }

  @Test
  public void checkFirstValue() {
    assertEquals("", inputPage.getNumberFieldValue());
  }

  @Test
  public void checkRaiseKeys() {
    int index = 3;
    for (int i = 1; i <= index ; i++) {
      inputPage.upNumberField(1);
      softAssert1.assertEquals(String.valueOf(i), inputPage.getNumberFieldValue());
    }
    softAssert1.assertAll();
  }

  @Test
  public void checkDownKeys() {
    inputPage.downNumberField(1);
    softAssert1.assertEquals("-1", inputPage.getNumberFieldValue());
    softAssert1.assertAll();
  }

  @Test
  public void checkMultipleManipulations() {
    int value1 = 5;
    int value2 = 3;

    inputPage.sendValueToNumberField(value1);
    inputPage.upNumberField(value2);
    softAssert1.assertEquals(inputPage.getNumberFieldValue(), "8");

    inputPage.downNumberField(value2);
    softAssert1.assertEquals(inputPage.getNumberFieldValue(), "5");
    softAssert1.assertAll();
  }

  @AfterMethod
  public void afterMethod() {
    driver.navigate().refresh();
  }
}
