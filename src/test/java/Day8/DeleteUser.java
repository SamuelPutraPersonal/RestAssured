package Day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUser {

    @Test
    void test_userDelete(ITestContext context) {
        String bearerToken = "01dedb3bf28d308502179f73d9fc5b7be576dd9e6693855662d8feb12fc4f53f";

        //int id = (int) context.getAttribute("user_id");
        int id = (int) context.getSuite().getAttribute("user_id");
        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .pathParams("id", id)

                .when()
                .delete("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(204)
                .log().all();

    }
}
