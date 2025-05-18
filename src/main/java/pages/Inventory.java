package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utils.BrowserFactory.handlePopups;
import static utils.ElementActions.*;
import static utils.Validations.*;


public class Inventory {
    private WebDriver driver;

    private By firstProduct = By.id("add-to-cart-sauce-labs-backpack");
    private By secondProduct = By.id("add-to-cart-sauce-labs-bike-light");
    private By shoppingCart = By.id("shopping_cart_container");
    private By menuBtn = By.id("react-burger-menu-btn");
    private By menuContainer = By.className("bm-menu-wrap");
    private By resetAppStateLink = By.id("reset_sidebar_link");
    private By menuCloseBtn = By.id("react-burger-cross-btn");



    // Constructor
    public Inventory(WebDriver driver) {
        this.driver = driver;
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////// Actions ////////////////////////////////




    @Step("Add Items to Cart then go to cart page")
    public Inventory addProductsToCartAndNavigateToCart() {

        click(driver, firstProduct);
        click(driver, secondProduct);
        click(driver, shoppingCart);
        handlePopups();
        return this;
    }

    @Step("Add Items to Cart")
    public Inventory addProductsToCart() {

        click(driver, firstProduct);
        click(driver, secondProduct);
        handlePopups();
        return this;
    }

    @Step("Clicking on 'Reset App State' from side menu")
    public Inventory resetAppState() {

        click(driver, menuBtn);
        softAssertElementDisplayed(driver, menuContainer, "menu container");
        softAssertElementIsNotHidden(driver, menuContainer, "side menu is not hidden");
        handlePopups();
        mouseHover(driver, resetAppStateLink);
        click(driver, resetAppStateLink);
        click(driver, menuCloseBtn);
        return this;
    }

    @Step("Clicking on Cart without adding items")
    public Inventory clickOnCart() {

        click(driver, shoppingCart);
        handlePopups();

        return this;
    }

    @Step("Check Add to cart button")
    public Inventory checkElement() {

        softAssertElementDisplayed(driver, firstProduct, "Add to cart button");

        return this;
    }



}