import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Clipboard_Test {
    public AndroidDriver driver;
    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "S24 Ultra");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("PlatformVersion", "10.0");
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("app", System.getProperty("user.dir")+"/apps/selendroid.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void ClipboardTest() {
        String text="Hello World";
        driver.setClipboardText(text);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));
        String s=wait.until(ExpectedConditions.alertIsPresent()).getText();
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        System.out.println(s);
        WebElement textBox=driver.findElement(AppiumBy.accessibilityId("my_text_fieldCD"));
        textBox.clear();
        textBox.sendKeys(driver.getClipboardText());
        Assert.assertEquals(textBox.getText(),text,"texts aren't equal");



    }

    @AfterTest
    public void tearDown() {
        if (driver != null)
        {driver.quit();}
    }


}
