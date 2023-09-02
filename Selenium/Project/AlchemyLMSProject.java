package LMS;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AlchemyLMSProject {
    WebDriver driver;
    @BeforeMethod
    public void launchapplication() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://alchemy.hguy.co/lms");
    }
    @Test
    public void Activity1() {
        String sTitle = driver.getTitle();
        Assert.assertEquals("Alchemy LMS – An LMS Application", sTitle);

    }
    @Test
    public void Activity2() {
        String sHeadingText = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals("Learn from Industry Experts", sHeadingText);

    }
    @Test
    public void Activity3() {
        List<WebElement> sTitles = driver.findElements(By.xpath("//h3[@class='uagb-ifb-title'][1]"));
        int i=1;
        for (WebElement webElement : sTitles) {
            if(i==1) {
                System.out.println(webElement.getText());
                Assert.assertEquals("Actionable Training", webElement.getText());
            }
            i++;
        }

    }

    @Test
    public void Activity4() {
        List<WebElement> sTitles = driver.findElements(By.xpath("//h3[@class='entry-title']"));
        int i=1;
        for (WebElement webElement : sTitles) {
            if(i==2) {
                System.out.println(webElement.getText());
                Assert.assertEquals("Email Marketing Strategies", webElement.getText());
            }
            i++;
        }

    }

    @Test
    public void Activity5() {
        driver.findElement(By.linkText("My Account")).click();
        String sTitle = driver.getTitle();
        Assert.assertEquals("My Account – Alchemy LMS", sTitle);
    }

    @Test
    public void Activity6() {
        driver.findElement(By.linkText("My Account")).click();
        String sTitle = driver.getTitle();
        Assert.assertEquals("My Account – Alchemy LMS", sTitle);
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("user_login")).sendKeys("root");
        driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
        driver.findElement(By.id("wp-submit")).click();
        WebElement logoutbutton = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logoutbutton.isDisplayed());

    }

    @Test
    public void Activity7() {
        driver.findElement(By.linkText("All Courses")).click();
        List<WebElement> sTitles = driver.findElements(By.xpath("//h3[@class='entry-title']"));
        System.out.println("Total Number of course is: "+sTitles.size());
        for (WebElement webElement : sTitles) {
                System.out.println(webElement.getText());

        }
    }

    @Test
    public void Activity8() {
        driver.findElement(By.linkText("My Account")).click();
        String sTitle = driver.getTitle();
        Assert.assertEquals("My Account – Alchemy LMS", sTitle);
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("user_login")).sendKeys("root");
        driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
        driver.findElement(By.id("wp-submit")).click();
        WebElement logoutbutton = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logoutbutton.isDisplayed());

    }
    @Test
    public void Activity9() {
        driver.findElement(By.linkText("My Account")).click();
        String sTitle = driver.getTitle();
        Assert.assertEquals("My Account – Alchemy LMS", sTitle);
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("user_login")).sendKeys("root");
        driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
        driver.findElement(By.id("wp-submit")).click();
        WebElement logoutbutton = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logoutbutton.isDisplayed());

        driver.findElement(By.linkText("All Courses")).click();
        driver.findElement(By.xpath("//a[text()='See more...'][1]")).click();
        driver.findElement(By.xpath("//*[starts-with(@class,'ld-item-list-item ld-expandable ld-item-lesson-item ld-lesson-item')][1]")).click();
        String coursetitle = driver.findElement(By.xpath("//div[@class='ld-focus-content']//h1")).getText();
        Assert.assertEquals(coursetitle,"Developing Strategy");

        try {
            WebElement markcomplete = driver.findElement(By.xpath("//input[@value='Mark Complete'][1]"));
            if(markcomplete.isDisplayed()) {
                driver.findElement(By.xpath("//input[@value='Mark Complete'][1]")).click();
            }
        }
        catch (Exception  e){
            System.out.println("Course is already completed :"+e);
        }
    }

    @AfterMethod
    public void closebrowser(){
         driver.close();
    }
}
