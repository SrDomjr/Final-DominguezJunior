package screenplay.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;


public class ProfilePage {

    private final AndroidDriver driver;

    // ─── LOCATORS ────────────────────────────────────────────────────────────

    @AndroidFindBy(id = "com.example.shoppingcart:id/userMenuButton")
    private WebElement userMenuButton;

    @AndroidFindBy(id = "com.example.shoppingcart:id/logoutButton")
    private WebElement logoutButton;

    @AndroidFindBy(id = "com.example.shoppingcart:id/usernameLabel")
    private WebElement usernameLabel;

    @AndroidFindBy(id = "com.example.shoppingcart:id/profileEmail")
    private WebElement profileEmail;

    @AndroidFindBy(id = "com.example.shoppingcart:id/loginScreen")
    private WebElement loginScreen;

    @AndroidFindBy(id = "com.example.shoppingcart:id/profileMenuContainer")
    private WebElement profileMenuContainer;

    // ─── CONSTRUCTOR ─────────────────────────────────────────────────────────

    public ProfilePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    // ─── MÉTODOS ─────────────────────────────────────────────────────────────

    public void clickUserMenu() {
        userMenuButton.click();
    }


    public void clickLogout() {
        logoutButton.click();
    }


    public boolean isLogoutButtonVisible() {
        try {
            return logoutButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUsername() {
        return usernameLabel.getText();
    }


    public String getCurrentUserEmail() {
        return profileEmail.getText();
    }


    public boolean isLoginScreenDisplayed() {
        try {
            return loginScreen.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isProfileMenuVisible() {
        try {
            return profileMenuContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
