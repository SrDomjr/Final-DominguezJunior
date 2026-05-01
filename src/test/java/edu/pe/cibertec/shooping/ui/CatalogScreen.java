package edu.pe.cibertec.shooping.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CatalogScreen {

    public static final Target CATALOG_TAB = Target.the("tab inicio catalogo")
            .located(By.xpath("//android.widget.TextView[@text='Inicio']"));

    public static final Target CATALOG_TITLE = Target.the("titulo productos")
            .located(By.xpath("//android.widget.TextView[@text='Productos']"));

    public static final Target SEARCH_FIELD = Target.the("campo buscar productos")
            .located(By.xpath("//android.widget.EditText[contains(@text,'Buscar')]"));

    public static final Target SEARCH_BUTTON = Target.the("boton buscar")
            .located(By.xpath("//android.widget.EditText[contains(@text,'Buscar')]"));

    public static final Target PRODUCT_LIST = Target.the("lista de productos")
            .located(By.xpath("//android.widget.TextView[@text='Productos']"));

    public static final Target CATEGORY_SPINNER = Target.the("filtro electronica")
            .located(By.xpath("//android.widget.TextView[@text='Electrónica']"));

    public static Target categoryOption(String categoryName) {
        return Target.the("opcion categoria " + categoryName)
                .located(By.xpath("//android.widget.TextView[@text='" + categoryName + "']"));
    }
}
