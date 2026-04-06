package API_RestAssured;

import APIProject.POJO_PostReq;
import APIProject.POJO_PostReq_Address;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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

        POJO_PostReq_Address address = new POJO_PostReq_Address();
        address.setId("addr-2");
        address.setType("HOME");
        address.setCity("Noida");
        address.setCountry("India");
        address.setState("UP");
        address.setIsDefault(true);

        data.setAddress(address);

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
}
