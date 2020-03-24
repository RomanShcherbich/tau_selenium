package hovers;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.HoversPage.HoveredAvatar;
import pages.HoversPage.HoversPage;

@Test(dataProvider = "bddData")
public class HoversTests extends BaseTests {

  private HoversPage hoversPage;
  private static SoftAssert softAssert1;

  @BeforeClass
  public void beforeClass() {
    hoversPage = new HomePage(driver).clickHovers();
  }

  @BeforeMethod
  public void beforeMethod() {
    softAssert1 = new SoftAssert();
  }

  public void checkCaptionOfAvatar(int index) {
    HoveredAvatar avatar = hoversPage.hoverOverFigure(index);
    softAssert1.assertTrue(avatar.isHeaderDisplayed(), "User" + index + " details are not displayed");
    softAssert1.assertTrue(avatar.isProfileLinkDisplayed(), "Profile link " + index + " is not displayed");
    softAssert1.assertEquals(avatar.getHeaderText(), "name: user" + index, "Invalid user name text " + index);
    softAssert1.assertEquals(avatar.getProfileLinkText(), "View profile", "Invalid Profile link text " + index);
    softAssert1.assertTrue(avatar.getProfileHref().endsWith("/users/" + index), "Invalid profile link text " + index);
    softAssert1.assertAll();
  }

  @DataProvider
  public Object[] bddData() {
    Object[] data = new Object[3];
    data[0] = 1;
    data[1] = 2;
    data[2] = 3;
    return data;
  }

}
