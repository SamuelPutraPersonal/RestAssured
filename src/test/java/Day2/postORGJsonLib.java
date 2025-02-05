package Day2;

import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class postORGJsonLib {

/*
Different ways to create post request body
1 Post Request body using Hashmap
2 Post Request body creation using org.json
3 Post Request body creation using POJO
4 Post Request body creation using external JSON files
 */


    // get request
    @Test(priority = 1)
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
        JSONObject data = new JSONObject();
        data.put("id", "4");
        data.put("name", "John");
        data.put("location", "USA");
        data.put("phone", "1234567890");

        String[] courseArr = {"Math", "Java"};
        data.put("courses", courseArr);

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("John"))
                .body("location", equalTo("USA"))
                .body("phone", equalTo("1234567890"))
                .body("id", equalTo("4"))
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
