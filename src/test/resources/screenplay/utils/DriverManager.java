package screenplay.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * Clase utilitaria para gestionar la creación y configuración
 * del AndroidDriver de Appium.
 *
 * Centraliza la configuración de las Desired Capabilities para
 * que todos los Steps usen la misma configuración del driver.
 */
public class DriverManager {

    // ─── CONSTANTES DE CONFIGURACIÓN ─────────────────────────────────────────

    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    private static final String APP_PACKAGE       = "com.example.shoppingcart";
    private static final String APP_ACTIVITY      = ".MainActivity";
    private static final String DEVICE_NAME        = "emulator-5554";
    private static final String PLATFORM_VERSION   = "13.0";

    // ─── CONSTRUCTOR PRIVADO ──────────────────────────────────────────────────

    private DriverManager() {
        // Clase utilitaria – no instanciar
    }

    // ─── MÉTODOS PÚBLICOS ─────────────────────────────────────────────────────

    /**
     * Crea y retorna una nueva instancia de AndroidDriver configurada
     * para la aplicación Shopping Cart en el emulador Android.
     *
     * @return AndroidDriver listo para usar en los tests
     * @throws RuntimeException si no se puede conectar al servidor Appium
     */
    public static AndroidDriver createDriver() {
        try {
            UiAutomator2Options options = new UiAutomator2Options();

            // Plataforma
            options.setPlatformName("Android");
            options.setPlatformVersion(PLATFORM_VERSION);
            options.setDeviceName(DEVICE_NAME);

            // Aplicación
            options.setAppPackage(APP_PACKAGE);
            options.setAppActivity(APP_ACTIVITY);

            // Comportamiento de la app
            options.setNoReset(false);       // Resetear la app en cada ejecución
            options.setFullReset(false);     // No reinstalar la APK
            options.setAutoGrantPermissions(true); // Aceptar permisos automáticamente

            // Timeouts
            options.setNewCommandTimeout(Duration.ofSeconds(60));
            options.setImplicitWaitTimeout(Duration.ofSeconds(10));

            AndroidDriver driver = new AndroidDriver(
                    new URL(APPIUM_SERVER_URL),
                    options
            );

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            return driver;

        } catch (MalformedURLException e) {
            throw new RuntimeException(
                    "URL del servidor Appium inválida: " + APPIUM_SERVER_URL, e
            );
        }
    }
}
