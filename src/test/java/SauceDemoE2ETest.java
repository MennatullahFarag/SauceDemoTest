import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.CartPage;
import pages.Inventory;

import pages.LoginPage;
import utils.*;


@Listeners({io.qameta.allure.testng.AllureTestNg.class, utils.TestngListener.class})
public class SauceDemoE2ETest {
    private WebDriver driver;

    private JsonFileManager testData;
    private Inventory inventory;
    private LoginPage loginPage;
    private CartPage cartPage;
    private LoggerClass mylogger;
    private Validations validate;



    /******************************************* Test Cases *******************************************/

    @Test
    @Description("Open SauceDemo website and login with standard user, Add products to cart and checkout")
    @Story("Select Products")
    //@Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    @Epic("Automation Event ")
    @Feature("Selenium")
    public void selectProductsThenCheckout() {

        loginPage.navigateToLandingPage().login(testData.getTestData("username_standard"), testData.getTestData("password"));

        inventory.addProductsToCartAndNavigateToCart();

        cartPage.checkout(testData.getTestData("firstName"), testData.getTestData("lastName"), testData.getTestData("postalCode"));

    }


    @Test
    @Description("Open SauceDemo website and login with standard user and verify 'Reset App State' works properly")
    @Story("Select Products")
    //@Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    @Epic("Automation Event ")
    @Feature("Selenium")
    public void verifyResetAppStateWorksProperly(){

        loginPage.navigateToLandingPage().login(testData.getTestData("username_standard"),testData.getTestData("password"));

        inventory.addProductsToCart();

        inventory.resetAppState();

        inventory.checkElement();

    }

    /*@Test
    @Description("Open SauceDemo website and login with standard user and try to checkout without adding any products to cart")
    @Story("Select Products")
    //@Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    @Epic("Automation Event ")
    @Feature("Selenium")
    public void checkoutWhenCartIsEmpty(){

        loginPage.navigateToLandingPage().login(testData.getTestData("username_standard"),testData.getTestData("password"));

        inventory.clickOnCart();

        cartPage.ifCheckoutBtnDisabled();

    }*/


/******************************************* Configurations *******************************************/
    @BeforeClass
    public void classSetup() {
        testData = new JsonFileManager("src/test/resources/TestData/SauceDemoTestData.json");
    }

    @BeforeMethod
    public void methodSetup() {

        driver = BrowserFactory.getBrowser();
        inventory = new Inventory(driver);
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterMethod
    public void methodTearDown() {
        if (driver != null) {
            Validations.assertAll();
            BrowserActions browserActions = new BrowserActions(driver);
            browserActions.closeAllOpenedBrowserWindows();
        }
    }
}
