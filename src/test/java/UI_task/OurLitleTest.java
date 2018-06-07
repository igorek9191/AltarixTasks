package UI_task;

import UI_task.Pages.MainHeader;
import com.codeborne.selenide.Configuration;

import com.google.gson.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.jayway.restassured.RestAssured.given;

public class OurLitleTest {

    @BeforeClass
    public void beforeClass() {
        Configuration.browser = "chrome";
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        Configuration.openBrowserTimeoutMs = 25000;
    }

    //@Test
    public void test() {
        MainHeader mh = open("http://potolkoff58.ru/", MainHeader.class);
        mh
    //1
        .checkMenu()
    //2
        .goToFotoGalery()
        .checkFotoGaleryTitle()
        .checkTypesOfCeilingsIsActive()
        .checkSubsections_TypesOfCellings()
        .checkRoomTypeWithSubsections()
        .checkChandeliersAndLampsIsActive()
        .checkDirectoryOfColorsIsActive()
    //3
        .goToPriceSheetPage()
        .checkPricesTitle()
        .checkFirstTable()
        .checkCallGaugerLink()
    //4
        .goToCalculatorPage()
        .checkCalculatorTitle()
        .calcPriceWithPhotoPrinting()
        .calcPriceWithoutPhotoPrinting()
    //5
        .goToContactsPage()
        .checkContactsTitle()
        .checkRelationWithCorrectEmail()
        .goToContactsPage()
        .checkRelationWithWrongEmail();
    }

    @Test
    public void hardJSON() {
        // create the albums object
        JsonObject albums = new JsonObject();
        // add a property calle title to the albums object
        albums.addProperty("title", "album1");

        // create an array called datasets
        JsonArray datasets = new JsonArray();

        // create a dataset
        JsonObject dataset = new JsonObject();
        // add the property album_id to the dataset
        dataset.addProperty("album_id", 1);
        // add the property album_year to the dataset
        dataset.addProperty("album_year", 1996);

        datasets.add(dataset);

        albums.add("dataset", datasets);

        // create the gson using the GsonBuilder. Set pretty printing on. Allow
        // serializing null and set all fields to the Upper Camel Case
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        System.out.println(gson.toJson(albums));

    }
}
