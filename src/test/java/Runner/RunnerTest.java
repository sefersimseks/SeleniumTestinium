package Runner;

import Driver.WebAndChromeDriver;
import StepDefinitions.LoginPage;
import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;

import static Component.DataConfig.PLATFORM;

@RunWith(Cucumber.class)
@CucumberOptions(features ="src/test/resources/Features/SeleniumAutomaion.feature",glue = {"StepDefinitions"},

        monochrome = true,
plugin= {"pretty","json:target/cucumber-reports/cucumber.json",
         "html:target/cucumber-reports/cucumber.html"   },

        strict = true
)
public class RunnerTest extends WebAndChromeDriver{
    LoginPage loginPage = new LoginPage();
    Scenario scenario;

    @Before
    public void openBrowser(){
        openBrowser(PLATFORM);
        loginPage.Setup(scenario);
    }
    @AfterClass
    public static void tearDown(){
        driver.close();
        driver.quit();
    }
}