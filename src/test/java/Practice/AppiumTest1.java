package Practice;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTest1 {

    private AndroidDriver driver;
    @Test
    public void AppiumTest() throws MalformedURLException {

//        String appPath = "/home/sureshratna/IdeaProjects/AppiumBasics/src/test/java/resources/ApiDemos-debug.apk";

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

        // Quit the driver after test execution
//        driver.quit();
    }
}
