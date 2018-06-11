package Back_end_task.SOAP;

import com.jayway.restassured.path.xml.XmlPath;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class Tests {

    @Test
    public void soapTest() throws IOException {

        String content = new String(Files.readAllBytes(Paths.get("src/test/resources/getAllDict.xml")));
        String pathToEachDictionary = "Envelope.Body.getAllDictResponse.ehdDictionaries.ehdDictionary";

        String response = given()
            .contentType("text/xml;charset=UTF-8")
            .header("username", "asu_ods2")
            .header("password", "ehsnH4")
            .body(content)
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
