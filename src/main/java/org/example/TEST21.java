package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static org.openqa.selenium.By.*;


public class TEST21 {

    protected static WebDriver driver;

    @BeforeClass
    public static void openBrowser()
    {
        System.setProperty("webdriver.chrome.driver","src/test/java/driver/chromedriver.exe");

        //open Chrome browser:
        driver = new ChromeDriver();

        //Implicit wait method :
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Maximizing chrome window:
        driver.manage().window().maximize();

        //Driver to type URL :
        driver.get("https://demo.nopcommerce.com/");
    }

    @AfterClass
    public void driverQuit()
    {
        //   driver.quit();
    }


    @Test
    public void registration()
    {
        // click on register button
        driver.findElement(className("ico-register")).click();

        //select on male or female
        driver.findElement(xpath("//input[@id=\"gender-female\"]")).click();

        // enter firstname
        // driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys("Allan");
        sendKeys(id("FirstName"),"Charmy");


        //enter last name
        //driver.findElement(By.id("LastName")).sendKeys("Sugar");
        sendKeys(id("LastName"),"Patel");

        //Date of birth
        selectDropdownByValue(xpath("//select[@name='DateOfBirthDay']"),"2");

        //Month of birth
        selectDropdownByValue(xpath("//select[@name='DateOfBirthMonth']"),"9");

        //Year of the birth
        selectDropdownByValue(xpath("//select[@name='DateOfBirthYear']"),"1980");


        //EMAIL address
        //driver.findElement(By.id("Email")).sendKeys("easy123@mail.com");
        sendKeys(id("Email"),"easy"+randomDate()+"@mail.com");


        //password field
        // driver.findElement(By.id("Password")).sendKeys("12345678");
        sendKeys(id("Password"),"12345678");


        //Confirm Password
        // driver.findElement(By.id("ConfirmPassword")).sendKeys("12345678");
        sendKeys(id("ConfirmPassword"),"12345678");

        //click on Register button on the button of the page

        //driver.findElement(By.id("register-button")).click();
        click(id("register-button"));

        String expectedMessage = "Your registration completed";
        String actualMessage = driver.findElement(className("result")).getText();
        Assert.assertEquals(expectedMessage,actualMessage,"Your registration is NOT successful");


    }

    @Test

    public void  EmailFriend(){

        {

            driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Computers ']")).click();

            driver.findElement(By.xpath("//img[@alt=\"Picture for category Desktops\"]")).click();

            driver.findElement(By.xpath("//h2[@class=\"product-title\"]/a[@href=\"/build-your-own-computer\"]")).click();

            selectByValueDropDown(By.id("product_attribute_1"), "1");

            selectByValueDropDown(By.id("product_attribute_2"), "3");

            driver.findElement(By.xpath("//label[@for=\"product_attribute_3_6\"]")).click();

            driver.findElement(By.xpath("//label[@for=\"product_attribute_4_9\"]")).click();

            driver.findElement(By.xpath("//label[@for=\"product_attribute_5_11\"] ")).click();

            driver.findElement(By.xpath("//input[@value=\"12\"]")).click();

            // driver.findElement(By.xpath("//button[@id=\"add-to-cart-button-1]")).click();
            clickOnElement(By.xpath("//button[@id=\"add-to-cart-button-1\"]"));

            clickOnElement(By.xpath("//span[@class=\"cart-label\"]"));

            String expectedMessage = "Your registration completed";
            String actualMessage = driver.findElement(className("result")).getText();
            Assert.assertEquals(expectedMessage,actualMessage,"Your registration is NOT successful");






        }
    }

//---------------------------------------------------------------------------------------------------------
    //utility method

    public static void sendKeys(By by,String text)
    {
        driver.findElement(by).sendKeys(text);
    }

    public static void click(By by)
    {
        driver.findElement(by).click();
    }

    public static void getTextFromElement(By by)
    {
        driver.findElement(by).getText();
    }

    public static String randomDate()
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMYyyyHHMmSs");
        return formatter.format(date);
    }

    public static void driverWaitUnTillElementToBeClickable(By by,int time)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();

    }

    public static void driverWaitUnitElementContainsUrlFraction(By by,int time,String FractionUrl)
    {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait1.until(ExpectedConditions.urlContains("FractionUrl"));
    }

    public static void driverWaitUntilElementLocated(By by,int time)
    {
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait2.until(ExpectedConditions.presenceOfElementLocated(by));

    }
    public static void driverWaitUntilElementsTitleContains(int time,String TitleContains)
    {
        WebDriverWait wait4 = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait4.until(ExpectedConditions.titleContains(TitleContains));

    }
    public static void driverWaitUntilContainsUrl(int time,String url)
    {

        WebDriverWait wait5 = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait5.until(ExpectedConditions.urlContains(url));

    }

    public static void driverWaitUntilPresenceOfElement(By by, int time)
    {
        WebDriverWait wait6 = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait6.until(ExpectedConditions.presenceOfElementLocated(by));
    }


    public static void driverWaitUntilInvisibilityOfWebElement( int time, WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.invisibilityOf(element));

    }

    public static void driverWaitUntil(int time,By by)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeSelected(by));
    }
    public static void selectDropdownByValue(By by,String value)
    {
        Select dropdown = new Select(driver.findElement(by));
        dropdown.selectByValue(value);

    }
    public void selectByValueDropDown(By by, String value) {

        //this method select value from drop down

        Select select = new Select(driver.findElement(by));

        //Select by value
        select.selectByValue(value);

    }
    public static void clickOnElement(By by) {    // click on register button
        driver.findElement(by).click();
    }


}








