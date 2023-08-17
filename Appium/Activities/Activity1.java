package Activities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class Activity1 {
    // Driver Declaration
    AndroidDriver driver;

    // Set up method
    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Desired Capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.sec.android.app.popupcalculator");
        options.setAppActivity("com.sec.android.app.popupcalculator.Calculator");
        options.noReset();

        // Server Address
        URL serverURL = new URL("http://127.0.0.1:4723/wd/hub");

        // Driver Initialization
        driver = new AndroidDriver(serverURL, options);
    }

    // Test method
    @Test
    public void multiplyTest() {
        // Perform the calculation
        driver.findElement(AppiumBy.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_05")).click();
        driver.findElement(AppiumBy.accessibilityId("Multiplication")).click();
        driver.findElement(AppiumBy.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_08")).click();
        driver.findElement(AppiumBy.accessibilityId("Equal")).click();

        // Find the result
        String result = driver.findElement(AppiumBy.id("com.sec.android.app.popupcalculator:id/calc_edt_formula")).getText();

        // Assertion
        Assert.assertEquals(result, "40 Calculation result");
    }


    // Tear down method
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }
}