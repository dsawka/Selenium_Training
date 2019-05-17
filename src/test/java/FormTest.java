import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class FormTest {

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
    public void fillForm() {
        driver.get("https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html");

        WebElement firstName = driver.findElement(By.id("first-name"));
        String nameOfFirstName = firstName.getAttribute("name");
        String firstNameValue = "Karol";
        workWithWebelement(firstName, nameOfFirstName, firstNameValue);
        if (firstName.isDisplayed()) {
            firstName.sendKeys("Karol");
        }

        WebElement lastName = driver.findElement(By.id("last-name"));
        String nameOfLastName = lastName.getAttribute("name");
        String lastNameValue = "Kowalski";
        workWithWebelement(lastName, nameOfLastName, lastNameValue);
        if (lastName.isDisplayed()) {
            lastName.sendKeys("Kowalski");
        }

        List<WebElement> elements = driver.findElements(By.cssSelector(".radio-inline"));
        for (WebElement element : elements) {
            if (element.getText().equals("Female")) {
                element.click();
                break;
            }
        }
//        for (int i = 0; i < elements.size(); i++) {
//            if(elements.get(i).getText().equals("Female")){
//                elements.get(i).click();
//                break;
//            }
//        }
        driver.findElement(By.id("dob")).sendKeys("05/22/2010");
        driver.findElement(By.id("address")).sendKeys("Prosta 51");
        driver.findElement(By.id("email")).sendKeys("karol.kowalski@mailinator.com");
        driver.findElement(By.id("password")).sendKeys("Pass123");
        driver.findElement(By.id("company")).sendKeys("Coders Lab");
        Select roleDropdown = new Select(driver.findElement(By.name("role")));
        roleDropdown.selectByVisibleText("Manager");
        Select jobDropdown = new Select(driver.findElement(By.name("expectation")));
        jobDropdown.selectByVisibleText("Good teamwork");
        driver.findElement(By.xpath("//label[text()='Read books']")).click();
        driver.findElement(By.id("comment")).sendKeys("To jest mój pierwszy automat testowy");
        driver.findElement(By.id("submit")).click();

        String message = driver.findElement(By.id("submit-msg")).getText();
        assertEquals("Successfully submitted!",message);





//        System.out.println(elements.get(0).getText());
//        System.out.println(elements.get(1).getText());
//        System.out.println(elements.get(2).getText());
//        elements.get(0).click();

    }

    @Test
    public void checkErrors() {
        driver.get("https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html");
        driver.findElement(By.id("submit")).click();

//        List<String> listOfIds = getListOfIds();

        List<String> listOfIds = Arrays.asList("first-name", "last-name", "gender");

        int counter = 0;

        for (String elementLocator : listOfIds) {
            elementLocator = elementLocator + "-error";
            assertEquals("This field is required.", driver.findElement(By.id(elementLocator)).getText());
            counter++;
        }
        System.out.println(counter);
    }

    private List<String> getListOfIds() {
        List<String> listID = new ArrayList<String>();
        listID.add("first-name");
        listID.add("last-name");
        listID.add("gender");
        return listID;

    }

    @Test
    public void validateEmail() {
        driver.get("https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html");
        driver.findElement(By.id("email")).sendKeys("123");
        driver.findElement(By.id("password")).click();
        String emailError = driver.findElement(By.id("email-error")).getText();
        assertEquals("Please enter a valid email address.", emailError);
        System.out.println(emailError);

    }

    @Test
    public void validateEmail_2 () {
        driver.get("https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html");
        driver.findElement(By.id("email")).sendKeys("123");
        driver.findElement(By.id("password")).click();

        if (driver.findElement(By.id("email-error")).isEnabled()) {
            String errorMessage = driver.findElement(By.id("email-error")).getText();
            System.out.println(errorMessage);
        }
    }
//        Boolean errorMsg = driver.findElement(By.id("email-error")).isDisplayed();
//        System.out.println(errorMsg);
//        Boolean errorMsg2 = driver.findElement(By.id("email-error")).isEnabled();
//        System.out.println(errorMsg2);

    @Test
    public void testForm () {
        driver.get("https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html");
        driver.findElement(By.id("first-name")).sendKeys("!@#123");
        driver.findElement(By.id("last-name")).sendKeys("!@#123");
        // pola name przyjmują znaki specjalne oraz liczby
        List<WebElement> elements = driver.findElements(By.cssSelector(".radio-inline"));
        for (WebElement element : elements) {
            if (element.getText().equals("Female")) {
                element.click();
                break;
            }
        }
        driver.findElement(By.id("dob")).sendKeys("05/22/2020");
        // możliwość wpisania przyszłej daty urodzenia
        driver.findElement(By.id("address")).sendKeys("!@#");
        // address przyjmuje znaki specjalne
        driver.findElement(By.id("email")).sendKeys("123@123");
        // pole email przujmuje nieprawidłowy format adresu email
        driver.findElement(By.id("password")).sendKeys("!");
        // pole hasło może przyjmować tylko jeden znak
        driver.findElement(By.id("company")).sendKeys("!@#");
        // pole company przyjmuje znaki specjalne
        driver.findElement(By.id("submit")).click();
        // pola "Role", "Ways of development", "Comment" nie są wymagane

        String message = driver.findElement(By.id("submit-msg")).getText();
        assertEquals("Successfully submitted!",message);
    }

    private void workWithWebelement(WebElement webElement, String name, String inputValue) {
        if (webElement.isDisplayed()) {
            webElement.sendKeys(inputValue);
            System.out.println(name + " : " + inputValue);
        }

    }

}