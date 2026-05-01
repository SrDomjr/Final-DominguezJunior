package edu.pe.cibertec.shooping.hooks;


import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class AppiumHooks {

    private AndroidDriver driver;

    @Before(order = 1)
    public void setUpStage() throws MalformedURLException {
        // ── Capabilities ──────────────────────────────────────────────────
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName",          "Android");
        caps.setCapability("appium:deviceName",     "emulator-5554");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:appPackage",     "edu.pe.cibertec.shooping_cart_appium_demo");
        caps.setCapability("appium:appActivity",    "edu.pe.cibertec.shooping_cart_appium_demo.MainActivity");
        caps.setCapability("appium:noReset",        true);   // true = usa el APK ya instalado


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);


        OnStage.setTheStage(Cast.whereEveryoneCan(BrowseTheWeb.with(driver)));
    }

    @After(order = 1)
    public void tearDownStage() {
        OnStage.drawTheCurtain();   // limpia el Stage de Serenity
        if (driver != null) {
            driver.quit();
        }
    }
}