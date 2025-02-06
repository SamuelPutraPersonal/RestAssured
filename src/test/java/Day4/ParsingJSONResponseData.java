package Day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ParsingJSONResponseData {


    @Test(priority = 1)
    void testJsonResponse() {
        // Approach 1
//      given()
//              .contentType("ContentType.JSON")
//              .when()
//              .get("http://localhost:3000/book")
//              .then()
//              .statusCode(200)
//              .body("[3].title", equalTo("The Lord of the Rings"))
//              .log().body();
        // Approach 2
        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/book");


        Assert.assertEquals(res.getStatusCode(), 200); // Validation 1
        Assert.assertEquals(res.header("Content-Type"), "application/json");
        String bookName = res.jsonPath().get("[3].title").toString();
        Assert.assertEquals(bookName, "The Lord of the Rings");


    }


    @Test(priority = 2)
    void testJsonResponseBodyData() {
        // Approach 2
        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/book");

//
//        Assert.assertEquals(res.getStatusCode(),200); // Validation 1
//        Assert.assertEquals(res.header("Content-Type"), "application/json");
//        String bookName = res.jsonPath().get("[3].title").toString();
//        Assert.assertEquals(bookName, "The Lord of the Rings");

        JSONArray jsonArray = new JSONArray(res.asString()); // Convert response to JSONArray
        boolean status =false;
        for (int i = 0; i < jsonArray.length(); i++) {
            String bookTitle = jsonArray.getJSONObject(i).getString("title");
            System.out.println(bookTitle);
            if(bookTitle.equals("The Lord of the Rings"))
            {
                status=true;
                System.out.println("Status is: " + status);
                break;
            }
        }
        Assert.assertEquals(status,true);


        // Validate total price of books
        double totalPrice = 0;
        for (int i = 0; i < jsonArray.length(); i++) {
            double price = jsonArray.getJSONObject(i).getDouble("price"); // FIXED HERE
            totalPrice += price;
        }

        System.out.println("Total Price: " + totalPrice);
        Assert.assertEquals(totalPrice, 526.0);
    }
    }
