package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.URL;
import java.util.Properties;

/*
@className = BaseTest
@description = Base Test Class for test environment setup
*/
public class BaseTest {
    public static WebDriver driver;
    public static Properties properties = PropertyFileReader.readPropertiesFile("testdata.properties");
    @BeforeTest
    public void setUp(){
        String browser = properties.getProperty("browser");
        if(driver==null){
            driver = get(browser);
        }
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
    /*
    @methodName = get
    @description = This method is used to get browser type
    @parameters = String (browser name)
    */
    public static WebDriver get(String browser){
        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(properties.getProperty("URL"));
            return driver;
        } else if(browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get(properties.getProperty("URL"));
            return driver;
        } else if(browser.equals("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.manage().window().maximize();
            driver.get(properties.getProperty("URL"));
            return driver;
        } else if(browser.equals("docker")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.setCapability(ChromeOptions.CAPABILITY, options);
            options.setCapability("browserName", "chrome");
            options.setCapability("acceptSslCerts", "true");
            options.setCapability("javascriptEnabled", "true");
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            options.merge(cap);
            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

}
