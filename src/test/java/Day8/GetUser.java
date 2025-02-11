package Day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetUser {

    @Test
    void test_getUser(ITestContext context) {
        //int id = (int) context.getAttribute("user_id"); // this should come from create User request
        int id = (int) context.getSuite().getAttribute("user_id");


        String bearerToken = "01dedb3bf28d308502179f73d9fc5b7be576dd9e6693855662d8feb12fc4f53f";

        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .pathParams("id", id)

                .when()
                .get("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().all();


    }
}
