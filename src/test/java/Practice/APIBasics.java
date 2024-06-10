package Practice;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class APIBasics {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com/";

        //ADDPlace - POST
        String AddPlaceResponse = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(Payload.AddPlace())

                .when().post("/maps/api/place/add/json")

                .then().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("Server", equalTo("Apache/2.4.52 (Ubuntu)"))
                .extract().response().asString();

        System.out.println("AddPlaceResponse: "+AddPlaceResponse);

        JsonPath js = new JsonPath(AddPlaceResponse);
        String place_id = js.getString("place_id");
        System.out.println("place_id: "+place_id);

        //UpdatePlace - PUT API
        String UpdatePlaceResponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\""+place_id+"\",\n" +
                        "\"address\":\"70 Summer walk, USA\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")

                .when().log().all().put("/maps/api/place/update/json")

                .then().log().all().assertThat().statusCode(200)
                .body("msg", equalTo("Address successfully updated"))
                        .extract().response().asString();

        System.out.println("UpdatePlaceResponse: " +UpdatePlaceResponse);
        JsonPath js2 = new JsonPath(UpdatePlaceResponse);
        String UpdateAPIAddress = js2.getString("address");
        System.out.println("UpdateAPIAddress: " +UpdateAPIAddress);

        //GETPlace - GET API
        String GetPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
                .when().log().all().get("maps/api/place/get/json")
                .then().log().all().statusCode(200)
                .extract().response().asString();

        System.out.println("GetPlaceResponse: " +GetPlaceResponse);
        JsonPath js3 = new JsonPath(GetPlaceResponse);
        String GetAPIAddress = js3.getString("address");
        System.out.println("GetAPIAddress: " +GetAPIAddress);


    }
}
