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

public class TestSuit {

    protected static WebDriver driver;

    @BeforeClass
    public static void OpenBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");

        driver = new ChromeDriver();  //open the brower
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//implicitlywait for browser
        driver.manage().window().maximize();//dreiver get window maximize
        driver.get("https://demo.nopcommerce.com/");//get on the home page
    }

    @AfterClass
    public void driverQuit() {

        //driver.quit();
    }

    //>>>>>>>>>>>>>>>>>>>>>>>> User should be able register success fully <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test(priority = 1)
    public void UserShouldBeAbleTORegisterSuccessfully() {
        // click on register button
        clickOnElement(By.className("ico-register"));  //

        //click on gender
        driver.findElement(By.id("gender-male")).click();

        // enter your  firstname
        sendKeys(By.xpath("//input[@name='FirstName']"), "Autoamtion");

        //enter your lastname
        // driver.findElement(By.id("LastName")).sendKeys("LastNameTest");
        sendKeys(By.id("LastName"), "LastNameTest");
        //>>>>>>>>>>>>>>>>>>>>>>select birthday >>>>>>>>>>>>>>>>>>>>
        Select birthDay = new Select(driver.findElement(By.name("DateOfBirthDay")));//select on the day
        birthDay.selectByIndex(28);

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>select Month >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        Select birtMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));//select on month
        birtMonth.selectByIndex(3);

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>select year  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        Select birthYear = new Select(driver.findElement(By.name("DateOfBirthYear")));//select on year
        birthYear.selectByVisibleText("1984");

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Enter your Email addresss  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        // driver.findElement(By.id("Email")).sendKeys("Jigar75@gmail.com");
        sendKeys(By.id("Email"), "jigar75" + randomDate() + "@gmail.com");

        //Enter your password
        //driver.findElement(By.id("Password")).sendKeys("Jb1984sn");
        sendKeys(By.id("Password"), "Jb1984sn");

        // Enter Confirm Password

        //driver.findElement(By.id("ConfirmPassword")).sendKeys("Jb1984sn");
        sendKeys(By.id("ConfirmPassword"), "Jb1984sn");
        //  Enter register Button
        driver.findElement(By.id("register-button")).click();

        //Assert  for registration

        String expectedMessage = "Your registration completed";
        String actualMessage = driver.findElement(By.className("result")).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Your registration is NOT successful");

        //>>>>>>>>>>>>>>>>>>>>>User Should be able to build their onwn computer <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    }

    @Test(priority = 2)
    public void UserShouldBeAbleToBuildYourOwnComputer() {
        // clik on computer
        driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Computers ']")).click();

        //  click on desktop
        driver.findElement(By.xpath("//img[@alt=\"Picture for category Desktops\"]")).click();

        //   click on Build your own computer
        driver.findElement(By.xpath("//h2[@class=\"product-title\"]/a[@href=\"/build-your-own-computer\"]")).click();

        //select on processor 2.2GHZ
        Select Processor = new Select(driver.findElement(By.id("product_attribute_1")));
        Processor.selectByValue("1");

        //select on RAM  2 GB
        Select Ram = new Select(driver.findElement(By.id("product_attribute_2")));
        Ram.selectByValue("3");

        //click on radio 320gb
        driver.findElement(By.xpath("//label[@for=\"product_attribute_3_6\"]")).click();

        //click on vista primium
        driver.findElement(By.xpath("//label[@for=\"product_attribute_4_9\"]")).click();

        //tick  on Microsoft Office [+$50.00]
        driver.findElement(By.xpath("//label[@for=\"product_attribute_5_11\"] ")).click();

        //tick  on Acrobat Reader [+$10.00]
        driver.findElement(By.xpath("//input[@value=\"12\"]")).click();

        //tick on  Total Commander [+$5.00]
        clickOnElement(By.xpath("//button[@id=\"add-to-cart-button-1\"]"));

        //click on Add to CART
        clickOnElement(By.xpath("//span[@class=\"cart-label\"]"));

        //Assert for Shopping cart   (1)

        String expectedMessage = "Shopping cart";

        String actualMessage = driver.findElement(By.xpath("//h1 [contains (text (),'Shopping cart')]")).getText();

        Assert.assertEquals(actualMessage, expectedMessage, "The product will not added to the cart ");

        //Assert  for Build your own computer (2)


        String actualMessage1 = driver.findElement(By.className("product-name")).getText();

        String expectedMessage1 = "Build your own computer";

        Assert.assertEquals(actualMessage, expectedMessage, "build your computer not added to the cart");

    }

    @Test(priority = 3)
    public void RegisterUerShouldReferproductToTheirFriends() {
        clickOnElement(By.className("ico-register"));  //

        // enter your  firstname
        sendKeys(By.xpath("//input[@name='FirstName']"), "Autoamtion");

        //enter your lastname
        // driver.findElement(By.id("LastName")).sendKeys("LastNameTest");
        sendKeys(By.id("LastName"), "LastNameTest");

        sendKeys(By.id("Email"), "jigar75" + randomDate() + "@gmail.com");

        //Enter your password
        //driver.findElement(By.id("Password")).sendKeys("Jb1984sn");
        sendKeys(By.id("Password"), "Jb1984sn");

        // Enter Confirm Password

        //driver.findElement(By.id("ConfirmPassword")).sendKeys("Jb1984sn");
        sendKeys(By.id("ConfirmPassword"), "Jb1984sn");
        //  Enter register Button
        driver.findElement(By.id("register-button")).click();


        //click on computer
        driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Computers ']")).click();

        //click on desk top
        driver.findElement(By.xpath("//img[@alt=\"Picture for category Desktops\"]")).click();

        //build your own compute added to the cart
        clickOnElement(By.xpath("//div[@data-productid=\"1\"]/div/div[3]/div[2]//button[@class=\"button-2 product-box-add-to-cart-button\"]"));

        //cilck on email friend button
        driver.findElement(By.xpath("//button[@class=\"button-2 email-a-friend-button\"]")).click();

        //enter your friend email
        driver.findElement(By.xpath("//*[@id=\"FriendEmail\"]")).sendKeys("jigarsoni75@yaoo.com");

        //enter your email address
        driver.findElement(By.xpath("//*[@id=\"YourEmailAddress\"]")).sendKeys(" ");

        // enter personal message
        driver.findElement(By.xpath("//*[@id=\"PersonalMessage\"]")).sendKeys("This computer is a very nice and good price.");
        //clik on send email Button
        driver.findElement(By.name("send-email")).click();


        String actualMessage = driver.findElement(By.className("result")).getText();

        String expectedMessage = "Your message has been sent.";

        Assert.assertEquals(actualMessage, expectedMessage, " your message has not been sent");

    }

    @Test(priority = 4)

    public void userShouldBeableToSuccesfullyChangethecurrency() {

        selectByValueDropDown(By.xpath("//strong [contains (text (),'$1,200.00')]"), "https://demo.nopcommerce.com/changecurrency/1?returnUrl=%2F");
        String actualMessage3 = getTextFromElement(By.xpath("//strong [contains (text (),'$1,200.00')]"));
        String expectedMessage3 = "Us Dollar";
        Assert.assertEquals(actualMessage3, expectedMessage3, "Us Dollar is NOT the currency selected");


//        String actualMessage4 = getTextFromElement(By.xpath("//select[@id=\"customerCurrency\"]"));
//        String expectedMessage4 = "Euro";
//        Assert.assertEquals(actualMessage4, expectedMessage4, "Euro is NOT the currency selected");

        Select birtMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));//select on month
        birtMonth.selectByIndex(3);


    }


