import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class DragAndDrop {
    WebDriver driver;
    final String SITE_URL = "https://testingcup.pgs-soft.com/task_7";

    @BeforeTest
    public void initialization() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(SITE_URL);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void addProductWithDragAndDrop() {
        WebElement fieldProductNumber = driver.findElement(By.xpath("//h4[text()='Aparat']/following-sibling::div/input"));
        fieldProductNumber.sendKeys("2");

        WebElement productImage = driver.findElement(By.xpath("//div[h4='Aparat']/preceding-sibling::div/img"));
        WebElement basket = driver.findElement(By.cssSelector(".panel-body"));

        Actions action = new Actions(driver);
        action.dragAndDrop(productImage, basket).perform();

        WebElement basketAmountForProduct = driver.findElement(By.xpath("//span[@data-quantity-for='Aparat']"));

        assertEquals("2", basketAmountForProduct.getText());

    }

}
