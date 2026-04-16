package API_RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class class2 {
    @Test
    public void responseParsingJSON(){
        Response res = given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get("http://dummy.restapiexample.com/api/v1/employees");

//        System.out.println(res.asString());

        //Using jsonPath()
        String employeeName = res.jsonPath().get("data[5].employee_name");
//        System.out.println(employeeName);

        //Using JSONObject (org.json)
        JSONObject jo = new JSONObject(res.asString());
//        String emp_name = jo.getJSONArray("data").getJSONObject(5).get("employee_name").toString();
//        System.out.println(emp_name);
        for (int i = 0; i < jo.getJSONArray("data").length(); i++) {
            String record = jo.getJSONArray("data").getJSONObject(i).get("employee_name").toString();
            System.out.println(record);
        }

//      NOTE:  It doesn’t matter in context to the concept of response parsing whether the data is in the form of JSONObject or JSONArray we have further
//             methods like .getJSONObject(), .getJSONArray() further to parse our target data.
    }

    @Test
    public void resParsingJSON(){
        Response res =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get("http://localhost:3000/products");
        JSONArray ja = new JSONArray(res.asString());
//        String name = ja.getJSONObject(0).get("name").toString();
//        System.out.println(name);
        for (int i = 0; i < ja.length(); i++) {
            String name = ja.getJSONObject(i).get("name").toString();
            System.out.println(name);
        }
    }
}
