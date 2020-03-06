import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class MouseMove {
    WebDriver driver;
    WebDriverWait wait;
    final String SITE_URL = "http://automationpractice.com/index.php";


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
    public void moveMouseToWomenMenuAndGoToTopsCategory() {
        WebElement womenLink = driver.findElement(By.xpath("//a[text()='Women']"));

        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(womenLink));

        actions.moveToElement(womenLink).perform();

        WebElement topLink = driver.findElement(By.xpath("//a[text()='Tops']"));
        wait.until(ExpectedConditions.visibilityOf(topLink));
        actions.moveToElement(topLink).click().perform();

        WebElement topLabel = driver.findElement(By.xpath("//span[@class='cat-name']"));
        wait.until(ExpectedConditions.visibilityOf(topLabel));
        assertEquals("TOPS ", topLabel.getText());
    }

}
