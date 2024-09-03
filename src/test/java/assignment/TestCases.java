package assignment;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;


public class TestCases {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    // Setup method to initialize the WebDriver and browser settings
    public void setup() {
    	
        // Launch the browser
        driver = new ChromeDriver();

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Maximize the window
        driver.manage().window().maximize();

        // Set explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Method to perform login
    public void performLogin(String username, String password) throws InterruptedException {
    	
        // Click login button
        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@type='button'])[1]")));
        login.click();

        // Enter username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);

        // Enter password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        // Click login button
        WebElement loginToQkartBtn = driver.findElement(By.xpath("//button[text()='Login to QKart']"));
        loginToQkartBtn.click();    
        Thread.sleep(3000);
    }
    
    /*TODO:
     * Automate the login process.
     * Validate login with valid credentials.
     * Validate that appropriate error messages are shown for invalid credentials.
*/

    @Test(priority = 0)
    public void TestCase01() throws InterruptedException {
    	
    	System.out.println("*** Start TestCase01 : Login Test ***");
    	
        // Navigate to the Login page of QKart
        driver.get("https://crio-qkart-frontend-qa.vercel.app/");

        // Perform login with valid credentials
        performLogin("sheetalverma00000", "123456789");
        Thread.sleep(3000);

        // Logout Process
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Logout']")));
        logout.click();

        // Login again with invalid credentials
        performLogin("abcdefghmx1", "01290");
        Thread.sleep(3000);

        // Validate error message
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("notistack-snackbar")));
        if (errorMessage.isDisplayed()) {
            System.out.println("Error message displayed for invalid credentials: " + errorMessage.getText());
            Thread.sleep(3000);
        } else {
            System.out.println("No error message displayed for invalid credentials.");
            
          System.out.println("*** End TestCase01 ***");
        }
    }

    
    /*TODO:
     * Automate a scenario where a user searches for a product, adds it to the cart, and proceeds to checkout.
     * Validate that the product is correctly added to the cart.
     * Validate that the checkout process works as expected.
 */
    
    @Test(priority = 1)
    public void TestCase02() throws InterruptedException {
    	
    	System.out.println("*** Start TestCase02 : Functional Test ***");
    	
        // Navigate to the Login page of QKart
        driver.get("https://crio-qkart-frontend-qa.vercel.app/");

        // Perform login with valid credentials
        performLogin("sheetalverma00000", "123456789");
        Thread.sleep(2000);

        // Search for a product
        WebElement searchProduct = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-b52kj1")));
        searchProduct.sendKeys("sofa");
        searchProduct.sendKeys(Keys.ENTER); //keyboard function
        Thread.sleep(3000);

        // Add product to the cart
        WebElement addCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']")));
        addCart.click();
        Thread.sleep(3000);
        
        //Validate that the product is correctly added to the cart.
        WebElement cartProduct = driver.findElement(By.xpath("//div[text()='Stylecon 9 Seater RHS Sofa Set ']"));
        String productText = cartProduct.getText();
        System.out.println(productText);
        Thread.sleep(3000);
        
        //Proceed to Checkout
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Checkout']")));
        checkoutBtn.click();
        Thread.sleep(3000);
        
        //Validate that the checkout process works as expected.
        WebElement checkoutHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Stylecon 9 Seater RHS Sofa Set ']")));
        if(checkoutHeader.isDisplayed()) {
        	System.out.println("Work as expected");
        }else {
        	System.out.println("Not Work as expected");
        }
        	
        System.out.println("*** End TestCase02 ***");
    }

    
    /*TODO:
     Automate a test to verify the presence of key UI elements on the homepage.
     *  Ensure elements like the :
     *  search bar, 
     *  navigation menu and
     *  footer are correctly displayed and functional.
     */
    
    @Test(priority = 2)
    public void TestCase03() throws InterruptedException {
    	
    	System.out.println("*** Start TestCase03 : UI Test ***");
    	
        // Navigate to the Login page of QKart
        driver.get("https://crio-qkart-frontend-qa.vercel.app/");

     // Verify the presence of search bar
        WebElement searchBar = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-11zsshc")));
        if(searchBar.isDisplayed()) {
        	System.out.println("Search Bar is displayed correctly.");
        }else {
        	System.out.println("Search Bar is not displayed.");
        }
        
        // Verify the functionality of the search bar
        // Search for a product
        WebElement searchProduct = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-b52kj1")));
        searchProduct.sendKeys("sofa");
        searchProduct.sendKeys(Keys.ENTER);
        Thread.sleep(3000); // Allow time for the search results to load
        
        WebElement searchResult = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-b52kj1")));
        if(searchResult.isDisplayed()){
        	System.out.println("Functionality Works.");
        }else {
        	System.out.println("Functionality Not Works.");
        }        
        
        WebElement navMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Logout']")));
        if(navMenu.isDisplayed()){
        	System.out.println("Navigation menu is displayed.");
        }else {
        	System.out.println("Navigation menu is not displayed.");
        }
        
     // Verify the presence of the footer
        WebElement footer = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#root > div > div > div.footer.MuiBox-root.css-0")));
        
     // Use JavaScript to scroll to the footer element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
        
        if(footer.isDisplayed()) {
        	System.out.println("Footer is displayed.");
        }else {
        System.out.println("Footer is not displayed.");
        }
        
        System.out.println("*** End TestCase03 ***");
    }
    
    
    /*TODO:
      Automate a scenario where the user fills out a 
     * registration or 
     * contact form.
     
      Validate required:
     * field errors, 
     * input constraints, and 
     * correct form submission.
 */
    
    @Test(priority = 3)
    public void TestCase04() throws InterruptedException {
        System.out.println("*** Start TestCase04 : Form Validation Test ***");
        driver.get("https://crio-qkart-frontend-qa.vercel.app/register");
        
      // Filling out the form/registration with incorrect data
     // Locate form fields and submit button
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement confirmPasswordField = driver.findElement(By.id("confirmPassword"));
        WebElement registerButton = driver.findElement(By.xpath("//button[text()='Register Now']"));        
        Thread.sleep(3000);

     // 1. Attempt to submit the form with empty fields to check required field errors
        registerButton.click();
        Thread.sleep(2000);

     // Validate required field errors (Assuming error messages have specific IDs or classes)
        WebElement usernameError = driver.findElement(By.xpath("//*[text()='Username is a required field']"));
        if(usernameError.isDisplayed()) {
            System.out.println("Username required error displayed: " + usernameError.getText());
        }else {
        	System.out.println("Nothing Show");
        }
        
        WebElement registerButtonPswrd = driver.findElement(By.xpath("//button[text()='Register Now']"));
        registerButtonPswrd.click();
        Thread.sleep(3000);
        
     // 2. Fill the form/registration with invalid data to trigger constraints-> less length
        usernameField.sendKeys("testyeees12300333");
        passwordField.sendKeys("123"); 
        confirmPasswordField.sendKeys("123");
        registerButton.click();
        Thread.sleep(2000);
       
     // Validate input constraints error (Password must be at least 6 characters long)
        WebElement passwordConstraintError = driver.findElement(By.xpath("//*[text()='Password must be at least 6 characters']")); 
        if(passwordConstraintError.isDisplayed()) {
            System.out.println("Password length constraint error displayed: " + passwordConstraintError.getText());
        }
        
     // 3. Fill the form with valid data and submit
        passwordField.clear();
        confirmPasswordField.clear();
        passwordField.sendKeys("7778839927"); 
        confirmPasswordField.sendKeys("7778839927");
        registerButton.click();
        Thread.sleep(4000);
        
        // Validate successful form submission (Assuming a success message or redirect is displayed)
        WebElement successMessage = driver.findElement(By.xpath("//*[text()='Registered Successfully']")); 
        if(successMessage.isDisplayed()) {
            System.out.println("Login successfully: " + successMessage.getText());
        } else {
            System.out.println("Login failed.");
        }
        
        System.out.println("*** End TestCase04 ***");
    }   
    
    // Main method to run the setup and testcases
    public static void main(String[] args) throws InterruptedException {
        
    	TestCases test = new TestCases();

        // Run setup
        test.setup();

        // Run the test cases
        test.TestCase01();
        test.TestCase02();
        test.TestCase03();
        test.TestCase04();
    }
        
@AfterMethod
public void teardown() {
    if (driver != null) {
        driver.quit();
    }
}
}