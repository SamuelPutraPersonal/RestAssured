package Day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoggingDemo {

    @Test(priority = 1)
    void testLogs() {
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .log().headers();
    }
}
