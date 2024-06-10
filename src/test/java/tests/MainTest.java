package tests;

import Practice.AppiumTest1;
import Practice.LE1PointO;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class MainTest extends LE1PointO{

    private LE1PointO le;
    private AppiumTest1 appiumTest;

    @BeforeClass
    public void setUp() {
        le = new LE1PointO(); // Initialize the LE1PointO instance before tests
        appiumTest = new AppiumTest1(); // Initialize the AppiumTest1 instance before tests
    }

    @Test(priority = 1)
    public void RetreivingPollTitle()
    {
        le.InitiatingURL();
        le.LoginAPIResponse();
        le.SlideMenuAPI();
        le.DashboardAPIResponse();
    }

    @Test(priority = 2)
    public void runAppiumTest() throws MalformedURLException {
        appiumTest.AppiumTest();
    }

}
