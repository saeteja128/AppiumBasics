package Practice;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class sample {
    String AccessToken;

    @Test
    public void response1() {


        RestAssured.baseURI = "https://statefarmstg.sureify.com/";
        // Step 1: Send the First API Request
        String LoginAPIResponse = given().contentType("application/x-www-form-urlencoded").formParam("email", "saiteja.peravali+s6@sureify.com").formParam("password", "Black@123")
                .header("Accept", "*/*")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .when().log().all().post("api/v3/carriers/1/authorization")
                .then().assertThat().statusCode(200).extract().response().asString();

        System.out.println("Login API Response is: " + LoginAPIResponse);

        //Storing the Access Token variable
        JsonPath js = new JsonPath(LoginAPIResponse);
        AccessToken = js.getString("data.access_token");
        System.out.println("Access Token: " + AccessToken);


    }

    @Test
    public void Response2() {
        RestAssured.baseURI = "https://statefarmstg.sureify.com/";
        // Step 2: Send the Second API Request using the extracted access token
        given().log().all()
                .baseUri("https://statefarmstg.sureify.com")
                .header("access-token", AccessToken)
                .when().log().all()
                .get("/api/v3/carriers/1/menu/config")
                .then().log().all()
                .assertThat().statusCode(200); // Assuming 200 is the expected status code


    }
}

