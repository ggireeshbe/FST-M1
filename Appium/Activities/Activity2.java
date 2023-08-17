package Activities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Activity2 {
    // Driver Declaration
    AndroidDriver driver;

    // Set up method
    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Desired Capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.noReset();

        // Server Address
        URL serverURL = new URL("http://127.0.0.1:4723/wd/hub");

        // Driver Initialization
        driver = new AndroidDriver(serverURL, options);

        // Open the page in Chrome
        driver.get("https://v1.training-support.net/");
    }

    // Test method
    @Test
    public void chromeTest() {
        // Find heading on the page
        // Create object for WebDriverWait class

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));

//Use ExpectedConditions helper methods to wait for elements
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Training Support']")));
        String pageHeading = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Training Support']")).getText();

        // Print to console
        System.out.println("Heading: " + pageHeading);

        // Find and click the About Us link
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='About Us']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='/* Coming Soon */']")));
        // Find heading of new page and print to console
        String aboutPageHeading = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='About Us']")).getText();
        System.out.println(aboutPageHeading);
    }


    // Tear down method
    @AfterClass
    public void tearDown() {
        // Close the app
      //  driver.quit();
    }
}