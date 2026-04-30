package screenplay.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;

import screenplay.pages.ScreenPlayCatalogPage;
import screenplay.pages.ScreenPlayLoginPage;
import screenplay.utils.DriverManager;


public class CatalogSteps {

    private AndroidDriver driver;
    private ScreenPlayCatalogPage catalogPage;
    private ScreenPlayLoginPage loginPage;

    // ─── HOOKS ────────────────────────────────────────────────────────────────


    @Before("@catalogo")
    public void setUp() {
        // Arrange – inicialización del entorno
        driver = DriverManager.createDriver();
        loginPage = new ScreenPlayLoginPage(driver);
        catalogPage = new ScreenPlayCatalogPage(driver);
    }


    @After("@catalogo")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ─── GIVEN ────────────────────────────────────────────────────────────────


    @Given("que el usuario esta logueado en la aplicacion")
    public void elUsuarioEstaLogueado() {
        loginPage.enterUsername("usuario_prueba");
        loginPage.enterPassword("password123");
        loginPage.clickLogin();

        Assertions.assertTrue(
                loginPage.isHomePageDisplayed(),
                "El usuario debería haber accedido a la HomePage tras el login"
        );
    }


    @Given("que el usuario esta en el catalogo")
    public void elUsuarioEstaEnElCatalogo() {
        // Arrange
        loginPage.enterUsername("usuario_prueba");
        loginPage.enterPassword("password123");
        loginPage.clickLogin();

        // Act
        catalogPage.navigateToCatalog();

        // Assert
        Assertions.assertTrue(
                catalogPage.isCatalogDisplayed(),
                "El catálogo debería estar visible"
        );
    }

    // ─── WHEN ─────────────────────────────────────────────────────────────────


    @When("navega al catalogo de productos")
    public void navegaAlCatalogo() {
        // Act
        catalogPage.navigateToCatalog();
    }


    @When("busca el producto {string}")
    public void buscaProducto(String productName) {
        // Act
        catalogPage.searchProduct(productName);
    }


    @When("selecciona la categoria {string}")
    public void seleccionaCategoria(String categoryName) {
        // Act
        catalogPage.filterByCategory(categoryName);
    }

    // ─── THEN ─────────────────────────────────────────────────────────────────


    @Then("deberia ver la lista de productos disponibles")
    public void deberiaVerListaDeProductos() {
        // Assert
        Assertions.assertTrue(
                catalogPage.isProductListVisible(),
                "La lista de productos debería ser visible y contener al menos un elemento"
        );
        Assertions.assertTrue(
                catalogPage.getVisibleProductCount() > 0,
                "Debería haber al menos un producto en el catálogo"
        );
    }


    @Then("deberia ver productos que contengan {string}")
    public void deberiaVerProductosQueContengan(String keyword) {
        // Assert – todos los productos visibles deben contener el keyword
        Assertions.assertTrue(
                catalogPage.isProductListVisible(),
                "Debería mostrarse al menos un resultado para '" + keyword + "'"
        );
        Assertions.assertTrue(
                catalogPage.allProductsContain(keyword),
                "Todos los productos mostrados deberían contener '" + keyword + "' en su nombre"
        );
    }


    @Then("deberia ver solo productos de la categoria {string}")
    public void deberiaVerSoloProductosDeLaCategoria(String categoryName) {
        // Assert
        Assertions.assertTrue(
                catalogPage.isProductListVisible(),
                "Debería mostrarse al menos un producto de la categoría '" + categoryName + "'"
        );
    }


    @Then("todos los productos mostrados pertenecen a la categoria {string}")
    public void todosLosProductosPertenecenALaCategoria(String categoryName) {
        // Assert
        Assertions.assertTrue(
                catalogPage.allProductsBelongToCategory(categoryName),
                "Todos los productos visibles deberían pertenecer a la categoría '" + categoryName + "'"
        );
    }
}
