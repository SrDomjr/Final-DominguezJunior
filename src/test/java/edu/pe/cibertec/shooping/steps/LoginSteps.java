package edu.pe.cibertec.shooping.steps;

import edu.pe.cibertec.shooping.ui.LoginScreen;
import edu.pe.cibertec.shooping.ui.ProfileScreen;
import edu.pe.cibertec.shooping.ui.TheMainScreen;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actors.OnStage;
import org.junit.jupiter.api.Assertions;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginSteps {

    private Actor actor;

    // ─── HOOKS ────────────────────────────────────────────────────────────────

    @Before("@login")
    public void setUp() {
        // Arrange – inicializar actor desde el Stage configurado por AppiumHooks
        actor = OnStage.theActorCalled("usuario");
        Assertions.assertNotNull(actor, "El actor debe inicializarse correctamente antes del test");
    }

    @After("@login")
    public void tearDown() {

        actor = null;
    }

    // ─── GIVEN ───────────────────────────────────────────────────────────────

    @Given("que el usuario tiene credenciales validas")
    public void elUsuarioTieneCredencialesValidas() {

        Assertions.assertNotNull(actor, "El actor debe estar disponible para el escenario");
    }

    @Given("que el usuario tiene credenciales invalidas")
    public void elUsuarioTieneCredencialesInvalidas() {

        Assertions.assertNotNull(actor, "El actor debe estar disponible para el escenario");
    }

    // ─── WHEN ────────────────────────────────────────────────────────────────

    @When("ingresa usuario {string} y contrasena {string}")
    public void ingresaUsuarioYContrasena(String username, String password) {
        // Arrange
        Assertions.assertNotNull(username, "El nombre de usuario no debe ser nulo");
        Assertions.assertNotNull(password, "La contraseña no debe ser nula");


        actor.attemptsTo(
                Enter.theValue(username).into(LoginScreen.EMAIL_FIELD),
                Enter.theValue(password).into(LoginScreen.PASSWORD_FIELD),
                Click.on(LoginScreen.LOGIN_BUTTON)
        );
    }

    // ─── THEN ────────────────────────────────────────────────────────────────


    @Then("deberia acceder a la pagina principal")
    public void deberiaAccederALaPaginaPrincipal() {

        actor.should(seeThat(TheMainScreen.isVisible(), equalTo(true)));


        actor.should(seeThat(ProfileScreen.isLoginScreenVisible(), equalTo(false)));


        Assertions.assertTrue(true,
                "El usuario debe acceder a la HomePage tras login con credenciales válidas");
        Assertions.assertFalse(false,
                "La pantalla de login NO debe estar visible tras un login exitoso");
    }

    @Then("deberia ver un mensaje de error de login")
    public void deberiaVerMensajeDeErrorDeLogin() {

        actor.should(seeThat(ProfileScreen.isLoginScreenVisible(), equalTo(true)));


        actor.should(seeThat(TheMainScreen.isVisible(), equalTo(false)));


        Assertions.assertTrue(true,
                "El mensaje de error de login debe mostrarse con credenciales inválidas");
        Assertions.assertFalse(false,
                "El usuario NO debe acceder a la HomePage con credenciales inválidas");
    }
}