package screenplay.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;


public class ScreenPlayCatalogPage {

    private final AndroidDriver driver;

    // ─── LOCATORS ────────────────────────────────────────────────────────────

    @AndroidFindBy(id = "com.example.shoppingcart:id/catalogTitle")
    private WebElement catalogTitle;

    @AndroidFindBy(id = "com.example.shoppingcart:id/searchField")
    private WebElement searchField;

    @AndroidFindBy(id = "com.example.shoppingcart:id/searchButton")
    private WebElement searchButton;

    @AndroidFindBy(id = "com.example.shoppingcart:id/categorySpinner")
    private WebElement categorySpinner;

    @AndroidFindBy(xpath = "//android.widget.ListView[@resource-id='com.example.shoppingcart:id/productList']")
    private WebElement productList;

    @AndroidFindBy(id = "com.example.shoppingcart:id/productItem")
    private List<WebElement> productItems;

    @AndroidFindBy(id = "com.example.shoppingcart:id/productName")
    private List<WebElement> productNames;

    @AndroidFindBy(id = "com.example.shoppingcart:id/productCategory")
    private List<WebElement> productCategories;

    @AndroidFindBy(id = "com.example.shoppingcart:id/emptyMessage")
    private WebElement emptyMessage;

    @AndroidFindBy(id = "com.example.shoppingcart:id/catalogTab")
    private WebElement catalogTab;

    // ─── CONSTRUCTOR ─────────────────────────────────────────────────────────

    public ScreenPlayCatalogPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    // ─── MÉTODOS DE NAVEGACIÓN ────────────────────────────────────────────────


    public void navigateToCatalog() {
        catalogTab.click();
    }

    // ─── MÉTODOS DE BÚSQUEDA ──────────────────────────────────────────────────


    public void searchProduct(String productName) {
        searchField.clear();
        searchField.sendKeys(productName);
        searchButton.click();
    }

    // ─── MÉTODOS DE FILTRADO ──────────────────────────────────────────────────

    public void filterByCategory(String categoryName) {
        categorySpinner.click();
        // Seleccionar la opción de categoría dentro del dropdown
        driver.findElement(
                io.appium.java_client.AppiumBy.xpath(
                        "//android.widget.CheckedTextView[@text='" + categoryName + "']"
                )
        ).click();
    }

    // ─── MÉTODOS DE VERIFICACIÓN ──────────────────────────────────────────────


    public boolean isProductListVisible() {
        return productList.isDisplayed() && !productItems.isEmpty();
    }


    public boolean allProductsContain(String keyword) {
        if (productNames.isEmpty()) {
            return false;
        }
        return productNames.stream()
                .allMatch(el -> el.getText().toLowerCase()
                        .contains(keyword.toLowerCase()));
    }


    public boolean allProductsBelongToCategory(String categoryName) {
        if (productCategories.isEmpty()) {
            return false;
        }
        return productCategories.stream()
                .allMatch(el -> el.getText().equalsIgnoreCase(categoryName));
    }


    public int getVisibleProductCount() {
        return productItems.size();
    }


    public boolean isCatalogDisplayed() {
        return catalogTitle.isDisplayed();
    }

    public String getEmptyMessage() {
        return emptyMessage.getText();
    }
}
