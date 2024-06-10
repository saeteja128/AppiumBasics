package Practice;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class LE2PointO {
    public static void main(String[] args)
    {
        RestAssured.baseURI = "https://statefarm-stg-api.sureify.com/";

        String OATHResponse = given().header("Cookie", "__cf_bm=UDJ1vQqSHlJ5Agj5PqPdq1del0miMQHDF7bKgHeA4N4-1709733091-1.0.1.1-uv6MaWDX9RxzewKFPo5WRp.15ipXBBmq85pWHdyOwIQEHTqanqvkPij3.u0pskCrPW6EIZBxQMc4Q16PSQ5hLA")
                        .header("Content-Type", "application/x-www-form-urlencoded").body(Payload.LE2())
                        .when().post("v1/oauth/token")
                        .then().assertThat().statusCode(200).extract().response().asString();

        System.out.println("OathResponse: " +OATHResponse);

        JsonPath js = new JsonPath(OATHResponse);
        String JWTToken = js.getString("data.jwt_token");
        System.out.println("JWT Token: "+JWTToken);

        given().log().all().queryParam("email", "saiteja.peravali+s6@sureify.com").queryParam("password","Black@123").header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + JWTToken)
                .body(Payload.LoginBody())
                        .when().log().all().post("v1/authorization")
                        .then().log().all();

    }
}
