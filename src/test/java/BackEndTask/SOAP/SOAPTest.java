package BackEndTask.SOAP;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.AllureRestAssured;
import utils.LogTestListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
@Listeners(LogTestListener.class)
public class SOAPTest {

    @BeforeClass
    public void beforeClass() {
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    public void soapTest() throws IOException {

        String pathToEachDictionary = "Envelope.Body.getAllDictResponse.ehdDictionaries.ehdDictionary";

        String response = given()
            .contentType("text/xml;charset=UTF-8")
            .header("username", "asu_ods2")
            .header("password", "ehsnH4")
            .body(Files.readAllBytes(Paths.get("src/test/resources/getAllDict.xml")))
            .when().post("http://op.mos.ru:80/EHDWS/soap")
            .then()
            .log().all()
            .statusCode(200)
            .assertThat().body(notNullValue())
            .extract().response().asString();

        XmlPath xmlPath = new XmlPath(response);

        assertThat("Assert that first ID is 60", xmlPath.get(pathToEachDictionary + ".id[0]"), is("60"));

        List<String> assertionList = xmlPath.getList(pathToEachDictionary + ".id");
        List<String> myList = Arrays.asList("60", "61", "89", "1118", "1119", "1120", "2270");

        assertEquals(assertionList, myList, "Assertion of two lists");
        for (Object o : xmlPath.getList(pathToEachDictionary))
            System.out.println(o.toString());

    }

}
