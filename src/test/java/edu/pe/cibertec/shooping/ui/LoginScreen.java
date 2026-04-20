package edu.pe.cibertec.shooping.ui;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class LoginScreen {
    public static final Target EMAIL_FIELD = Target
            .the("email field")
            .located(AppiumBy.xpath("//android.widget.EditText[1]"));


    public static final Target PASSWORD_FIELD = Target
            .the("password field")
            .located(AppiumBy.xpath("//android.widget.EditText[2]"));


    public static final Target LOGIN_BUTTON = Target
            .the("login button")
            .located(AppiumBy.xpath("//android.widget.Button[1]"));
}
