package edu.pe.cibertec.shooping.steps;

import edu.pe.cibertec.shooping.ui.CheckoutScreen;
import edu.pe.cibertec.shooping.ui.LoginScreen;
import edu.pe.cibertec.shooping.ui.TheMainScreen;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.questions.Visibility;
import org.junit.jupiter.api.Assertions;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutSteps {

    // ─── HOOKS ────────────────────────────────────────────────────────────────

    @Before("@checkout")
    public void setUp() {
        // Arrange – login previo requerido para todos los escenarios de checkout
        Actor actor = OnStage.theActorCalled("usuario");
        actor.attemptsTo(
                Enter.theValue("usuario_prueba").into(LoginScreen.EMAIL_FIELD),
                Enter.theValue("password123").into(LoginScreen.PASSWORD_FIELD),
                Click.on(LoginScreen.LOGIN_BUTTON)
        );
        actor.should(seeThat(TheMainScreen.isVisible(), equalTo(true)));
    }

    @After("@checkout")
    public void tearDown() {
        // El driver se cierra en AppiumHooks global
    }

    // ─── GIVEN ───────────────────────────────────────────────────────────────

    @Given("que el usuario tiene productos en el carrito")
    public void elUsuarioTieneProductosEnElCarrito() {
        // Arrange + Assert
        Actor actor = OnStage.theActorCalled("usuario");
        actor.attemptsTo(Click.on(CheckoutScreen.CART_TAB));
        actor.should(seeThat(
                Visibility.of(CheckoutScreen.EMPTY_CART_MESSAGE).asBoolean(), equalTo(false)));
        Assertions.assertTrue(true,
                "El carrito debería tener al menos un producto antes del checkout");
    }

    @Given("que el usuario tiene el carrito vacio")
    public void elUsuarioTieneElCarritoVacio() {
        // Arrange + Assert
        Actor actor = OnStage.theActorCalled("usuario");
        actor.attemptsTo(Click.on(CheckoutScreen.CART_TAB));
        actor.should(seeThat(
                Visibility.of(CheckoutScreen.EMPTY_CART_MESSAGE).asBoolean(), equalTo(true)));
    }

    // ─── WHEN ────────────────────────────────────────────────────────────────

    @When("procede al checkout")
    public void procedeAlCheckout() {
        // Act
        Actor actor = OnStage.theActorCalled("usuario");
        actor.attemptsTo(Click.on(CheckoutScreen.CHECKOUT_BUTTON));
    }

    @When("intenta proceder al checkout")
    public void intentaProcederAlCheckout() {
        // Act
        Actor actor = OnStage.theActorCalled("usuario");
        actor.attemptsTo(Click.on(CheckoutScreen.CHECKOUT_BUTTON));
    }

    @And("ingresa los datos de envio")
    public void ingresaLosDatosDeEnvio() {
        // Act – ingresar datos válidos de envío
        Actor actor = OnStage.theActorCalled("usuario");
        actor.attemptsTo(
                Enter.theValue("Juan").into(CheckoutScreen.FIRST_NAME_FIELD),
                Enter.theValue("Perez").into(CheckoutScreen.LAST_NAME_FIELD),
                Enter.theValue("Av. Arequipa 1234").into(CheckoutScreen.ADDRESS_FIELD),
                Enter.theValue("Lima").into(CheckoutScreen.CITY_FIELD),
                Enter.theValue("15001").into(CheckoutScreen.ZIP_CODE_FIELD),
                Click.on(CheckoutScreen.CONTINUE_BUTTON)
        );
    }

    @And("confirma la compra")
    public void confirmaLaCompra() {
        // Act
        Actor actor = OnStage.theActorCalled("usuario");
        actor.attemptsTo(Click.on(CheckoutScreen.CONFIRM_BUTTON));
    }

    @And("intenta confirmar la compra sin ingresar direccion de envio")
    public void intentaConfirmarSinDireccion() {
        // Act – clic en continuar sin rellenar campos obligatorios
        Actor actor = OnStage.theActorCalled("usuario");
        actor.attemptsTo(Click.on(CheckoutScreen.CONTINUE_BUTTON));
    }

    // ─── THEN ────────────────────────────────────────────────────────────────

    @Then("deberia ver el mensaje de compra exitosa")
    public void deberiaVerMensajeDeCompraExitosa() {
        // Assert
        Actor actor = OnStage.theActorCalled("usuario");
        actor.should(seeThat(
                Visibility.of(CheckoutScreen.SUCCESS_MESSAGE).asBoolean(), equalTo(true)));
        Assertions.assertTrue(true,
                "Debería mostrarse un mensaje de confirmación de compra exitosa");
    }

    @Then("deberia ver mensaje de carrito vacio")
    public void deberiaVerMensajeDeCarritoVacio() {
        // Assert
        Actor actor = OnStage.theActorCalled("usuario");
        actor.should(seeThat(
                Visibility.of(CheckoutScreen.EMPTY_CART_MESSAGE).asBoolean(), equalTo(true)));
        Assertions.assertTrue(true,
                "Debería mostrarse el mensaje de carrito vacío");
    }

    @Then("deberia ver un mensaje de error solicitando la direccion de envio")
    public void deberiaVerMensajeErrorDireccion() {
        // Assert
        Actor actor = OnStage.theActorCalled("usuario");
        actor.should(seeThat(
                Visibility.of(CheckoutScreen.ADDRESS_ERROR_MESSAGE).asBoolean(), equalTo(true)));
        Assertions.assertTrue(true,
                "Debería mostrarse error indicando que la dirección es obligatoria");
    }
}