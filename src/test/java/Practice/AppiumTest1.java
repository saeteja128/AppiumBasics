package Practice;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumTest1 {

    private AndroidDriver driver;
    private WebDriverWait wait;
    private String PollTitle;

    public void PollTitleCheck()
    {
        PollTitle = LE1PointO.title;
        System.out.println("Poll Titile: " +PollTitle);
        if(!PollTitle.equals("null")){
            System.out.println("Initiating Appium Server");
        }
        else {
            tearDown();
        }
    }
    public void initAppium() throws MalformedURLException {

        String appPath = "/home/sureshratna/IdeaProjects/AppiumBasics/src/test/java/resources/SF_STG_4.7.5_209.apk";
        // Define Appium server URL
        URL appiumServerURL = new URL("http://127.0.0.1:4723");
        // Create UiAutomator2Options instance
        UiAutomator2Options options = new UiAutomator2Options();
        // Set desired capabilities
        options.setCapability("deviceName", "TestAppium");
        options.setCapability("app", appPath);
        // Initialize AndroidDriver
        driver = new AndroidDriver(appiumServerURL, options);
    }

    public void Login() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(AppiumBy.accessibilityId("Already have an account? Login now, Button")).click();
        driver.findElement(AppiumBy.id("com.sureify.statefarm.consumer.staging:id/email")).sendKeys("saiteja.peravali+s6@sureify.com");
        driver.findElement(AppiumBy.id("com.sureify.statefarm.consumer.staging:id/password")).sendKeys("Test@123");
        driver.findElement(AppiumBy.accessibilityId("Login, Button")).click();
    }

    public void Dashboard() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.android.permissioncontroller:id/permission_message")));

        driver.findElement((AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.sureify.statefarm.consumer.staging:id/customToolbarTitle")));
    }

    public void findPoll() {

        try {
            // Use WebDriverWait for waiting for elements to be visible and clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(250));
            // Example: Scroll and locate a dynamic element
            // Use a loop to scroll and search for the element
            boolean elementFound = false;
            int maxScrolls = 10; // Define the maximum number of scrolls to avoid infinite loop
            int scrollCount = 0;

            while (!elementFound && scrollCount < maxScrolls) {
                try {
                    // Locate the element using a dynamic strategy
                    wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.sureify.statefarm.consumer.staging:id/memDescription' and @text='"+PollTitle+"']")));
                    elementFound = true;
                    // If element is found, perform actions
                    driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.sureify.statefarm.consumer.staging:id/memDescription' and @text='"+PollTitle+"']")).click();
                } catch (Exception e) {
                    // If element not found, scroll down
                    driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
                    scrollCount++;
                }
            }
            if (!elementFound) {
                System.out.println("Element not found after maximum scrolls");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AnswerigPoll(){
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.sureify.statefarm.consumer.staging:id/ctaTitle")));
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@resource-id='com.sureify.statefarm.consumer.staging:id/ctaRadioOption' and @text='Option One']")).click();
        driver.findElement(AppiumBy.id("com.sureify.statefarm.consumer.staging:id/clickToActionButton")).click();
        WebDriverWait close_button = new WebDriverWait(driver, Duration.ofSeconds(10));
        close_button.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Close']")));
        driver.findElement(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Close']")).click();
        WebDriverWait EarnPoints = new WebDriverWait(driver, Duration.ofSeconds(10));
        EarnPoints.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("EARN POINTS")));
    }

    public void tearDown() {
        driver.quit();
    }


}
