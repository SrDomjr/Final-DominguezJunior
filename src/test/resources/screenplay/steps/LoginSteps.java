package screenplay.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;

import screenplay.pages.ScreenPlayLoginPage;
import screenplay.utils.DriverManager;


public class LoginSteps {

    private AndroidDriver driver;
    private ScreenPlayLoginPage loginPage;

    // ─── HOOKS ────────────────────────────────────────────────────────────────

    @Before("@login")
    public void setUp() {
        driver    = DriverManager.createDriver();
        loginPage = new ScreenPlayLoginPage(driver);
    }

    @After("@login")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ─── GIVEN ────────────────────────────────────────────────────────────────

    @Given("que el usuario tiene credenciales validas")
    public void elUsuarioTieneCredencialesValidas() {

    }

    @Given("que el usuario tiene credenciales invalidas")
    public void elUsuarioTieneCredencialesInvalidas() {

    }

    // ─── WHEN ─────────────────────────────────────────────────────────────────

    @When("ingresa usuario {string} y contrasena {string}")
    public void ingresaUsuarioYContrasena(String username, String password) {
        // Act
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    // ─── THEN ─────────────────────────────────────────────────────────────────


    @Then("deberia acceder a la pagina principal")
    public void deberiaAccederALaPaginaPrincipal() {

        Assertions.assertTrue(
                loginPage.isHomePageDisplayed(),
                "Tras un login exitoso, el usuario debería ver la pantalla principal (HomePage)"
        );


        Assertions.assertFalse(
                loginPage.isLoginScreenDisplayed(),
                "La pantalla de login no debería seguir visible después de un acceso exitoso"
        );
    }


    @Then("deberia ver un mensaje de error de login")
    public void deberiaVerMensajeDeErrorDeLogin() {

        Assertions.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Ante credenciales inválidas, debería mostrarse un mensaje de error de login"
        );


        String errorMessage = loginPage.getErrorMessage();
        Assertions.assertFalse(
                errorMessage.isEmpty(),
                "El mensaje de error no debería estar vacío"
        );


        Assertions.assertFalse(
                loginPage.isHomePageDisplayed(),
                "Con credenciales inválidas, el usuario no debería acceder a la HomePage"
        );


        Assertions.assertTrue(
                loginPage.isLoginScreenDisplayed(),
                "La pantalla de login debería permanecer visible después de un login fallido"
        );
    }
}
