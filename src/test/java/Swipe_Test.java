import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Swipe_Test {
    public AndroidDriver driver;
    public AndroidTouchAction action;
    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("PlatformVersion", "9.0");
        capabilities.setCapability("app", System.getProperty("user.dir")+"/apps/ApiDemos.apk");
        driver=new AndroidDriver(new URL("http://localhost:4723/wd/hub"),capabilities);
    }

    @Test

    public void swipe(){

        action=new AndroidTouchAction(driver);
        WebElement views= driver.findElement(AppiumBy.accessibilityId("Views"));
        action.tap(ElementOption.element(views)).perform();


        WebElement gallery=driver.findElement(AppiumBy.accessibilityId("Gallery"));
        action.tap(ElementOption.element(gallery)).perform();

        WebElement photos= driver.findElement(AppiumBy.accessibilityId("1. Photos"));
        action.tap(ElementOption.element(photos)).perform();

        WebElement firstPhoto= driver.findElements(By.className("android.widget.ImageView")).getFirst();
        Dimension dimension =driver.manage().window().getSize();


        action.press(ElementOption.element(firstPhoto))
                .waitAction()
                .moveTo(PointOption.point(-300,210))
                .release().perform();



    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {}
        driver.quit();
    }

}
