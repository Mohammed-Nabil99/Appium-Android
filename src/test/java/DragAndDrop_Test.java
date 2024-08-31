import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class DragAndDrop_Test {
    public AndroidDriver driver;
    public AndroidTouchAction action;
    @BeforeTest
    public void setUp() throws MalformedURLException  {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("PlatformVersion", "15.0");
        capabilities.setCapability("app", System.getProperty("user.dir")+"/apps/ApiDemos.apk");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @Test

    public void dragAndDrop(){
        action=new AndroidTouchAction(driver);
       WebElement views= driver.findElement(AppiumBy.accessibilityId("Views"));
       action.tap(ElementOption.element(views)).perform();


                WebElement DragDrop= driver.findElement(AppiumBy.accessibilityId("Drag and Drop"));
        action.tap(ElementOption.element(DragDrop)).perform();

        WebElement source= driver.findElement(By.id("drag_dot_1"));
        WebElement destination=driver.findElement(By.id("drag_dot_2"));

        action.longPress(ElementOption.element(source))
                .waitAction()
                .moveTo(ElementOption.element(destination))
                .release().perform();



    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {}
        driver.quit();
    }

}
