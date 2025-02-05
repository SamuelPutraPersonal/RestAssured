package Day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class CookiesDemo {

    @Test(priority = 1)
    void testCookie() {
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                //.cookie("AEC", "AVcja2c_FOTaNcnAs3Kx_SfBDeAWGaXvcCFnlv3fa_SHeg7n80KY8prBJJM")
                .log().all();
    }

    @Test(priority = 2)
    void getCookiesInfo() {
        Response res = given()
                .when()
                .get("https://www.google.com/");

//        Get single cookie info
//        String cookie_value = res.getCookie("AEC");
//        System.out.println("The value cookie is: " + cookie_value);

        // Get all cookies info
        Map<String,String> cookies_values=res.getCookies();
        //System.out.println(cookies_values.keySet());

        for (String key : cookies_values.keySet())
        {
            String value =cookies_values.get(key);
            System.out.println("Cookie name: " + key + " value: " + value);
        }

    }
}
