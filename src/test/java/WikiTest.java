import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WikiTest {


    private static final String LOCATOR = "main-page-column1";
    private static final String MAIN_PAGE_CONTENT = "main-page-content";
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/damian/IDEA/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://pl.wikipedia.org/");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void getTextFromWebelementTest() {
        String text = driver.findElement(By.id(LOCATOR)).getText();
        System.out.println(text);
    }

    @Test
    public void moreFlexibleWay() {
        getTextFromElement(LOCATOR);
    }

    @Test
    public void getAttribute() {
        String attribute = driver.findElement(By.id(MAIN_PAGE_CONTENT)).getAttribute("class");
        System.out.println(attribute);
    }

    private void getTextFromElement(String locator) {
       WebElement element = driver.findElement(By.id(locator));
        System.out.println(element.getText());
    }

    @Test
    public void getTagName() {
        String tagName = driver.findElement(By.id(MAIN_PAGE_CONTENT)).getTagName();
        System.out.println(tagName);

        assertEquals("div", tagName);
        assertTrue(tagName.equals("div"));
        assertThat(tagName, equalTo("div"));
    }

    @Test
    public void getCssValue() {
        String cssValue = driver.findElement(By.id(MAIN_PAGE_CONTENT)).getCssValue("font-size");
        System.out.println(cssValue);
    }

    @Test
    public void isEnabledCheck() {

        WebElement searchInput = driver.findElement(By.id("searchInput"));
        if (searchInput.isEnabled()) {
            searchInput.sendKeys("Selenium");
            searchInput.submit();
        } else {
            Assert.fail();
        }

    }

    @Test
    public void waitSample() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("searchInput")));
        searchInput.sendKeys("Selenium");
        searchInput.submit();
    }

}



