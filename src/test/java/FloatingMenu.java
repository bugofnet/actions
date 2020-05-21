import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FloatingMenu {
    WebDriver driver;
    WebDriverWait wait;
    final String SITE_URL = "http://the-internet.herokuapp.com/floating_menu";

    @BeforeTest
    public void initialization() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(SITE_URL);
        wait = new WebDriverWait(driver, 10);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void  floatingMenuTest(){
        int i=1;
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        //it will scroll to the bottom
        jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");

        i=1;
    }
}
