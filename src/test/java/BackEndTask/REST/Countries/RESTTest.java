package BackEndTask.REST.Countries;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.AllureRestAssured;
import utils.DBmethods;
import utils.LogTestListener;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static utils.DBmethods.getColumnValueFromCountries;
import static utils.DBmethods.getCountryByCallingCode;

@Listeners(LogTestListener.class)
public class RESTTest {

    @BeforeClass
    public void beforeClass(){
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    public void compareGermanyCountryCodes() throws SQLException {

        String restResult = given()
            .contentType("application/json; charset=UTF-8")
            .get("https://restcountries.eu/rest/v2/name/Germany")
            .then()
            .assertThat().body(not(nullValue()))
            .extract().path("[0].alpha3Code");

        String dbResult = getColumnValueFromCountries("Code", "Germany");

        assertEquals(restResult, dbResult);

    }

    @Test
    public void compareUKCountryCodes() throws SQLException {

        String restResult = given()
                .contentType("application/json; charset=UTF-8")
                .get("https://restcountries.eu/rest/v2/name/United Kingdom")
                .then()
                .assertThat().body(not(nullValue()))
                .extract().path("[0].alpha3Code");

        String dbResult = getColumnValueFromCountries("Code", "United Kingdom");

        assertEquals(restResult, dbResult);

    }

    @Test
    public void compareGermanyCallingCode() throws SQLException {

        String restResult = given()
                .contentType("application/json; charset=UTF-8")
                .get("https://restcountries.eu/rest/v2/name/Germany")
                .then()
                .assertThat().body(not(nullValue()))
                .extract().path("[0].callingCodes[0]");

        String dbResult = getColumnValueFromCountries("Calling_Code", "Germany");

        assertEquals(restResult, dbResult);

    }

    @Test
    public void compareUKCallingCode() throws SQLException {

        String restResult = given()
                .contentType("application/json; charset=UTF-8")
                .get("https://restcountries.eu/rest/v2/name/United Kingdom")
                .then()
                .assertThat().body(not(nullValue()))
                .extract().path("[0].callingCodes[0]");

        String dbResult = getColumnValueFromCountries("Calling_Code", "United Kingdom");

        assertEquals(restResult, dbResult);

    }

    @Test
    public void compareCountryNamesByCallingCode() throws SQLException {

        String restResult = given()
                .contentType("application/json; charset=UTF-8")
                .get("https://restcountries.eu/rest/v2/callingcode/49")
                .then()
                .assertThat().body(not(nullValue()))
                .extract().path("[0].name");

        String dbResult = getCountryByCallingCode(49);

        assertEquals(restResult, dbResult);

    }


}
