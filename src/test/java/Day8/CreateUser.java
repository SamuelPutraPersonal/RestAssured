package Day8;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class CreateUser {

    @Test
    void test_createUser(ITestContext context){

        Faker faker = new Faker();
        JSONObject data = new JSONObject();

       data.put("name",faker.name().fullName());
        data.put("gender","Male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","inactive");

        String bearerToken = "01dedb3bf28d308502179f73d9fc5b7be576dd9e6693855662d8feb12fc4f53f";

        int id = given()
                .headers("Authorization", "Bearer " + bearerToken)
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");

        System.out.println("Generated id is: " + id);
        //context.setAttribute("user_id", id);
        context.getSuite().setAttribute("user_id", id);

    }
}
