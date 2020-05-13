import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class NewWindow {
    WebDriver driver;
    WebDriverWait wait;
    final String SITE_URL = "http://the-internet.herokuapp.com/windows";

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
    public void newWindowHandling() {
        driver.findElement(By.linkText("Click Here")).click();

        waitForSecondWindow();

        Set<String> windows = driver.getWindowHandles();

        Iterator<String> itr = windows.iterator();
        String parentWindow = itr.next();
        String childWindow = itr.next();

        driver.switchTo().window(childWindow);

        assertEquals(driver.getTitle(), "New Window");

        driver.switchTo().window(parentWindow);

        assertEquals( driver.getTitle(), "The Internet");

    }

    @Test
    public void newWindowHandlingList() {
        driver.findElement(By.linkText("Click Here")).click();

        waitForSecondWindow();

        Set<String> windows = driver.getWindowHandles();
        List<String> winds = new ArrayList<>(windows);
        String parentWindow = winds.get(0);
        String childWindow = winds.get(1);

        driver.switchTo().window(childWindow);

        assertEquals(driver.getTitle(), "New Window");

        driver.switchTo().window(parentWindow);

        assertEquals( driver.getTitle(), "The Internet");

    }

    public void waitForSecondWindow() {
        wait.until(((ExpectedCondition<Boolean>) d -> driver.getWindowHandles().size() > 1));
    }

}
