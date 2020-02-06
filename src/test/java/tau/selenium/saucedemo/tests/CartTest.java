package tau.selenium.saucedemo.tests;

import tau.selenium.saucedemo.pages.ProductsPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static tau.selenium.saucedemo.constants.SauceLab.*;

public class CartTest extends BaseTest {

    @Test(description = "Products page should be opened after continue shopping button clicking" +
            " if one product is added to cart")
    public void productsPageShouldBeOpenedAfterContinueShoppingButtonClickingIfOneProductAddedToCart() {
        ProductsPage productsPage = loginValid();
        productsPage
                .addToCart(PRODUCT_FLEECE_JACKET_NAME);
        productsPage = cartPage
                .openPage()
                .isPageOpened()
                .clickOnContinueShoppingButton()
                .isPageOpened();
        String removeButtonName = productsPage
                .isRemoveButtonVisible(PRODUCT_FLEECE_JACKET_NAME)
                .getAddToCartRemoveButtonName(PRODUCT_FLEECE_JACKET_NAME);
        productsAssert.buttonNameLike(removeButtonName, REMOVE_BUTTON_NAME);
        String actualProductCount = productsPage.getProductsNumberInShoppingCart();
        productsAssert.productNumberLike(actualProductCount, "1");
    }

    @Test(description = "Products page should be opened after continue shopping button clicking" +
            " if no product is added to cart")
    public void productsPageShouldBeOpenedAfterContinueShoppingButtonClickingIfNoProductAddedToCart() {
        ProductsPage productsPage = loginValid();
        productsPage = cartPage
                .openPage()
                .isPageOpened()
                .clickOnContinueShoppingButton()
                .isPageOpened();
        String actualProductCount = productsPage.getProductsNumberInShoppingCart();
        productsAssert.productNumberLike(actualProductCount, "0");
    }

    @Test(description = "Product number in shopping cart should be equal zero after removing one existed product")
    public void productNumberInShoppingCartShouldBeEqualZeroAfterRemovingOneExistedProduct() {
        ProductsPage productsPage = loginValid();
        productsPage
                .addToCart(PRODUCT_FLEECE_JACKET_NAME);
        List<WebElement> productsInShoppingCart = cartPage
                .openPage()
                .isPageOpened()
                .clickOnRemoveButton(PRODUCT_FLEECE_JACKET_NAME)
                .getProductsInShoppingCart();
        cartAssert.productNumberLike(productsInShoppingCart.size(), 0);
    }

    @Test(description = "Product number in shopping cart should be equal one after removing one of" +
            " two products in cart")
    public void productNumberInShoppingCartShouldBeEqualOneAfterRemovingOneOfTwoProducts() {
        ProductsPage productsPage = loginValid();
        productsPage
                .addToCart(PRODUCT_FLEECE_JACKET_NAME)
                .addToCart(PRODUCT_BACKPACK_NAME);
        List<WebElement> productsInShoppingCart = cartPage
                .openPage()
                .isPageOpened()
                .clickOnRemoveButton(PRODUCT_FLEECE_JACKET_NAME)
                .getProductsInShoppingCart();
        cartAssert.productNumberLike(productsInShoppingCart.size(), 1);
        cartAssert.productsNameLike(Collections.singletonList(PRODUCT_BACKPACK_NAME),
                Collections.singletonList(PRODUCT_BACKPACK_NAME));
    }
}
