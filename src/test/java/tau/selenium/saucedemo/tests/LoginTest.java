package tau.selenium.saucedemo.tests;

import tau.selenium.saucedemo.pages.ModalMenuPage;
import tau.selenium.saucedemo.pages.ProductsPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static tau.selenium.saucedemo.constants.SauceLab.*;
import static tau.selenium.saucedemo.pages.LoginPage.LOGIN_BUTTON;
import static tau.selenium.saucedemo.pages.LoginPage.LOGIN_PAGE_URL;

public class LoginTest extends BaseTest {

    @DataProvider(name = "invalidCredsDataProvider")
    public Object[][] invalidCredsDataProvider() {
        return new Object[][]{
                {getEnvOrReadProperty(SAUCE_LAB_USER_PROPERTY), "Invalid password",
                        INVALID_USER_NAME_PASSWORD_ERROR},
                {"Invalid user name", getEnvOrReadProperty(SAUCE_LAB_PASS_PROPERTY),
                        INVALID_USER_NAME_PASSWORD_ERROR},
                {getEnvOrReadProperty(SAUCE_LAB_LOCKED_USER_PROPERTY), getEnvOrReadProperty(SAUCE_LAB_PASS_PROPERTY),
                        LOCKED_USER_ERROR},
        };
    }

    @Test(description = "Products page is opened after successful login")
    public void productsPageOpenedAfterSuccessfulLogin() {
        ProductsPage productsPage = loginValid();
        String productsLabel = productsPage.getProductsLabel();
        productsAssert.productsLabelLike(productsLabel, "Products");
        int productItemsNumber = productsPage.getProductItems().size();
        productsAssert.productsNumberLike(productItemsNumber, 6);
    }

    @Test(description = "Logout should lead to login page")
    public void logoutShouldLeadToLoginPage() {
        ProductsPage productsPage = loginValid();
        ModalMenuPage modalMenuPage = productsPage
                .openMenu()
                .isPageOpened();
        modalMenuPage
                .logOut();
        loginPage
                .isPageOpened();

        boolean isLoginDisplayed = loginPage.isElementDisplayed(LOGIN_BUTTON);
        String pageUrl = loginPage.getPageUrl();
        loginAssert.loginPageOpened(isLoginDisplayed, pageUrl, LOGIN_PAGE_URL);
    }

    @Test(description = "Error message should appear on attempt to login with invalid username and password" +
            " or with locked user",
            dataProvider = "invalidCredsDataProvider")
    public void errorMessageShouldAppearOnAttemptToLoginWithInvalidUsernameAndPassword(String userName,
                                                                                       String password,
                                                                                       String expectedErrorMessage) {
        loginPage
                .openPage()
                .isPageOpened()
                .tryLogin(userName, password);
        String errorMessage = loginPage.getLockedUserMessage();
        loginAssert.errorMessageAppearAfterLoginByLockedUser(errorMessage, expectedErrorMessage);
    }
}
