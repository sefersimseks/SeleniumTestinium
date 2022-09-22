package Pages;

import Driver.WebAndChromeDriver;
import Utils.FileUtils;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;


public class HomeSearchPage extends WebAndChromeDriver {

    public static By SEARCH_CLASS = By.cssSelector("input[class='default-input o-header__search--input']");
    public static By THIRD_ITEM_CLASS = By.cssSelector("a[href='/p_beymen-club-mavi-cizgili-zincir-nakisli-mini-gomlek-elbise_1008793']");
    public static By ITEM_NAME = (By.className("o-productDetail__description"));
    public static By ITEM_PRICE_OLD = (By.id("priceOld"));
    public static By ITEM_PRICE_NEW = (By.id("priceNew"));
    public static By HOME_SWIPER_WRAPPER = (By.id("o-fullSlider-342"));
    public static By ADD_BASKET = (By.id("addBasket"));
    public static By BASKET_TITTLE = By.cssSelector("[title=\"Sepetim\"]");
    public static By INCREASE_ITEM_NUMBER = (By.id("quantitySelect0-key-0"));
    public static By CART_DOUBLE_ITEM_PRICE = (By.className("m-productPrice__salePrice"));
    public static By CART_SUMMARY_ITEM_PRICE = By.xpath("//span[contains(@class, 'm-productPrice__salePrice')]");
    public static By DELETE_CART = (By.id("removeCartItemBtn0-key-0"));
    public static By CHECK_EMPTY_CART_CLASS = (By.className("m-empty__messageTitle"));

    String Item_New_Price;
    String Expected_Empty_Message = "SEPETINIZDE ÜRÜN BULUNMAMAKTADIR";
    @SneakyThrows
    public void checkHomaPage() {
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(HOME_SWIPER_WRAPPER).isDisplayed(), true);
    }

    @SneakyThrows
    public void enterFirstText() {
        FileInputStream fs = new FileInputStream("Test.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        System.out.println(sheet.getRow(0).getCell(0));
        String Text1 = String.valueOf(sheet.getRow(0).getCell(0));
        sendKeysMethod(SEARCH_CLASS, Text1);
    }

    @SneakyThrows
    public void deleteText() {
        sendKeysMethod(SEARCH_CLASS, Keys.CONTROL + "a");
        sendKeysMethod(SEARCH_CLASS, Keys.DELETE);
    }

    @SneakyThrows
    public void enterSecondText() {
        FileInputStream fs = new FileInputStream("Test.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(0);
        System.out.println(sheet.getRow(1).getCell(0));
        String Text2 = String.valueOf(sheet.getRow(1).getCell(0));
        sendKeysMethod(SEARCH_CLASS, Text2);
        sendKeysMethod(SEARCH_CLASS,Keys.ENTER);
    }
    @SneakyThrows
    public void selectThirdItem() {
        clickMethod(THIRD_ITEM_CLASS);
    }
    @SneakyThrows
    public void getItemInfo() {
        String Item_Name = driver.findElement(ITEM_NAME).getText();
        String Item_Old_Price = driver.findElement(ITEM_PRICE_OLD).getText();
        Item_New_Price = driver.findElement(ITEM_PRICE_NEW).getText();
        System.out.println(Item_Name);
        System.out.println(Item_Old_Price);
        System.out.println(Item_New_Price);

        File text = new File("text.txt");
        FileWriter fw = new FileWriter(text);
        fw.write(Item_Name);
        fw.write(Item_New_Price);
        fw.close();
    }
    @SneakyThrows
    public void addItemCart() {
        driver.findElement(By.xpath("//span[contains(text(),'40')]")).click();
        clickMethod(ADD_BASKET);
    }

    @SneakyThrows
    public void goesCart() {
        clickMethod(BASKET_TITTLE);
    }
    @SneakyThrows
    public void increaseItemNumber() {
        String Item_double_price = driver.findElement(CART_DOUBLE_ITEM_PRICE).getText();
        System.out.println(Item_double_price);
        Assert.assertEquals(Item_double_price,Item_New_Price);
        clickMethod(driver.findElement(INCREASE_ITEM_NUMBER));
        Select fruits = new Select(driver.findElement(INCREASE_ITEM_NUMBER));
        fruits.selectByVisibleText("2 adet");
        fruits.selectByIndex(1);
    }
    @SneakyThrows
    public void checkPriceOfDoubleItem() {
        Item_New_Price = Item_New_Price.substring(0,6);
        Double Item_New_Price_Double = Double.parseDouble(Item_New_Price.replace(",","."));
        Double Item_New_Price_Double_1 = Item_New_Price_Double * 2;
        String Item_New_Price_Expected = Double.toString(Item_New_Price_Double_1);
        Item_New_Price_Expected= Item_New_Price_Expected.substring(0,5).replace(".","");
        Thread.sleep(3000);
        String Item_double_price = driver.findElement(CART_SUMMARY_ITEM_PRICE).getText();
        Item_double_price = Item_double_price.substring(0,5).replace(".","");
        System.out.println(Item_New_Price_Expected);
        System.out.println(Item_double_price);
        Assert.assertEquals(Item_double_price,Item_New_Price_Expected);
    }

    @SneakyThrows
    public void deleteItem() {
        clickMethod(DELETE_CART);
        String Empty_Message = driver.findElement(CHECK_EMPTY_CART_CLASS).getText();
        System.out.println(Empty_Message);
        Assert.assertEquals(Empty_Message,Expected_Empty_Message);
    }


}
