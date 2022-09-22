package Driver;

import Component.DataConfig;
import Exceptions.Exceptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.time.Duration;
import java.util.function.Function;
import java.util.logging.Logger;

public class WebAndChromeDriver{

    public static WebDriver driver;

    protected static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    private static final int USER_WAIT_IN_MS = 1000;
    private static final int EXPECTED_CONDITION_TIMEOUT = 10;
    private static final int EXPECTED_CONDITION_POLLING_INTERVAL = 3;

    DataConfig data = new DataConfig();


    @SneakyThrows
    public void openBrowser(String platform){

        if (platform.equals("Firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if(platform.equals("Chrome")){
            ChromeOptions handlingSSL = new ChromeOptions();
            handlingSSL.setAcceptInsecureCerts(true);

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
            else{
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setBrowserName("chrome");
            dc.setVersion("93.0.4577.82");
            URL url = new URL("http://localhost:4444/wd/hub");
            driver = new RemoteWebDriver(url,dc);

        }
        driver.get(DataConfig.LOGIN_ADDRESS);
        driver.manage().window().maximize();

    }

    public <id, keyString> void sendKeysMethod(id id, keyString keyString) throws Exceptions {
        sleepms(USER_WAIT_IN_MS);
        if ((id == null))
            throw new Exceptions(id + "is null");
        else if (!(id instanceof WebElement) && !(id instanceof By))
            throw new Exceptions(id + "parameter type not supported by sendKeys function");

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(EXPECTED_CONDITION_TIMEOUT))
                .pollingEvery(Duration.ofSeconds(EXPECTED_CONDITION_POLLING_INTERVAL))
                .ignoring(ElementNotVisibleException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class);

        wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement el = null;
                if (id instanceof By) {
                    el = driver.findElement((By) id);
                } else {
                    el = ((WebElement) id);
                }
                if (keyString instanceof String) {
                    el.clear();
                    el.sendKeys(((String) keyString));
                } else if (keyString instanceof Keys) {
                    el.sendKeys(((Keys) keyString));
                }
                sleepms(500);
                return el;
            }
        });
    }

    public <id> void clickMethod(id id) throws Exceptions {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(EXPECTED_CONDITION_TIMEOUT))
                .pollingEvery(Duration.ofSeconds(EXPECTED_CONDITION_POLLING_INTERVAL))
                .ignoring(ElementNotVisibleException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(org.openqa.selenium.TimeoutException.class)
                .ignoring(WebDriverException.class)
                .ignoring(ElementClickInterceptedException.class);
        wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement el = null;
                if (id instanceof By) {
                    el = driver.findElement((By) id);
                } else if (id instanceof WebElement) {
                    el = ((WebElement) id);
                } else if (id instanceof String) {
                    LOGGER.info("id is " + id);
                    el = (WebElement) ((JavascriptExecutor) driver).executeScript("return " + String.valueOf(id));
                    if (el == null)
                        return null;
                }

                new WebDriverWait(driver, 3)
                        .until(ExpectedConditions.elementToBeClickable(el));
                el.click();
                return el;
            }
        });
        sleepms(USER_WAIT_IN_MS);
    }

    public static void sleepms(long time) {
        LOGGER.fine("Sleeping for " + time + " ms");
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}





