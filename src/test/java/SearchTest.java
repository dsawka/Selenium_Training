import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class SearchTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/damian/IDEA/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchTest() {
        driver.get("http://www.google.com");
        WebElement input = driver.findElement(By.name("q"));
        input.sendKeys("Testowanie Selenium");
        input.submit();

        String title = driver.getTitle();
        System.out.println(title);

        assertEquals("Testowanie Selenium - Google Search", title);
    }

    @Test
    public void searchTest2() {
        driver.get("http://www.bing.com");
        WebElement input = driver.findElement(By.name("q"));
        input.sendKeys("Mistrzostwa Świata w piłce nożnej 2018");
        WebElement search = driver.findElement(By.name("go"));
        search.click();

        WebElement firstLink = driver.findElement(By.partialLinkText("Wikipedia"));
        String firstLinkText = firstLink.getText();
        assertEquals("Mistrzostwa Świata w Piłce Nożnej 2018 – Wikipedia, …", firstLinkText);

        String title = driver.getTitle();
        assertEquals("Mistrzostwa Świata w piłce nożnej 2018 - Bing", title);
    }

}
