package Day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HTTPRequests {

    int id;
    @Test(priority = 1)
    void getUsers()
    {
        given()

                .when()
                .get("https://reqres.in/api/users?page=2")

                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();  // Logs full response
    }


    @Test(priority = 2)
    void createUser()
    {
        HashMap<String,String> data = new HashMap<>();
        data.put("name","Sam29019");
        data.put("job","teacher");

                id =given()
                        .contentType("application/json")
                        .body(data)
                .when()
                        .post("https://reqres.in/api/users")
                        .jsonPath().getInt("id");
//                .then()
//                        .statusCode(201)
//                        .log().all();
    }

    @Test(priority = 3, dependsOnMethods = {"createUser"})
    void updateUser()
    {
        HashMap<String,String> data = new HashMap<>();
        data.put("name","SamPutra");
        data.put("job","developer");

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .put("https://reqres.in/api/users/" + id)

                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 4)
    void deleteUser()
    {
        given()
                .when()
                .delete("https://reqres.in/api/users/" +id)

                .then()
                .statusCode(204)
                .log().all();
    }

}
