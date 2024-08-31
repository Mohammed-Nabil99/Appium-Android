import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SendMessage_Test {
    public AndroidDriver driver;
    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("PlatformVersion", "9.0");
        capabilities.setCapability("appPackage","com.google.android.apps.messaging");
        capabilities.setCapability("appActivity",".ui.ConversationListActivity");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @Test

    public void sendMessage(){
        driver.sendSMS("16674-626","Hello World!");

    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {}
        driver.quit();
    }
}
