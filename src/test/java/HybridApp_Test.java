import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
import java.util.Set;

public class HybridApp_Test {
    public AndroidDriver driver;
   private static By chromeBtn= AppiumBy.accessibilityId("buttonStartWebviewCD");
    private static By homeBtn=By.id("goBack");
    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel 2 XL");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("PlatformVersion", "11");
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("app", System.getProperty("user.dir")+"/apps/selendroid.apk");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }
    private void switchToWebView()
    {
        Set<String> contexts=driver.getContextHandles();
        System.out.println(contexts);
        for(String cont: contexts)
        {

        if (cont.contains("WEBVIEW"))
            {
                System.out.println("found!");
                driver.context(cont);
                break;
            }



    }}

    @Test
    public void HybridTest() {
            driver.switchTo().alert().accept();
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(chromeBtn)).click();
        switchToWebView();
        WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement textBox= driver.findElement(By.id("name_input"));
        textBox.clear();
        textBox.sendKeys("Hello World");
        driver.context("NATIVE_APP");
        driver.findElement(homeBtn).click();




    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {}
        driver.quit();
    }


}
