package edu.pe.cibertec.shooping.ui;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.questions.Visibility;
import org.openqa.selenium.By;

public class TheMainScreen {

    public static final Target PRODUCTOS_TITLE = Target.the("titulo productos")
            .located(By.xpath("//android.widget.TextView[@text='Productos']"));

    public static final Target INICIO_TAB = Target.the("tab inicio")
            .located(By.xpath("//android.widget.TextView[@text='Inicio']"));

    public static Question<Boolean> isVisible() {
        return Visibility.of(PRODUCTOS_TITLE).asBoolean();
    }
}
