package Activities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Activity1 {
    AndroidDriver driver;
    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Desired Capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.google.android.apps.tasks");
        options.setAppActivity("com.google.android.apps.tasks.ui.TaskListsActivity");
        options.noReset();

        // Server Address
        URL serverURL = new URL("http://127.0.0.1:4723/wd/hub");

        // Driver Initialization
        driver = new AndroidDriver(serverURL, options);

    }
    @Test
    public void addTaskToGoogleTask() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
        List<String> tasklist=new ArrayList<>();
        tasklist.add("Complete Activity with Google Tasks");
        tasklist.add("Complete Activity with Google Keep");
        tasklist.add("Complete the second Activity Google Keep");

        for (String task:tasklist){
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Create new task")));
            driver.findElement(AppiumBy.accessibilityId("Create new task")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.google.android.apps.tasks:id/add_task_title")));
            driver.findElement(AppiumBy.id("com.google.android.apps.tasks:id/add_task_title")).sendKeys(task);
            driver.findElement(AppiumBy.id("com.google.android.apps.tasks:id/add_task_done")).click();
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Create new task")));
        WebElement parentElement = driver.findElement(AppiumBy.id("com.google.android.apps.tasks:id/tasks_list"));
        List<WebElement> allChildElements = parentElement.findElements(AppiumBy.xpath("//android.view.View[@content-desc=\"Mark as complete\"]"));
        System.out.println("total task is : "+allChildElements.size());
        Assert.assertEquals(allChildElements.size(), 3);
    }

    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }

}
