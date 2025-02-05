package Day2;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/*
Different ways to create post request body
1 Post Request body using Hashmap
2 Post Request body creation using org.json
3 Post Request body creation using POJO
4 Post Request body creation using external JSON files
 */


public class postHashMap {


    // get request
    @Test (priority = 1)
    void getUser()
    {
        given()
                .when()
                .get("http://localhost:3000/students")
                .then()
                .log().all();
    }

    // 1) Post Request body using Hashmap
    @Test(priority = 2)
    void testPostUsingHashMap()
    {
        HashMap <String, Object> data = new HashMap<>();
        data.put("id", "4");
        data.put("name", "Sam");
        data.put("location", "Indonesia" );
        data.put("phone", "2233");

        String[] courseArr = {"Math", "Java"};
        data.put("courses", courseArr);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Sam"))
                .body("location", equalTo("Indonesia"))
                .body("phone", equalTo("2233"))
                .body("name", equalTo("Sam"))
                .header("Content-Type", equalTo("application/json"))
                .log().all();
    }

    @Test(priority = 3)
    void testDelete(){
        given()
                .when()
                .delete("http://localhost:3000/students/4" )
                .then()
                .log().all();
    }
}
