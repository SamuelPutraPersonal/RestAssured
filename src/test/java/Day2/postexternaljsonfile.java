package Day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class postexternaljsonfile {
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
    void testPostUsingHashMap() throws FileNotFoundException {
        File f = new File("C:\\Users\\Dianpermanapu\\IdeaProjects\\RestAssured\\body.json");

        if (!f.exists()) {
            System.out.println("File not found: " + f.getAbsolutePath());
        }

        FileReader fr = new FileReader(f);

        JSONTokener jt = new JSONTokener(fr);

        JSONObject data =new JSONObject(jt);


        given()
                .contentType("application/json")
                .body(data.toString())

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
