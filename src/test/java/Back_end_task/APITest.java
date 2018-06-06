package Back_end_task;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by IVlasyuk on 22.04.2018.
 */
//@Loggable(limit = 5, unit = TimeUnit.MILLISECONDS)

public class APITest {

    String basketName = "igorek3465";
    String token = null;

    //private final static Logger LOG = LogManager.getLogger(APITest.class);

    @Test
    public void test() {
        createBasket();
        getBasket();
        editBasket();
        deleteBasket();
    }

    public void createBasket() {

        Map<String,Object> parameters = new HashMap<String,Object>();
        parameters.put("forward_url","https://www.yandex.ru/");
        parameters.put("insecure_tls",true);
        parameters.put("expand_path",true);
        parameters.put("capacity",1);

        token = given()
            .contentType("application/json; charset=UTF-8")
            .body(parameters)
            .when().post("https://rbaskets.in/baskets/"+basketName)
            .then()
            .log().all()
            .assertThat().statusCode(201)
            .extract().path("token");
    }

    public void getBasket() {

        given()
            .header("Authorization", token)
            .when().get("https://rbaskets.in/baskets/"+basketName)
            .then()
            .log().all()
            .assertThat().body("forward_url", equalTo("https://www.yandex.ru/"))
            .assertThat().body("insecure_tls", equalTo(true))
            .assertThat().body("expand_path", equalTo(true))
            .assertThat().body("capacity", equalTo(1));
    }

    public void editBasket() {

        Map<String,Object> parameters = new HashMap<String,Object>();
        parameters.put("forward_url","https://www.ya.ru/");
        parameters.put("insecure_tls",false);
        parameters.put("expand_path",false);
        parameters.put("capacity",200);

        given()
            .contentType("application/json; charset=UTF-8")
            .header("Authorization", token)
            .body(parameters)
            .when().put("https://rbaskets.in/baskets/"+basketName)
            .then()
            .log().all()
            .assertThat().statusCode(204);
    }

    public void deleteBasket() {
        given()
            .header("Authorization", token)
            .when().delete("https://rbaskets.in/baskets/"+basketName)
            .then()
            .log().all()
            .assertThat().statusCode(204);
    }

}