//    @Test
//
//    public static void userShouldBeableToSuccesfullyChangethecurrency() {
//
//
////       Select dollar = new Select(driver.findElement(By.id("customerCurrency")));//select on the day
////       dollar.selectByValue("https://demo.nopcommerce.com/changecurrency/1?returnUrl=%2Fproductemailafriend%2F1");
//
//        Select Euro = new Select(driver.findElement(By.id("customerCurrency")));//select on euro
//        Euro.selectByValue("https://demo.nopcommerce.com/changecurrency/6?returnUrl=%2Fregister%3FreturnUrl%3D%2F");
//
//    }


////            >>>>>>>>>>>>>>>>> re-usable methods <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    //    }
    public static void sendKeys(By by, String text) { //enter firstname
        driver.findElement(by).sendKeys(text);
    }

    public static String randomDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
        return formatter.format(date);
    }

    public static void elementToBeClickable(int time, By by) {                                             //(1)
        WebDriverWait waitForClickable = new WebDriverWait(driver, Duration.ofSeconds(time));
        waitForClickable.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public static void urlContainsFraction(int time, String fraction) {                                     //(2)
        WebDriverWait wait01 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait01.until(ExpectedConditions.urlContains(fraction));
    }

    public static void driverWaitTextToBePresentInElementLocated(int time, By by, String text) {            //(3)
        WebDriverWait wait02 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait02.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }

    public static void driverWaitTitleContains(int time, String title) {                                  //(4)
        WebDriverWait wait03 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait03.until(ExpectedConditions.titleContains(title));
    }

    public static void driverWaitTitleContainsURL(int time, String title) {                               //(5)
        WebDriverWait wait04 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait04.until(ExpectedConditions.urlContains(title));
    }

    public static void driverWaitPresenceOfElementLocated(int time, By by) {                              //(6)
        WebDriverWait wait05 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait05.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void driverWaitInvisibleOfElement(int time, By by) {                                    //(6)
        WebDriverWait wait06 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait06.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void driverWaitAttributeContains(int time, By by, String attribute, String value) {        //(7)
        WebDriverWait wait06 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait06.until(ExpectedConditions.attributeContains(by, attribute, value));
    }

    public static void driverWaitAttributeToBe(int time, By by, String value, String attribute) {          //(8)
        WebDriverWait wait07 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait07.until(ExpectedConditions.attributeToBe(by, value, attribute));
    }

    public static void driverWaitAttributeToBeNotEmpty(int time, By by, String attribute) {               //(9)
        WebDriverWait wait08 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait08.until(ExpectedConditions.attributeToBeNotEmpty((WebElement) by, attribute));
    }

    public static void driverWaitElementToBeClickable(int time, By by) {                                   //(10)
        WebDriverWait wait09 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait09.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void selectDropdownByValue(By by, String value) {
        Select dropdown = new Select(driver.findElement(by));
        dropdown.selectByValue(value);

    }

    public static void clickOnElement(By by) {    // click on register button
        driver.findElement(by).click();
    }

    public static void click(By by) {
        driver.findElement(by).click();
    }

    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }


    public void selectByValueDropDown(By by, String value) {

        //this method select value from drop down

        Select select = new Select(driver.findElement(by));

        //Select by value
        select.selectByValue(value);

    }



}