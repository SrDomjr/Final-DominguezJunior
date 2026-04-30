package screenplay.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;

import screenplay.pages.ProfilePage;
import screenplay.pages.ScreenPlayLoginPage;
import screenplay.utils.DriverManager;


public class LogoutSteps {

    private AndroidDriver driver;
    private ScreenPlayLoginPage loginPage;
    private ProfilePage profilePage;

    // ─── HOOKS ────────────────────────────────────────────────────────────────


    @Before("@logout")
    public void setUp() {
        driver      = DriverManager.createDriver();
        loginPage   = new ScreenPlayLoginPage(driver);
        profilePage = new ProfilePage(driver);

        loginPage.enterUsername("usuario_prueba");
        loginPage.enterPassword("password123");
        loginPage.clickLogin();

        Assertions.assertTrue(
                loginPage.isHomePageDisplayed(),
                "El login previo al escenario de logout debería ser exitoso"
        );
    }


    @After("@logout")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ─── GIVEN ────────────────────────────────────────────────────────────────


    @Given("que el usuario esta logueado en la aplicacion")
    public void elUsuarioEstaLogueado() {

        Assertions.assertTrue(
                loginPage.isHomePageDisplayed(),
                "El usuario debería estar en la pantalla principal (logueado)"
        );
    }

    // ─── WHEN ─────────────────────────────────────────────────────────────────


    @When("hace clic en el menu de usuario")
    public void haceClicEnElMenuDeUsuario() {

        profilePage.clickUserMenu();


        Assertions.assertTrue(
                profilePage.isProfileMenuVisible(),
                "El menú de perfil debería estar visible tras hacer clic en el ícono de usuario"
        );
        Assertions.assertTrue(
                profilePage.isLogoutButtonVisible(),
                "El botón de cerrar sesión debería ser visible en el menú de perfil"
        );
    }


    @And("hace clic en cerrar sesion")
    public void haceClicEnCerrarSesion() {
        // Act
        profilePage.clickLogout();
    }

    // ─── THEN ─────────────────────────────────────────────────────────────────


    @Then("deberia regresar a la pantalla de login")
    public void deberiaRegresarALaPantallaDeLogin() {
        // Assert
        Assertions.assertTrue(
                profilePage.isLoginScreenDisplayed(),
                "Después del logout, debería mostrarse la pantalla de inicio de sesión"
        );
        Assertions.assertFalse(
                loginPage.isHomePageDisplayed(),
                "La pantalla principal no debería ser visible después del logout"
        );
    }
}
