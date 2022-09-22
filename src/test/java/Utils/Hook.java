package Utils;

import Driver.WebAndChromeDriver;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;


public class Hook {

    private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    public void endTest(Scenario scenario){

        LOGGER.info("<-<-<-<-<-<-<-<-<-<-<---End " + scenario.getUri() + " Scenario # " + scenario.getName() + " <-<-<-<-<-<-<-<-<-");

        if(scenario.isFailed()){
            try {
                byte[] screenshot = ((TakesScreenshot) WebAndChromeDriver.driver).getScreenshotAs(OutputType.BYTES);

                scenario.embed(screenshot, "image/png");
            }catch (WebDriverException e){
                e.printStackTrace();
            }

        }
        else{
            LOGGER.info("<-<-<-<-<-<-<-<-<-<-<---End " + scenario.getUri() + " Scenario # " + scenario.getName() + " <-<-<-<-<-<-<-<-<-");
            try {
                byte[] screenshot = ((TakesScreenshot) WebAndChromeDriver.driver).getScreenshotAs(OutputType.BYTES);

                scenario.embed(screenshot, "image/png");
            }catch (WebDriverException e){
                e.printStackTrace();
            }

        }
}
}

