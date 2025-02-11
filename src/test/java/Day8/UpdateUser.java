package Day8;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUser {

    @Test
    void tes_updateUser(ITestContext context) {
        Faker faker = new Faker();
        JSONObject data = new JSONObject();

        data.put("name", faker.name().fullName());
        data.put("gender", "Male");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "active");

        String bearerToken = "01dedb3bf28d308502179f73d9fc5b7be576dd9e6693855662d8feb12fc4f53f";
        //int id = (int) context.getAttribute("user_id");
        int id = (int) context.getSuite().getAttribute("user_id");
        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .contentType("application/json")
                .body(data.toString())
                .pathParam("id", id)
                .when()
                .put("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
