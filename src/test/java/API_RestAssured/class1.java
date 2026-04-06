package API_RestAssured;

import APIProject.POJO_PostReq;
import APIProject.POJO_PostReq_Address;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.time.Instant;

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
    public void postUserWithOrgJSON(){
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
}
