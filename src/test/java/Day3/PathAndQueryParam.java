package Day3;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PathAndQueryParam {

    //https://reqres.in/api/users?page=2&id=5

    @Test
    void testQueryAndPath(){
        given()
                .pathParams("mypath", "users") // path params
                .queryParam("page","2")
                .queryParam("id","5")

                .when()
                .get("https://reqres.in/api/{mypath}")

                .then()
                .statusCode(200)
                .log().all();
    }
}
