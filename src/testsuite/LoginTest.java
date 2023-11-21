package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void SetUp(){
        openBrowser(baseUrl);
    }
    @Test
    public void userShouldNavigateToLoginPageSuccessfully()  {
        //find the login link and click on login page
        WebElement loginLink = driver.findElement(By.linkText("Log in"));
        loginLink.click();
        //Thread.sleep(5000);
        //verify the text 'Welcome,Please sign In!'
        String expectedText = "Welcome, Please Sign In!";
        //Find the actual text element and get the text from element
        WebElement actualTextElement =  driver.findElement(By.xpath("//h1"));
        String actualText = actualTextElement.getText();
        //Verify expected and actual text
        Assert.assertEquals("Not redirected to login page",expectedText,actualText);
    }
    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() throws InterruptedException {
        // Find the login link and click on login link
        WebElement loginLink = driver.findElement(By.linkText("Log in"));
        loginLink.click();
        // Find the Email Field and Type the email address
        WebElement emailField = driver.findElement(By.id("Email"));
        emailField.sendKeys("hansa123@gmail.com");
        Thread.sleep(5000);

        // Find the password field and Type the password to password field
        WebElement pwdField = driver.findElement(By.name("Password"));
        pwdField.sendKeys("hansa123");
        Thread.sleep(5000);

        //Find the login button element and click
        driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();
        //Thread.sleep(5000);

        String expectedErrorMessage = "Log out";
        //find the error message element and get the text
        String actualErrorMessage = driver.findElement(By.linkText("Log out")).getText();
        Assert.assertEquals("Log out", expectedErrorMessage,actualErrorMessage);
    }
    @Test
    public void  verifyTheErrorMessage() throws InterruptedException {
        //Find the login link and click on it
        driver.findElement(By.xpath("//a[@class='ico-login']")).click();

        //Find the email field and type the email address to email field
       driver.findElement(By.name("Email")).sendKeys("xyz123@gmail.com");
       Thread.sleep(5000);

        //Find the password field and type the password to password field
        driver.findElement(By.id("Password")).sendKeys("xyz123");
        Thread.sleep(5000);

        //Find the login btn element and click
         driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();
         Thread.sleep(5000);

        String expectedErrorMessage = "Login was unsuccessful. Please correct the errors and try again.\n" +
                " No customer account found";
        //find the error message element and get the text
        String actualErrorMessage = driver.findElement(By.xpath("//div[@class='message-error validation-summary-errors']")).getText();
        Assert.assertEquals("Error message not displayed", expectedErrorMessage, actualErrorMessage);
    }
    @After
    public void tearDown(){
        closeBrowser();
    }
}

