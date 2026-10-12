package API_RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService {
    // It is like a wrapper for Rest Assured !
    // It is used to reduce redundancy by implementing common methods for each class like : BaseURI, creating the request specification and handling the response.
    private static final String BASE_URL = "http://localhost:3000/";
    private RequestSpecification requestSpecification;
    public BaseService(){
        requestSpecification = RestAssured.given().baseUri(BASE_URL); // initializing request specification for each class
    }
    protected Response postRequest(Object payload, String endpoint){  // This is a generalized method and we cannot directly pass an object of POJO_PostReq class here, instead we can pass its Parent class which is Object. Hence, it makes the method as loosly coupled.
        return requestSpecification.contentType(ContentType.JSON).body(payload).post(endpoint);
    }
}
