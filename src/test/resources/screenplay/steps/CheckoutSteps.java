package screenplay.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;

import screenplay.pages.CheckoutPage;
import screenplay.pages.ShippingPage;
import screenplay.pages.ScreenPlayLoginPage;
import screenplay.utils.DriverManager;

/**
 * Steps de Cucumber para el módulo de Checkout.
 * Utiliza patrón AAA y Assertions de JUnit 5.
 * Incluye hooks @Before y @After para manejo correcto del driver.
 */
public class CheckoutSteps {

    private AndroidDriver driver;
    private ScreenPlayLoginPage loginPage;
    private CheckoutPage checkoutPage;
    private ShippingPage shippingPage;

    // ─── HOOKS ────────────────────────────────────────────────────────────────


    @Before("@checkout")
    public void setUp() {
        // Arrange – preparar entorno de prueba
        driver = DriverManager.createDriver();
        loginPage   = new ScreenPlayLoginPage(driver);
        checkoutPage = new CheckoutPage(driver);
        shippingPage = new ShippingPage(driver);

        // Login previo requerido para todos los escenarios de checkout
        loginPage.enterUsername("usuario_prueba");
        loginPage.enterPassword("password123");
        loginPage.clickLogin();
    }


    @After("@checkout")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ─── GIVEN ────────────────────────────────────────────────────────────────


    @Given("que el usuario tiene productos en el carrito")
    public void elUsuarioTieneProductosEnElCarrito() {
        // Arrange – navegar al carrito
        checkoutPage.navigateToCart();

        // Assert – verificar que hay productos
        Assertions.assertTrue(
                checkoutPage.hasItemsInCart(),
                "El carrito debería tener al menos un producto antes de proceder al checkout"
        );
    }


    @Given("que el usuario tiene el carrito vacio")
    public void elUsuarioTieneElCarritoVacio() {
        // Arrange – navegar al carrito
        checkoutPage.navigateToCart();

        // Assert – verificar que el carrito está vacío
        Assertions.assertFalse(
                checkoutPage.hasItemsInCart(),
                "El carrito debería estar vacío para este escenario"
        );
    }

    // ─── WHEN ─────────────────────────────────────────────────────────────────


    @When("procede al checkout")
    public void procedeAlCheckout() {
        // Act
        checkoutPage.proceedToCheckout();
    }


    @When("intenta proceder al checkout")
    public void intentaProcederAlCheckout() {
        // Act
        checkoutPage.attemptCheckoutWithEmptyCart();
    }


    @And("ingresa los datos de envio")
    public void ingresaLosDatosDeEnvio() {
        // Assert – verificar que estamos en la pantalla de envío
        Assertions.assertTrue(
                shippingPage.isShippingPageDisplayed(),
                "Debería mostrarse la pantalla de datos de envío"
        );

        // Act – ingresar datos válidos
        shippingPage.fillShippingData(
                "Juan",
                "Perez",
                "Av. Arequipa 1234",
                "Lima",
                "15001"
        );
        shippingPage.clickContinue();
    }

    @And("confirma la compra")
    public void confirmaLaCompra() {
        // Act
        shippingPage.confirmOrder();
    }


    @And("intenta confirmar la compra sin ingresar direccion de envio")
    public void intentaConfirmarSinDireccion() {
        // Assert – verificar que estamos en la pantalla de envío
        Assertions.assertTrue(
                shippingPage.isShippingPageDisplayed(),
                "Debería mostrarse la pantalla de datos de envío"
        );

        // Act – hacer clic en continuar sin rellenar campos obligatorios
        shippingPage.clickContinueWithoutAddress();
    }

    // ─── THEN ─────────────────────────────────────────────────────────────────


    @Then("deberia ver el mensaje de compra exitosa")
    public void deberiaVerMensajeDeCompraExitosa() {
        // Assert
        Assertions.assertTrue(
                shippingPage.isSuccessMessageDisplayed(),
                "Debería mostrarse un mensaje de confirmación de compra exitosa"
        );
        Assertions.assertFalse(
                shippingPage.getSuccessMessage().isEmpty(),
                "El mensaje de éxito no debería estar vacío"
        );
    }


    @Then("deberia ver mensaje de carrito vacio")
    public void deberiaVerMensajeDeCarritoVacio() {
        // Assert
        Assertions.assertTrue(
                checkoutPage.isEmptyCartMessageDisplayed(),
                "Debería mostrarse el mensaje de carrito vacío"
        );
        Assertions.assertFalse(
                checkoutPage.getEmptyCartMessage().isEmpty(),
                "El mensaje de carrito vacío no debería estar vacío"
        );
    }


    @Then("deberia ver un mensaje de error solicitando la direccion de envio")
    public void deberiaVerMensajeErrorDireccion() {
        // Assert
        Assertions.assertTrue(
                shippingPage.isAddressErrorDisplayed(),
                "Debería mostrarse un mensaje de error indicando que la dirección es obligatoria"
        );
        Assertions.assertFalse(
                shippingPage.getAddressErrorMessage().isEmpty(),
                "El mensaje de error no debería estar vacío"
        );
    }
}
