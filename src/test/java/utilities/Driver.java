package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

public class Driver {
    private Driver() {

    }

    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    //browserstack credentials of Büşra Tığlı
    public static final String AUTOMATE_USERNAME = "bratl1";
    public static final String AUTOMATE_ACCESS_KEY = "mQG7xpB7BfYq1NQk68Ss";
    public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";


    public static WebDriver get() {
        if (driverPool.get() == null) {
            String browser = System.getProperty("browser") != null ? browser = System.getProperty("browser") : utilities.ConfigurationReader.get("browser");
            switch (browser) {
                case "chrome":
                    ChromeOptions options=new ChromeOptions();
                    options.addArguments("--disable-notifications");
                    options.setExperimentalOption("prefs", Collections.singletonMap("intl.accept_languages", "tr-TR"));
                    System.setProperty("webdriver.chrome.driver","path/to/driver/exe");
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver(options));
                    break;
                case "chrome-headless":
                    ChromeOptions options1 = new ChromeOptions();
                    options1.addArguments("--headless");
                    options1.addArguments("--lang=tr-TR");
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver(options1.setHeadless(true)));
                    break;
                case "firefox":
                    FirefoxOptions options2=new FirefoxOptions();
                    options2.setProfile(new FirefoxProfile());
                    options2.addPreference("dom.webnotifications.enabled", false);
                    System.setProperty("webdriver.firefox.driver","path/to/driver/exe");
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(options2));
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    WebDriverManager.iedriver().setup();
                    driverPool.set(new InternetExplorerDriver());
                    break;
                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Edge");
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver());
                    break;
                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("Your OS doesn't support Safari");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driverPool.set(new SafariDriver());
                    break;
                case "remote_chrome":
                    ChromeOptions chromeOptions=new ChromeOptions();
                    chromeOptions.setCapability("platform", Platform.ANY);
                    try {
                        driverPool.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),chromeOptions));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                case "browserstack":
                    DesiredCapabilities caps = new DesiredCapabilities();

                    caps.setCapability("os", "Windows");
                    caps.setCapability("os_version", "10");
                    caps.setCapability("browser", "Chrome");
                    caps.setCapability("browser_version", "latest");
                    caps.setCapability("build", "QA");
                    caps.setCapability("name", "smoke test");
                    //caps.setCapability("browserstack.local", "false");
                    //caps.setCapability("browserstack.selenium_version", "3.14.0");

                    try {
                        driverPool.set(new RemoteWebDriver(new URL(URL), caps));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "mobile":
                    ChromeOptions mobileOptions = new ChromeOptions();
                    mobileOptions.addArguments("--disable-notifications");
                    //System.setProperty("webdriver.chrome.driver","path/to/driver/exe");
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver(mobileOptions));
                    break;
            }
        }
        return driverPool.get();

    }

    public static void closeDriver() {

        driverPool.get().quit();
        driverPool.remove();
    }
}