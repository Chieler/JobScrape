import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
public class AppConfig {
    //Reference to wbDriver
    public static WebDriver driver = new ChromeDriver();

    public static void init(){
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

    }
    public static WebDriver getDrive(){
        return driver;
    }


    
}
