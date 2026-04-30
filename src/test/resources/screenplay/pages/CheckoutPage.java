package screenplay.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class CheckoutPage {

    private final AndroidDriver driver;

    // ─── LOCATORS ────────────────────────────────────────────────────────────

    @AndroidFindBy(id = "com.example.shoppingcart:id/cartTitle")
    private WebElement cartTitle;

    @AndroidFindBy(id = "com.example.shoppingcart:id/checkoutButton")
    private WebElement checkoutButton;

    @AndroidFindBy(id = "com.example.shoppingcart:id/emptyCartMessage")
    private WebElement emptyCartMessage;

    @AndroidFindBy(id = "com.example.shoppingcart:id/cartItemCount")
    private WebElement cartItemCount;

    @AndroidFindBy(id = "com.example.shoppingcart:id/cartTab")
    private WebElement cartTab;

    // ─── CONSTRUCTOR ─────────────────────────────────────────────────────────

    public CheckoutPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    // ─── MÉTODOS ─────────────────────────────────────────────────────────────


    public void navigateToCart() {
        cartTab.click();
    }


    public void proceedToCheckout() {
        checkoutButton.click();
    }

    public void attemptCheckoutWithEmptyCart() {
        checkoutButton.click();
    }


    public boolean hasItemsInCart() {
        try {
            String count = cartItemCount.getText().trim();
            return Integer.parseInt(count) > 0;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isEmptyCartMessageDisplayed() {
        return emptyCartMessage.isDisplayed();
    }


    public String getEmptyCartMessage() {
        return emptyCartMessage.getText();
    }


    public boolean isCheckoutButtonEnabled() {
        return checkoutButton.isEnabled();
    }
}
