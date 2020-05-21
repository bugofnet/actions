import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class SelectTests {
    WebDriver driver;
    WebDriverWait wait;
    final String SITE_URL = "http://the-internet.herokuapp.com/dropdown";

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

    @Test (priority = 1)
    public void checkThatSelectedElementHasSymbols(){
        Select select = new Select(driver.findElement(By.id("dropdown")));
        WebElement option = select.getFirstSelectedOption();
        assertEquals("Please select an option", option.getText());
    }

    @Test(priority = 2)
    public void checkSelectByValue(){
        Select select = new Select(driver.findElement(By.id("dropdown")));
        select.selectByValue("1");
        WebElement option = select.getFirstSelectedOption();
        assertEquals("Option 1", option.getText());
    }

    @Test(priority = 3)
    public void checkSelectByVisibleText(){
        Select select = new Select(driver.findElement(By.id("dropdown")));
        select.selectByVisibleText("Option 1");
        WebElement option = select.getFirstSelectedOption();
        assertEquals("Option 1", option.getText());
    }

    @Test(priority = 4)
    public void checkSelectByIndex(){
        Select select = new Select(driver.findElement(By.id("dropdown")));
        select.selectByIndex(2);
        WebElement option = select.getFirstSelectedOption();
        assertEquals("Option 2", option.getText());
    }


}
