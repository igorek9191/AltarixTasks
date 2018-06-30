package BackEndTask.REST.Countries;

import io.restassured.RestAssured;
import io.restassured.path.json.config.JsonPathConfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.AllureRestAssured;
import utils.LogTestListener;

import java.util.List;

import static io.restassured.RestAssured.given;

@Listeners(LogTestListener.class)
public class RESTTest {

    @BeforeClass
    public void beforeClass(){
        RestAssured.filters(new AllureRestAssured());
    }

    String allCountries = "https://restcountries.eu/rest/v2/all";
    String listOfCodes = "https://restcountries.eu/rest/v2/alpha?codes=col;no;ee";

    @Test
    public void getAllCountries() {
        List<String> s = given()
            .contentType("application/json; charset=UTF-8")
            .get(listOfCodes)
            .then()
            .extract().jsonPath(new JsonPathConfig("name.topLevelDomain"));
        for(String a : s) System.out.println(a);
    }

}
