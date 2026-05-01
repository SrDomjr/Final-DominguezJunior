package edu.pe.cibertec.shooping.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginScreen {


    public static final Target EMAIL_FIELD = Target.the("campo email")
            .located(By.xpath("//android.widget.EditText[.//android.view.View[@content-desc='Email']]"));


    public static final Target PASSWORD_FIELD = Target.the("campo password")
            .located(By.xpath("//android.widget.EditText[.//android.view.View[@content-desc='Password']]"));


    public static final Target LOGIN_BUTTON = Target.the("boton iniciar sesion")
            .located(By.xpath("//android.view.View[.//android.widget.TextView[@text='Iniciar Sesión']]"));


    public static final Target ERROR_MESSAGE = Target.the("mensaje de error login")
            .located(By.xpath("//android.widget.TextView[@text='Formato de email inválido']"));


    public static final Target LOGIN_SCREEN_TITLE = Target.the("titulo login")
            .located(By.xpath("//android.widget.TextView[@text='Shopping Cart']"));
}
