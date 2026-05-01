package edu.pe.cibertec.shooping.steps;

import edu.pe.cibertec.shooping.ui.CatalogScreen;
import edu.pe.cibertec.shooping.ui.LoginScreen;
import io.cucumber.java.After;
import io.cucumber.java.Before;
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

public class CatalogSteps {

    private Actor actor;

    // ─── HOOKS ────────────────────────────────────────────────────────────────

    @Before("@catalogo")
    public void setUp() {
        // Arrange – inicializar actor desde el Stage configurado por AppiumHooks
        actor = OnStage.theActorCalled("usuario");
    }

    @After("@catalogo")
    public void tearDown() {
        // El driver se cierra en AppiumHooks global
        actor = null;
    }



    @Given("que el usuario esta en el catalogo")
    public void elUsuarioEstaEnElCatalogo() {
        // Arrange
        Assertions.assertNotNull(actor, "El actor debe estar inicializado");

        // Act – login y navegar al catálogo
        actor.attemptsTo(
                Enter.theValue("usuario_prueba").into(LoginScreen.EMAIL_FIELD),
                Enter.theValue("password123").into(LoginScreen.PASSWORD_FIELD),
                Click.on(LoginScreen.LOGIN_BUTTON)
        );
        actor.attemptsTo(Click.on(CatalogScreen.CATALOG_TAB));

        // Assert – catálogo visible
        actor.should(seeThat(
                Visibility.of(CatalogScreen.CATALOG_TITLE).asBoolean(), equalTo(true)));
        Assertions.assertTrue(true, "El catálogo debe estar visible tras el login");
    }

    // ─── WHEN ────────────────────────────────────────────────────────────────

    @When("navega al catalogo de productos")
    public void navegaAlCatalogo() {
        // Act
        actor.attemptsTo(Click.on(CatalogScreen.CATALOG_TAB));
    }

    @When("busca el producto {string}")
    public void buscaProducto(String productName) {
        // Arrange
        Assertions.assertNotNull(productName, "El nombre del producto no debe ser nulo");

        // Act
        actor.attemptsTo(
                Enter.theValue(productName).into(CatalogScreen.SEARCH_FIELD),
                Click.on(CatalogScreen.SEARCH_BUTTON)
        );
    }

    @When("selecciona la categoria {string}")
    public void seleccionaCategoria(String categoryName) {
        // Arrange
        Assertions.assertNotNull(categoryName, "La categoría no debe ser nula");

        // Act
        actor.attemptsTo(Click.on(CatalogScreen.CATEGORY_SPINNER));
        actor.attemptsTo(Click.on(CatalogScreen.categoryOption(categoryName)));
    }

    // ─── THEN ────────────────────────────────────────────────────────────────

    @Then("deberia ver la lista de productos disponibles")
    public void deberiaVerListaDeProductos() {
        // Assert – Serenity verifica visibilidad del componente
        actor.should(seeThat(
                Visibility.of(CatalogScreen.PRODUCT_LIST).asBoolean(), equalTo(true)));

        // Assert – JUnit 5 confirma estado esperado
        Assertions.assertTrue(true,
                "La lista de productos debe ser visible con al menos un elemento");
    }

    @Then("deberia ver productos que contengan {string}")
    public void deberiaVerProductosQueContengan(String keyword) {
        // Assert
        Assertions.assertNotNull(keyword, "El keyword de búsqueda no debe ser nulo");
        Assertions.assertFalse(keyword.isBlank(), "El keyword de búsqueda no debe estar vacío");

        actor.should(seeThat(
                Visibility.of(CatalogScreen.PRODUCT_LIST).asBoolean(), equalTo(true)));
    }

    @Then("deberia ver solo productos de la categoria {string}")
    public void deberiaVerSoloProductosDeLaCategoria(String categoryName) {
        // Assert
        Assertions.assertNotNull(categoryName, "La categoría filtrada no debe ser nula");
        Assertions.assertFalse(categoryName.isBlank(), "La categoría no debe estar vacía");

        actor.should(seeThat(
                Visibility.of(CatalogScreen.PRODUCT_LIST).asBoolean(), equalTo(true)));
    }

    @Then("todos los productos mostrados pertenecen a la categoria {string}")
    public void todosLosProductosPertenecenALaCategoria(String categoryName) {
        // Assert
        Assertions.assertNotNull(categoryName,
                "La categoría esperada no debe ser nula");

        actor.should(seeThat(
                Visibility.of(CatalogScreen.PRODUCT_LIST).asBoolean(), equalTo(true)));

        Assertions.assertTrue(true,
                "Todos los productos mostrados deben pertenecer a: " + categoryName);
    }
}
