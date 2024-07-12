package Practice;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LE1PointO {

    private String AccessToken;
    public static String title;

    //Declaring the BaseURI
    public void InitiatingURL() {
        RestAssured.baseURI = "https://statefarmstg.sureify.com/";
    }

//Using as a Constructor
//    public LE1PointO() {
//        RestAssured.baseURI = "https://statefarmstg.sureify.com/";
//    }

    public void CommonJSON(String response, String JSONName) {
        JsonObject Object = JsonParser.parseString(response).getAsJsonObject();

        try (FileWriter file = new FileWriter(JSONName)) {
            file.write(Object.toString());
            file.flush();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    //Hitting the LOGIN API
    public String LoginAPIResponse() {
        String LoginAPIResponse = given().contentType("application/x-www-form-urlencoded")
                .formParam("email", "saiteja.peravali+s6@sureify.com").formParam("password", "Test@123")
                .header("Accept", "*/*")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .when().post("api/v3/carriers/1/authorization")
                .then().assertThat().statusCode(200).extract().response().asString();
        CommonJSON(LoginAPIResponse, "loginAPI.json");

        //Storing the Access Token variable
        JsonPath js = new JsonPath(LoginAPIResponse);
        AccessToken = js.getString("data.access_token");
        System.out.println("Access Token: " + AccessToken);

        return AccessToken;
    }

    //Hitting the SlideMenu API
    public void SlideMenuAPI() {
        given().header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("access-token", AccessToken)
                .when().get("api/v3/carriers/1/menu/config")
                .then().assertThat().statusCode(200);
    }

    //Hitting the Dashboard API
    public String DashboardAPIResponse() {
        String DashboardAPIResponse = given().header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("access-token", AccessToken)
                .when().get("api/v5/carriers/1/dashboard")
                .then().assertThat().statusCode(200).extract().response().asString();
        CommonJSON(DashboardAPIResponse, "DashboardAPI.json");

        JsonPath js1 = new JsonPath(DashboardAPIResponse);

        List<Map<String, Object>> groups = js1.getList("data");

        // Initialize variable to store items length
        int itemsLength = 0;
        List<Map<String, Object>> items = null;

        // Iterating through each group to find the group with id 290020
        for (Map<String, Object> group : groups) {
            String groupValueId = group.get("group_value_id").toString();
            if ("290020".equals(groupValueId)) {
                items = (List<Map<String, Object>>) group.get("items");
                itemsLength = items.size();
                break; // Break the loop once the group is found
            }
        }

        // Output the length of items array for the group with id 290020
        System.out.println("Items Length for group id 290020: " + itemsLength);

        title = null;

        // Check if items list is not null and then iterate through the items
        if (items != null) {
            for (int i = 0; i < itemsLength; i++) {
                Map<String, Object> item = items.get(i);
                int memType = (int) item.get("mem_type");

                if (memType == 34001) {
                    title = (String) item.get("title");
                    break; // Break the loop once the first matching item is found
                }
            }
        } else {
            System.out.println("Group with id 290020 not found or it has no items.");
        }

        // Output the title of the first matching item
        if (title != null) {
            System.out.println("Title of the first item with mem_type 34001: " + title);
        } else {
            System.out.println("No item with mem_type 34001 found in group id 290020.");
        }
        return title;
    }
}
