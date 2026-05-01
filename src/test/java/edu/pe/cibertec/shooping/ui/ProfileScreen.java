package edu.pe.cibertec.shooping.ui;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProfileScreen {

    public static final Target USER_MENU_BUTTON = Target.the("tab perfil")
            .located(By.xpath("//android.widget.TextView[@text='Perfil']"));

    public static final Target LOGOUT_BUTTON = Target.the("boton cerrar sesion")
            .located(By.xpath("//android.widget.TextView[@text='Cerrar Sesión']"));

    public static final Target LOGIN_SCREEN_INDICATOR = Target.the("pantalla login")
            .located(By.xpath("//android.widget.TextView[@text='Shopping Cart']"));

    public static Question<Boolean> isLogoutButtonVisible() {
        return Visibility.of(LOGOUT_BUTTON).asBoolean();
    }

    public static Question<Boolean> isLoginScreenVisible() {
        return Visibility.of(LOGIN_SCREEN_INDICATOR).asBoolean();
    }
}
