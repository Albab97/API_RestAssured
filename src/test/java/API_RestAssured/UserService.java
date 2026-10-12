package API_RestAssured;

import Models.Request.UserRequest;
import io.restassured.response.Response;

public class UserService extends BaseService {
    private static final String BASE_PATH = "/users";

    public Response postUser(UserRequest payload){
        return postRequest(payload,BASE_PATH);
    }
}
