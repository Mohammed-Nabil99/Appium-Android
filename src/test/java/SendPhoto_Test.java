import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class SendPhoto_Test {
    public AndroidDriver driver;
    private By outSide=By.id("touch_outside");
    private By backuoBtn=By.id("auto_backup_switch");
    private By keepoffBtn=By.xpath("//android.widget.Button[@resource-id=\'android:id/button2\']");
    private By photo=By.xpath("//android.widget.ImageView[contains(@content-desc,'Photo taken on')]");
    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("PlatformVersion", "9.0");
        capabilities.setCapability("appPackage","com.google.android.apps.photos");
        capabilities.setCapability("appActivity",".home.HomeActivity");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @Test

    public void sendPhoto() throws IOException {
       File classPath= new File(System.getProperty("user.dir"));
       File imageDir= new File(classPath,"/images");
       File img= new File(imageDir.getCanonicalFile(),"img.png");
       File androidPath= new File("mnt/sdcard/Pictures");

       WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(backuoBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(outSide)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(keepoffBtn)).click();

        driver.pushFile(androidPath+"/"+img.getName(),img);
        wait.until(ExpectedConditions.numberOfElementsToBe(photo,1));


    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {}
        driver.quit();
    }

}
