package API_RestAssured;

import static io.restassured.RestAssured.*; // with static import we can use static methods of RestAssured class without needing to write the classname.
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class demo {
    @Test
    public void demoTest(){
        baseURI = "http://localhost:3000";
        RequestSpecification x = given();
        RequestSpecification y = x.header("Content-Type","application/json");
        Response response = y.get("/users");
        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(),200);
    }

}
