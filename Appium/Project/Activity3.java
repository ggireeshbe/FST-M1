package Activities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;


public class Activity3 {
    // Driver Declaration
    AndroidDriver driver;
    WebDriverWait wait;

    // Set up method
    @BeforeClass
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.noReset();
        URL serverURL = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        driver.get("https://v1.training-support.net/selenium");
    }

    @Test
    public void todolistvalidation()  {
        String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.className("android.widget.TextView")));

        driver.findElement(AppiumBy.androidUIAutomator(UiScrollable + ".flingForward()"));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='To-Do List']")).click();
        wait.until((ExpectedConditions.visibilityOfElementLocated(AppiumBy.className("android.widget.EditText"))));
        driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys("Add tasks to list");
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Add Task']")).click();
        driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys("Get number of tasks");
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Add Task']")).click();
        driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys("Clear the list");
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Add Task']")).click();

        try {
            WebElement parentElement = driver.findElement(AppiumBy.androidUIAutomator("resourceId(\"tasksCard\")"));
            List<WebElement> tasklistElement = parentElement.findElements(AppiumBy.androidUIAutomator("resourceId(\"tasksList\")"));
            System.out.println(tasklistElement.size());
            System.out.println("Task List as been added success");
            Assert.assertEquals(tasklistElement.size(),1);

        }
        catch(Exception  e){
            System.out.println("Task List creation failed");
        }

        //clicking clear list button
        driver.findElement(AppiumBy.xpath("//android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[3]")).click();

        try {
            WebElement parentElement = driver.findElement(AppiumBy.androidUIAutomator("resourceId(\"tasksCard\")"));
            List<WebElement> tasklistElement = parentElement.findElements(AppiumBy.androidUIAutomator("resourceId(\"tasksList\")"));
            System.out.println(tasklistElement.size());
            Assert.assertEquals(tasklistElement.size(),0);
            System.out.println("Task List Clear success");
        }
        catch(Exception  e){
           if(String.valueOf(e).contains("An element could not be located"))
           {
               Assert.assertTrue(true);
               System.out.println("Task List Clear success");
           }
           else
           {
               Assert.assertTrue(false);
               System.out.println("Task List clear failed");
           }
        }
    }
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }


}