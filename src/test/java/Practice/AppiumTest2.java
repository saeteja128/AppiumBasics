package Practice;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AppiumTest2 {

    @Test
    public void AppiumTest2() throws MalformedURLException {

//        AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("/home/sureshratna/.nvm/versions/node/v20.0.0/bin/appium"))
//                .withIPAddress("127.0.0.1").usingPort(4723).build();

        String appiumJSPath = "/home/sureshratna/.nvm/versions/node/v22.0.0/lib/node_modules/appium/build/lib/appium.js";

        // Define the IP address and port for Appium server
        String ipAddress = "127.0.0.1";
        int port = 4723;

        // Set the path to the Android SDK
        String androidHome = "/home/sureshratna/Android/Sdk";

        // Create a map to store environment variables
        Map<String, String> environment = new HashMap<>();
        environment.put("ANDROID_HOME", androidHome);

        // Create an AppiumServiceBuilder with environment variables
        AppiumServiceBuilder builder = new AppiumServiceBuilder().
                usingDriverExecutable(new File("/home/sureshratna/.nvm/versions/node/v22.0.0/lib/node_modules/appium/build/lib/appium.js"))
                .withAppiumJS(new File(appiumJSPath))
                .withIPAddress(ipAddress)
                .usingPort(port)
                .withEnvironment(environment);

        // Build the AppiumDriverLocalService
        AppiumDriverLocalService service = builder.build();

        service.start();


        String appPath = "home/sureshratna/IdeaProjects/AppiumBasics/src/test/java/resources/ApiDemos-debug.apk";
        // Define Appium server URL
//        URL appiumServerURL = new URL("http://127.0.0.1:4723");

        // Create UiAutomator2Options instance
        UiAutomator2Options options = new UiAutomator2Options();

        // Set desired capabilities
        options.setCapability("deviceName", "TestAppium");
        options.setCapability("app", appPath);

        // Initialize AndroidDriver
        AndroidDriver driver = new AndroidDriver(options);

        // Quit the driver after test execution
        driver.quit();
        service.start();
    }
}
