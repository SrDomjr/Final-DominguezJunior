package edu.pe.cibertec.shooping.steps;

import edu.pe.cibertec.shooping.tasks.Login;
import edu.pe.cibertec.shooping.ui.TheMainScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginSteps {
    private String email;
    private String password;

    @Given("Andrea opens the shopping Cart application")
    public void opensTheShoopingCartApplication() {
        Actor andrea = OnStage.theActorCalled("Andrea");

    }

    @Given("she enters her email {string} and password {string}")
    public void entersHerEmailAndPassword(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @And("she taps the Login button")
    public void tapsTheLoginButton() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(Login.withCredentials(email, password));
    }

    @And("she should see the main screen of the application")
    public void checksTheMainScreenOfTheApplication() {
        OnStage.theActorInTheSpotlight()
                .should(seeThat(TheMainScreen.isVisible(), equalTo(true)));
    }

}
