package screenplay.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;


public class ShippingPage {

    private final AndroidDriver driver;

    // ─── LOCATORS ────────────────────────────────────────────────────────────

    @AndroidFindBy(id = "com.example.shoppingcart:id/shippingTitle")
    private WebElement shippingTitle;

    @AndroidFindBy(id = "com.example.shoppingcart:id/firstNameField")
    private WebElement firstNameField;

    @AndroidFindBy(id = "com.example.shoppingcart:id/lastNameField")
    private WebElement lastNameField;

    @AndroidFindBy(id = "com.example.shoppingcart:id/addressField")
    private WebElement addressField;

    @AndroidFindBy(id = "com.example.shoppingcart:id/cityField")
    private WebElement cityField;

    @AndroidFindBy(id = "com.example.shoppingcart:id/zipCodeField")
    private WebElement zipCodeField;

    @AndroidFindBy(id = "com.example.shoppingcart:id/continueButton")
    private WebElement continueButton;

    @AndroidFindBy(id = "com.example.shoppingcart:id/shippingErrorMessage")
    private WebElement shippingErrorMessage;

    @AndroidFindBy(id = "com.example.shoppingcart:id/confirmOrderButton")
    private WebElement confirmOrderButton;

    @AndroidFindBy(id = "com.example.shoppingcart:id/successMessage")
    private WebElement successMessage;

    // ─── CONSTRUCTOR ─────────────────────────────────────────────────────────

    public ShippingPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    // ─── MÉTODOS ─────────────────────────────────────────────────────────────

    /** @return true si la pantalla de envío está visible. */
    public boolean isShippingPageDisplayed() {
        return shippingTitle.isDisplayed();
    }

    public void fillShippingData(String firstName, String lastName,
                                 String address, String city, String zipCode) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        addressField.clear();
        addressField.sendKeys(address);

        cityField.clear();
        cityField.sendKeys(city);

        zipCodeField.clear();
        zipCodeField.sendKeys(zipCode);
    }


    public void clickContinueWithoutAddress() {
        continueButton.click();
    }


    public void clickContinue() {
        continueButton.click();
    }


    public void confirmOrder() {
        confirmOrderButton.click();
    }


    public boolean isSuccessMessageDisplayed() {
        return successMessage.isDisplayed();
    }


    public String getSuccessMessage() {
        return successMessage.getText();
    }


    public boolean isAddressErrorDisplayed() {
        return shippingErrorMessage.isDisplayed();
    }


    public String getAddressErrorMessage() {
        return shippingErrorMessage.getText();
    }
}
