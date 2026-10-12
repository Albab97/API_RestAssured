package API_RestAssured;

import Models.Request.UserRequest;
import Models.Request.UserRequest_Address;
import Models.Response.UserResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.time.Instant;

public class UserAPITests {
    @Test(description = "verify if items are being added to users")
    public void addUserTest(){
        UserRequest data = new UserRequest();   // We can also write constructor for UserRequest and directly pass all data as parameters (but it would be too clumsy).
        data.setId("usr-1067");
        data.setFirstName("Gyanesh");
        data.setLastName("Kumar");
        data.setEmail("gyanugyanu2@xyz.com");
        data.setPhone("+91-9999999981");
        data.setStatus("ACTIVE");
        String[] roles = new String[] {"ADMIN"};
        data.setRoles(roles);
        data.setCreatedAt(Instant.now().toString());

        UserRequest_Address[] addresses = new UserRequest_Address[1];
        addresses[0] = new UserRequest_Address();
        addresses[0].setId("addr-2");
        addresses[0].setType("HOME");
        addresses[0].setCity("Noida");
        addresses[0].setCountry("India");
        addresses[0].setState("UP");
        addresses[0].setIsDefault(true);

        data.setAddresses(addresses);

        UserService userService = new UserService();
        Response response = userService.postUser(data); // Serialization: Converting the POJO object to JSON . We are able to pass POJO payload directly is because of GSON library in pom.xml
//        System.out.println(response.asPrettyString());

        UserResponse userResponse = response.as(UserResponse.class);    // Deserialization : Converting the JSON response to Java Object (UserResponse class object) done with the help of GSON library.
        System.out.println(userResponse);
        System.out.println(userResponse.getEmail());
    }
}
