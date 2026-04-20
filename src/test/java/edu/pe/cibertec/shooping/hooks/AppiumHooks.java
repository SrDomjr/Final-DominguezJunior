package edu.pe.cibertec.shooping.hooks;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.time.Duration;

public class AppiumHooks {

    private static AndroidDriver driver;
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    @Before
    public void setUp() throws MalformedInputException, MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554")
                .setAppPackage("edu.pe.cibertec.shooping_cart_appium_demo")
                .setAppActivity("edu.pe.cibertec.shooping_cart_appium_demo.MainActivity")
                .setNoReset(false)
                .setNewCommandTimeout(Duration.ofSeconds(120));

        driver = new AndroidDriver(options);

        OnStage.setTheStage(Cast.whereEveryoneCan(actor -> actor.whoCan(
                BrowseTheWeb.with(driver)
        )));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        OnStage.drawTheCurtain();
    }

    public static AndroidDriver getDriver() {
        return driver;
    }
}
