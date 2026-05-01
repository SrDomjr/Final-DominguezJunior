package edu.pe.cibertec.shooping.steps;

import edu.pe.cibertec.shooping.ui.LoginScreen;
import edu.pe.cibertec.shooping.ui.TheMainScreen;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actors.OnStage;
import org.junit.jupiter.api.Assertions;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;


public class CommonSteps {

    @Given("que el usuario esta logueado en la aplicacion")
    public void elUsuarioEstaLogueado() {
        // Arrange
        Actor actor = OnStage.theActorCalled("usuario");
        Assertions.assertNotNull(actor, "El actor no debe ser nulo");

        // Act – login con credenciales válidas
        actor.attemptsTo(
                Enter.theValue("usuario@prueba.com").into(LoginScreen.EMAIL_FIELD),
                Enter.theValue("password123").into(LoginScreen.PASSWORD_FIELD),
                Click.on(LoginScreen.LOGIN_BUTTON)
        );

        // Assert – pantalla principal visible
        actor.should(seeThat(TheMainScreen.isVisible(), equalTo(true)));
        Assertions.assertTrue(true, "El usuario debe haber accedido a la pantalla principal");
    }
}
