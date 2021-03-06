import org.junit.After;
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

        WebElement lastName = driver.findElement(By.id("last-name"));
        String nameOfLastName = lastName.getAttribute("name");
        String lastNameValue = "Kowalski";
        workWithWebelement(lastName, nameOfLastName, lastNameValue);

        List<WebElement> elements = driver.findElements(By.cssSelector(".radio-inline"));
        //css selektor(.radio-inline) wyekstraktuj do stałej
        for (WebElement element : elements) {
            if (element.getText().equals("Female")) {
                element.click();
                break;
            }
        }

//          zamiast pętli for each, zwykły for
//        for (int i = 0; i < elements.size(); i++) {
//            if(elements.get(i).getText().equals("Female")){
//                elements.get(i).click();
//                break;
//            }
//        }

        driver.findElement(By.id("dob")).sendKeys("05/22/2010");

        WebElement address = driver.findElement(By.id("address"));
        String nameAddress = address.getAttribute("name");
        String addressValue = "Prosta 51";
        workWithWebelement(address, nameAddress, addressValue);


        WebElement email = driver.findElement(By.id("email"));
        String nameEmail = email.getAttribute("name");
        String emailValue = "karol.kowalski@mailinator.com";
        workWithWebelement(email, nameEmail, emailValue);

        WebElement password = driver.findElement(By.id("password"));
        String passwordName = password.getAttribute("name");
        String passwordValue = "Pass123";
        workWithWebelement(password, passwordName, passwordValue);
        if (password.isDisplayed()) {
            password.sendKeys("Pass123");
        }

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
        // możliwość wpisania przyszłej daty urodzeniagit
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
            // generalna UWAGA - wszystie stringi mają być wyekstraktowane do stałych
            // dorób metodę podobną do workWithWebElement(), tylko dla dropDownów
            // metody nie mogą się nazywać np. validateEmail_2 :-)
}
