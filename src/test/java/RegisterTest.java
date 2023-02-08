import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegisterTest {
    WebDriver driver;
    @BeforeClass
    public void openBrowser()
    {
        System.setProperty("webdriver.chrome.driver",".\\src\\main\\resources\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test(dataProvider = "test_data")
    public void RegisterTestCase(String FirstName, String LastName, String Email,String Password, String ConfirmPassword) throws InterruptedException {
        driver.findElement(By.linkText("Register")).click();
        //driver.findElement(By.xpath("//*[@id=\"gender\"]/span[2]/label")).click();
        driver.findElement(By.id("FirstName")).sendKeys(FirstName);
        driver.findElement(By.id("LastName")).sendKeys(LastName);
        driver.findElement(By.id("Email")).sendKeys(Email);
        JavascriptExecutor java = (JavascriptExecutor)driver;
        java.executeScript("scroll(0,250)");
        driver.findElement(By.name("Password")).sendKeys(Password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(ConfirmPassword);
        driver.findElement(By.name("register-button")).click();
        String ExpectedResult= driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]")).getText();
        Assert.assertEquals("Your registration completed",ExpectedResult);
       // driver.findElement(By.linkText("Logout")).click();
        Thread.sleep(6000);
    }


    @AfterClass
    public void CloseBrowser()
    {
        driver.quit();
    }
    @DataProvider
    public  Object[][] test_data() throws IOException, InvalidFormatException {
        ReadExcel Obj = new ReadExcel();
        return Obj.readSheet();
    }
}
