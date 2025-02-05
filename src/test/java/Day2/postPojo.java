package Day2;

import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class postPojo {
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
       Pojo_PostRequest data = new Pojo_PostRequest();
       data.setName("Sam");
       data.setLocation("Indonesia");
       data.setPhone("1234567890");
       data.setId("4");

        String[] courseArr = {"Math", "Java"};
        data.setCourses(courseArr);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Sam"))
                .body("location", equalTo("Indonesia"))
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
