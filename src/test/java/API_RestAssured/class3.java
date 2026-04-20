package API_RestAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class class3 {
    // Response validation using .then()
    @Test
    public void responseValidation(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        given()
                .when()
                .get("/todos/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("title",equalTo("delectus aut autem"))
                .body("userId",equalTo(1))
                .log().all();
    }
    // Response validation using Hamcrest Matchers
    @Test
    public void responseValidationUsingHamcrest(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response res = given()
                .when()
                .get("/todos/1")
                .then()
                .extract().response();

        // Validate the response body is not empty
        assertThat(res.getBody().asString(),not(isEmptyString()));

        // Validate that the response body contains a specific value
        assertThat(res.getBody().asString(),containsString("delectus aut autem"));

        // Validate that the response has a specific JSON attribute
        assertThat(res.getBody().jsonPath().get("userId"),equalTo(1));
        assertThat(res.getBody().jsonPath().get("title"),equalTo("delectus aut autem"));
    }
    @Test
    public void responseValidationUsingHamcrest2(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response res = given()
                .when()
                .get("/posts");
//        System.out.println(res.asString());

        // Validate that the response body contains specific items
        assertThat(res.getBody().jsonPath().getList("title"), hasItems("sunt aut facere repellat provident occaecati excepturi optio reprehenderit","qui est esse"));
    }
    @Test
    public void responseValidationUsingHamcrest3(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response res = given()
                .when()
                .get("/comments?postId=1");
        System.out.println(res.asString());

        // Validate the size of response
        assertThat(res.jsonPath().getList(""),hasSize(5));

        // Validate that response body have specific items in a specific order
         String[] arr= {"Eliseo@gardner.biz","Jayne_Kuhic@sydney.com","Nikita@garfield.biz","Lew@alysha.tv","Hayden@althea.biz"};
         assertThat(res.jsonPath().getList("email"),contains(arr));
    }
    @Test
    public void responseValidationUsingHamcrest4(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response res = given()
                .when()
                .get("/users?id=1")
                        .then()
                                .statusCode(200)
                                        .extract().response();

//        System.out.println(res.asString());

        assertThat(res.jsonPath().getList(""),hasSize(1));

        // Validate the details of first user
        res.then().body("name[0]",is("Leanne Graham"));
        res.then().body("address[0].geo.lat",is("-37.3159"));
    }
}
