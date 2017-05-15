package trainTestNG;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

//import ru.stqa.selenium.*;
  //      openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * создано на примере видео про  параметры testNG ( но это без testng 27/04/2017.
 * датапровайдер находится в отдельном классе
 */
@Test(groups = "loadFromFile")
public class DataProviderExample {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void start() {
        /*   driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10); */
        System.out.println("  DataProviderSample is running: ");

    }


    //  пример использования  параметров из файла
@Test(dataProviderClass =  DataProviders.class,dataProvider ="loadUserFromFile")
public void testLogin(String user, String password)   {

   /* driver.get("https:///");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.findElement(By.cssSelector("li.sign_in_link")).click();
    WebElement btng =  driver.findElement(By.name("btnG"));
    btng.click();
    wait.until(titleIs("e")); */
    System.out.println("Test DataProviderExample with data from loaded from a file: " + user+ ":" + password);
}

/*@DataProvider
public Iterator<Object[]> users() {
    List<Object[]> data = new ArrayList<Object[]>();


    data.add(new Object[]{"guest", "guest"});
    return data.iterator();

}  */

@AfterTest
public void stop() {
    /*    driver.quit();
        driver = null; */
}

}
