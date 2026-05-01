package edu.pe.cibertec.shooping.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutScreen {

    public static final Target CART_TAB = Target.the("tab carrito")
            .located(By.xpath("//android.widget.TextView[@text='Carrito']"));

    public static final Target CHECKOUT_BUTTON = Target.the("boton checkout")
            .located(By.xpath("//android.widget.TextView[@text='Proceder al Pago']"));

    public static final Target EMPTY_CART_MESSAGE = Target.the("carrito vacio")
            .located(By.xpath("//android.widget.TextView[@text='Tu carrito está vacío']"));

    public static final Target FIRST_NAME_FIELD = Target.the("campo nombre")
            .located(By.xpath("(//android.widget.EditText)[1]"));

    public static final Target LAST_NAME_FIELD = Target.the("campo apellido")
            .located(By.xpath("(//android.widget.EditText)[2]"));

    public static final Target ADDRESS_FIELD = Target.the("campo direccion")
            .located(By.xpath("(//android.widget.EditText)[3]"));

    public static final Target CITY_FIELD = Target.the("campo ciudad")
            .located(By.xpath("(//android.widget.EditText)[4]"));

    public static final Target ZIP_CODE_FIELD = Target.the("campo codigo postal")
            .located(By.xpath("(//android.widget.EditText)[5]"));

    public static final Target CONTINUE_BUTTON = Target.the("boton continuar")
            .located(By.xpath("//android.widget.TextView[@text='Continuar']"));

    public static final Target CONFIRM_BUTTON = Target.the("boton confirmar")
            .located(By.xpath("//android.widget.TextView[@text='Confirmar Pedido']"));

    public static final Target SUCCESS_MESSAGE = Target.the("mensaje compra exitosa")
            .located(By.xpath("//android.widget.TextView[@text='¡Pedido Confirmado!']"));

    public static final Target ADDRESS_ERROR_MESSAGE = Target.the("error direccion")
            .located(By.xpath("//android.widget.TextView[contains(@text,'requerido') or contains(@text,'obligatorio') or contains(@text,'dirección')]"));
}
