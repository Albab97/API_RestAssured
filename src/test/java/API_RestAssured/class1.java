package API_RestAssured;

import APIProject.POJO_PostReq;
import APIProject.POJO_PostReq_Address;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Instant;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.*;

public class class1 {

    @Test
    public void getUsers(){
        Response res =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get("http://localhost:3000/users");

        System.out.println("Status code: "+res.getStatusCode());
        System.out.println("Data is:");
        System.out.println(res.asString()); // toString() gives you object reference as String but asString() is used for the purpose of getting the actual response as string
    }

    // Post request using POJO class.
    @Test
    public void postUser(){
        POJO_PostReq data = new POJO_PostReq();
        data.setId("usr-1003");
        data.setFirstName("Alex");
        data.setLastName("Carrey");
        data.setEmail("alexcarrey90@xyz.com");
        data.setPhone("+91-9999999991");
        data.setStatus("ACTIVE");
        String[] roles = new String[] {"ADMIN"};
        data.setRoles(roles);
        data.setCreatedAt(Instant.now().toString());

        POJO_PostReq_Address[] addresses = new POJO_PostReq_Address[1];
        addresses[0] = new POJO_PostReq_Address();
        addresses[0].setId("addr-2");
        addresses[0].setType("HOME");
        addresses[0].setCity("Noida");
        addresses[0].setCountry("India");
        addresses[0].setState("UP");
        addresses[0].setIsDefault(true);

        data.setAddresses(addresses);

        Response res =
                given()
                        .contentType(ContentType.JSON)
                        .body(data)
                        .when()
                        .post("http://localhost:3000/users");

        System.out.println("Status code: "+res.getStatusCode());
        System.out.println("Data posted is: ");
        System.out.println(res.asString());
    }

    //POST req using Org.JSON class
    @Test
    public void postReqWithOrgJSON(){
        JSONObject info = new JSONObject();
        info.put("id","ord-3003");
        info.put("userId","usr-1003");
        info.put("status","DELIVERED");
        info.put("createdAt",Instant.now().toString());

        JSONArray items = new JSONArray();

        JSONObject item1 = new JSONObject();
        item1.put("productId","prd-2003");
        item1.put("quantity","2");
        item1.put("price","8999");
        JSONObject item2 = new JSONObject();
        item2.put("productId","prd-2004");
        item2.put("quantity","1");
        item2.put("price","2999");

        items.put(0,item1);
        items.put(1,item2);

        info.put("items",items);
        info.put("totalAmount","11998");
        info.put("currency","INR");

        JSONObject payment = new JSONObject();
        payment.put("method","CARD");
        payment.put("status","SUCCESS");

        info.put("payment",payment);

        Response res =
                given()
                        .contentType(ContentType.JSON)
                        .body(info.toString())
                        .when()
                        .post("http://localhost:3000/orders");

        System.out.println("Status Code is "+res.getStatusCode());
        System.out.println("Data passed is :");
        System.out.println(res.asString());
    }
    //POST request using existing JSON file
    @Test
    public void postReqUsingExistingJSON() throws FileNotFoundException {
        File f = new File("../API_RestAssured/Body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject jo = new JSONObject(jt);
        Response res =
                given()
                        .contentType(ContentType.JSON)
                        .body(jo.toString())
                        .when()
                        .post("http://localhost:3000/transactions");
        System.out.println("Status code : "+ res.getStatusCode());
        System.out.println("Data Posted: ");
        System.out.println(res.asString());
    }
    // POST req using existing JSON file with JSON Variables
    @Test(dataProvider = "dp")
    public void postReqUsingExistingJSONWithVariables(String prd_id,String prd_name,String prd_category,String prd_price) throws FileNotFoundException {
        File f = new File("../API_RestAssured/JSONVariables.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject jo = new JSONObject(jt);

        String Data = jo.toString();
        Data = Data.replaceAll("prd_ID",prd_id);
        Data = Data.replaceAll("prd_name",prd_name);
        Data = Data.replaceAll("prd_category",prd_category);
        Data = Data.replaceAll("prd_price",prd_price);

        Response res =
                given()
                        .contentType(ContentType.JSON)
                        .body(Data)
                        .when()
                        .post("http://localhost:3000/products");
        System.out.println("Status code : "+ res.getStatusCode());
        System.out.println("Data Posted: ");
        System.out.println(res.asString());
    }
    @DataProvider(name="dp")
    public Object[][] getData(){
        return new Object[][] {
//                {"prd-2003","Adidas Running Shoes","Fashion","5590"},
                {"prd-2004","Reebok","Fashion","3550"}
        };
    }
}
