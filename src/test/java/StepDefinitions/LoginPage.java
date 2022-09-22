package StepDefinitions;


import Component.DataConfig;
import Driver.WebAndChromeDriver;
import Pages.HomeSearchPage;
import Utils.Hook;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;

import static Component.DataConfig.PLATFORM;

public class LoginPage {

    WebAndChromeDriver webAndChromeDriver = new WebAndChromeDriver();
    DataConfig data = new DataConfig();
    HomeSearchPage homeSearchPage = new HomeSearchPage();
    Scenario scenario;
    Hook hooks = new Hook();

    @Before
    public void Setup(Scenario scenario) {
        this.scenario = scenario;
        webAndChromeDriver = new WebAndChromeDriver();
    }

    @After
    public void CleanUp() {
        scenario.write("<-<-<-<-<-<-<-<-<-<-<---End " + scenario.getUri() + " Scenario # " + scenario.getLine() + " <-<-<-<-<-<-<-<-<-");
        hooks.endTest(scenario);
    }


    @Given("OPEN BROWSER")
    public void open_browser() {
        data.properties();
        webAndChromeDriver.openBrowser(PLATFORM);
       homeSearchPage.checkHomaPage();
    }

    @SneakyThrows
    @When("USER ENTERS FIRST TEXT IN SEARCH BOX")
    public void user_enters_first_text_in_search_box() {
        homeSearchPage.enterFirstText();
    }
    @SneakyThrows
    @When("USER DELETE TEXT IN SEARCH BOX")
    public void user_delete_text_in_search_box() {
        homeSearchPage.deleteText();
    }
    @SneakyThrows
    @When("USER ENTERS SECOND TEXT IN SEARCH BOX")
    public void user_enters_second_text_in_search_box() {
        homeSearchPage.enterSecondText();
    }
    @SneakyThrows
    @When("USER SELECT THIRD ITEM")
    public void user_select_third_item() {
        homeSearchPage.selectThirdItem();
    }
    @SneakyThrows
    @Then("USER GET ITEM INFORMATION")
    public void user_get_item_information() {
        homeSearchPage.getItemInfo();
    }
    @SneakyThrows
    @When("USER ADD ITEM TO CART")
    public void user_add_item_to_cart() {
        homeSearchPage.addItemCart();
    }
    @SneakyThrows
    @When("USER GOES CART")
    public void user_goes_cart() {
        homeSearchPage.goesCart();
    }
    @SneakyThrows
    @When("USER INCREASE THE NUMBER OF ITEM")
    public void user_increase_the_number_of_item() {
        homeSearchPage.increaseItemNumber();
    }
    @SneakyThrows
    @Then("USER CHECK PRICE OF DOUBLE ITEM")
    public void user_check_price_of_double_item() {
        homeSearchPage.checkPriceOfDoubleItem();
    }
    @SneakyThrows
    @When("USER DELETE ITEM IN THE CART")
    public void user_delete_item_in_the_cart() {
        homeSearchPage.deleteItem();
    }

}
