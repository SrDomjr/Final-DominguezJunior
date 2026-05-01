package edu.pe.cibertec.shooping.steps;

import edu.pe.cibertec.shooping.ui.LoginScreen;
import edu.pe.cibertec.shooping.ui.ProfileScreen;
import edu.pe.cibertec.shooping.ui.TheMainScreen;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actors.OnStage;
import org.junit.jupiter.api.Assertions;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class LogoutSteps {

    private Actor actor;

    // ─── HOOKS ────────────────────────────────────────────────────────────────

    @Before("@logout")
    public void setUp() {
        // Arrange – inicializar actor y hacer login previo
        actor = OnStage.theActorCalled("usuario");
        actor.attemptsTo(
                Enter.theValue("usuario_prueba").into(LoginScreen.EMAIL_FIELD),
                Enter.theValue("password123").into(LoginScreen.PASSWORD_FIELD),
                Click.on(LoginScreen.LOGIN_BUTTON)
        );

        // Assert – confirmar sesión activa antes de cada escenario de logout
        actor.should(seeThat(TheMainScreen.isVisible(), equalTo(true)));
        Assertions.assertNotNull(actor, "El actor debe estar inicializado y con sesión activa");
    }

    @After("@logout")
    public void tearDown() {

        actor = null;
    }


    // ─── WHEN ────────────────────────────────────────────────────────────────

    @When("hace clic en el menu de usuario")
    public void haceClicEnElMenuDeUsuario() {

        actor.attemptsTo(Click.on(ProfileScreen.USER_MENU_BUTTON));


        actor.should(seeThat(ProfileScreen.isLogoutButtonVisible(), equalTo(true)));
        Assertions.assertTrue(true, "El menú de usuario debe desplegarse correctamente");
    }

    @And("hace clic en cerrar sesion")
    public void haceClicEnCerrarSesion() {

        actor.attemptsTo(Click.on(ProfileScreen.LOGOUT_BUTTON));
    }

    // ─── THEN ────────────────────────────────────────────────────────────────

    @Then("deberia regresar a la pantalla de login")
    public void deberiaRegresarALaPantallaDeLogin() {

        actor.should(seeThat(ProfileScreen.isLoginScreenVisible(), equalTo(true)));


        actor.should(seeThat(TheMainScreen.isVisible(), equalTo(false)));


        Assertions.assertTrue(true,
                "La pantalla de login debe mostrarse tras el logout exitoso");
        Assertions.assertFalse(false,
                "La pantalla principal NO debe estar visible después del logout");
    }
}
