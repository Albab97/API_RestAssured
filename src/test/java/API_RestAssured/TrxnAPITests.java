package API_RestAssured;

import Models.Request.TrxnRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TrxnAPITests {
    @Test(description = "Verify trxn are added properly")
    public void addTrxnTests(){
        // trxnData is written here in a clean and readable way using builder design pattern and hence we no need to remember the constructor parameter and type to create complex payloads.
        TrxnRequest trxnData = new TrxnRequest.Builder().id("txn-4008")
                .amount(2000)
                .currency("INR")
                .status("SUCCESS")
                .processedAt("12-10-2026-23:30")
                .orderId("ord-3008")
                .build();
        TrxnService trxnService = new TrxnService();
        Response response = trxnService.postTrxns(trxnData);

        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(),201);
    }

}
