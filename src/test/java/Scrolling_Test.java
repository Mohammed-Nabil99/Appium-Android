import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Scrolling_Test {
        public AndroidDriver driver;
        public TouchAction action;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("platformVersion","9.0");
        capabilities.setCapability("appWaitForLaunch", "false");

        capabilities.setCapability("app",System.getProperty("user.dir")+"/apps/ApiDemos.apk");
        driver=new AndroidDriver(new URL("http://localhost:4723/wd/hub"),capabilities);
    }
    private void scrollDown()
    {
        Dimension dimension=driver.manage().window().getSize();
       int scrollStart=(int)(dimension.getHeight()*0.8);
       int scrollEnd=(int)(dimension.getHeight()*0.1);
        action = new TouchAction(driver);

        action.press(PointOption.point(0,scrollStart))
               .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(0,scrollEnd))
                .release().perform();
    }
   @Test
    public void testScrolling(){
        WebElement views= driver.findElement(AppiumBy.accessibilityId("Views"));
        TouchAction  action = new TouchAction(driver);
        action.tap(ElementOption.element(views)).perform();
        //scrollDown();
       WebElement list=driver.findElement(AppiumBy.androidUIAutomator(
               "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                       + "new UiSelector().text(\"Lists\"))"));
       list.click();


//       WebElement Lists= driver.findElement(AppiumBy.accessibilityId("Lists"));
//       action.tap(ElementOption.element(Lists)).perform();
   }
//    public void scrollAndClick(String visibleText) {
//        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
//                ".scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))").click();
//    }

//    @AfterTest
    public void tearDown(){
        if(driver!=null)
        driver.quit();
    }
}
