package tau.selenium.saucedemo.tests;

import tau.selenium.saucedemo.pages.CartPage;
import tau.selenium.saucedemo.pages.ProductsPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

import static tau.selenium.saucedemo.constants.SauceLab.*;

public class ProductsTest extends BaseTest {

    @DataProvider(name = "dropdownValuesSortingParametersDataProvider")
    public static Object[][] dropdownValuesSortingParametersDataProvider() {
        return new Object[][]{
                {AZ_DROPDOWN_VALUE, NAME_SORTING_PARAMETER, true},
                {ZA_DROPDOWN_VALUE, NAME_SORTING_PARAMETER, false},
                {LOHI_DROPDOWN_VALUE, PRICE_SORTING_PARAMETER, true},
                {HILO_DROPDOWN_VALUE, PRICE_SORTING_PARAMETER, false},
        };
    }

    @Test(description = "Product should be added into cart")
    public void productShouldBeAddedIntoCart() {
        ProductsPage productsPage = loginValid();
        productsPage
                .addToCart(PRODUCT_FLEECE_JACKET_NAME);
        cartPage
                .openPage()
                .isPageOpened();
        String actualPrice = cartPage.getProductPrice(PRODUCT_FLEECE_JACKET_NAME);
        String actualQuantity = cartPage.getProductQuantity(PRODUCT_FLEECE_JACKET_NAME);
        cartAssert.productDetailsLike(actualPrice, actualQuantity,
                FLEECE_JACKET_PRICE.substring(1), "1");
    }

    @Test(description = "Products should be sorted by name and by price in direct and reverse order",
            dataProvider = "dropdownValuesSortingParametersDataProvider")
    public void productsShouldBeSortedByNameAndByPriceInDirectAndReverseOrder(String dropdownValue,
                                                                              String sortingParameter,
                                                                              boolean sortingOrder) {
        ProductsPage productsPage = loginValid();
        Map<String, String> productsNamePriceMap = productsPage
                .selectOptionInDropdown(dropdownValue)
                .getProductsNamePriceMap();
        Map<String, String> expectedProductsNamePriceMap = productsAssert.sortProducts(PRODUCT_NAME_PRICE_MAP,
                sortingParameter, sortingOrder);
        productsPage.areNamesVisible();
        productsPage.arePricesVisible();
        productsAssert.productNamesAndPricesLike(productsNamePriceMap, expectedProductsNamePriceMap);
    }

    @Test(description = "Button to add remove product should change name after adding or removing product")
    public void buttonToAddRemoveProductShouldChangeNameAfterAddingRemovingProduct() {
        ProductsPage productsPage = loginValid();
        String removeButtonName = productsPage
                .addToCart(PRODUCT_FLEECE_JACKET_NAME)
                .isRemoveButtonVisible(PRODUCT_FLEECE_JACKET_NAME)
                .getAddToCartRemoveButtonName(PRODUCT_FLEECE_JACKET_NAME);
        productsAssert.buttonNameLike(removeButtonName, REMOVE_BUTTON_NAME);
        String addToCartButtonName = productsPage
                .removeFromCart(PRODUCT_FLEECE_JACKET_NAME)
                .isAddButtonVisible(PRODUCT_FLEECE_JACKET_NAME)
                .getAddToCartRemoveButtonName(PRODUCT_FLEECE_JACKET_NAME);
        productsAssert.buttonNameLike(addToCartButtonName, ADD_TO_CART_BUTTON_NAME);
    }

    @Test(description = "Product count in shopping cart should be increased" +
            "after product adding and decreased after product removing")
    public void productCountInShoppingCartShouldBeIncreasedAfterProductAddingAndDecreasedAfterProductRemoving() {
        ProductsPage productsPage = loginValid();
        Set<String> productNames = PRODUCT_NAME_PRICE_MAP.keySet();
        productsAssert.productCountInShoppingCartLikeAfterEachProductAddingRemoving(productsPage,
                productNames, 0, true);
        productsAssert.productCountInShoppingCartLikeAfterEachProductAddingRemoving(productsPage,
                productNames, 6, false);
    }

    @Test(description = "Empty shopping cart opens after click on cart")
    public void emptyShoppingCartOpensAfterClickOnCart() {
        ProductsPage productsPage = loginValid();
        CartPage cartPage = productsPage
                .clickOnCart()
                .isPageOpened();
        cartAssert.cartPageOpened(cartPage);
    }

    @Test(description = "Shopping cart with one product opens after click on cart")
    public void shoppingCartWithOneProductOpensAfterClickOnCart() {
        ProductsPage productsPage = loginValid();
        productsPage
                .addToCart(PRODUCT_FLEECE_JACKET_NAME);
        CartPage cartPage = productsPage
                .clickOnCart()
                .isPageOpened();
        cartAssert.cartPageOpened(cartPage);
    }
}
