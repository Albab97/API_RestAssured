package API_RestAssured;

import Models.Request.TrxnRequest;
import io.restassured.response.Response;

public class TrxnService extends BaseService{
    private static final String BASE_PATH = "/transactions";

    public Response postTrxns(TrxnRequest payload){
        return postRequest(payload,BASE_PATH);
    }
}
