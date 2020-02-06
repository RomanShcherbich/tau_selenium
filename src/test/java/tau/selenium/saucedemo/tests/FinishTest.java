package tau.selenium.saucedemo.tests;

import tau.selenium.saucedemo.pages.CheckoutInformationPage;
import tau.selenium.saucedemo.pages.CheckoutOverviewPage;
import tau.selenium.saucedemo.pages.FinishPage;
import tau.selenium.saucedemo.pages.ProductsPage;
import org.testng.annotations.Test;

import static tau.selenium.saucedemo.constants.SauceLab.*;

public class FinishTest extends BaseTest {

    @Test(description = "Thanks for order message should appear after successful order")
    public void thanksForOrderMessageShouldAppearAfterSuccessfulOrder() {
        ProductsPage productsPage = loginValid();
        productsPage
                .addToCart(PRODUCT_FLEECE_JACKET_NAME);
        CheckoutInformationPage checkoutInformationPage = cartPage
                .openPage()
                .isPageOpened()
                .clickOnCheckOutButton();
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage
                .isPageOpened()
                .fillInformation(FIRST_NAME, LAST_MANE, POSTAL_CODE)
                .clickOnContinueButton();
        FinishPage finishPage = checkoutOverviewPage
                .isPageOpened()
                .clickOnFinishButton();
        finishPage
                .isPageOpened();

        String thanksForOrderMessage = finishPage.getText();
        finishAssert.messageLike(thanksForOrderMessage, THANK_YOU_FOR_YOUR_ORDER_MESSAGE);
    }
}
