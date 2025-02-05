package Day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class HeadersDemo {

    @Test(priority = 1)
    void testHeaders() {
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .header("Content-Encoding", "gzip")
                .header("Server", "gws");
    }

    @Test(priority = 2)
    void getHeaders() {
        Response res = given()
                .when()
                .get("https://www.google.com/");

        //get single info
        String headerValue = res.getHeader("Content-Type");
        System.out.println("The value of the Content Type: " + headerValue);

        //get all headers info
         Headers myheaders = res.getHeaders();
         for(Header hd : myheaders)
         {
             System.out.println(hd.getName() + "  " + hd.getValue());
         }


    }
}
