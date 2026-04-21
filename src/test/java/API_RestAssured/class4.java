package API_RestAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class class4 {
    // API chaining in Rest Assured is the process of executing the sequence of API calls where the output(response) of the one
    // request is dynamically extracted and used as an input for the next request.
    // Using GitHub API as an example
    @Test
    public void APIChainingDemo(){
        String token = "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";
        String owner = "Albab97";

        // Step 1 - Create Repo from Post request

        RestAssured.baseURI = "https://api.github.com";

        JSONObject jo = new JSONObject();
        jo.put("name","DemoRepoName2");

        Response response =
                given()
                        .header("Authorization","Bearer "+token)
                        .header("Accept","application/vnd.github+json")
                        .body(jo.toString())
                        .when().post("/user/repos")
                        .then()
                        .statusCode(201)
                        .log().all().extract().response();

        JSONObject job = new JSONObject(response.asString());
        String createdRepoName = job.getString("name");

        // Step - 2 Delete the repo created in first step

        given()
                .header("Accept", "application/vnd.github+json")
                .header("Authorization","Bearer "+token)
                .pathParam("owner",owner)
                .pathParam("repo","DemoRepoName")
                .when().delete("/repos/{owner}/{repo}")
                .then().statusCode(204).log().all();

        // Step - 3 Get request for repo to confirm it is deleted

        given()
                .header("Accept", "application/vnd.github+json")
                .header("Authorization","Bearer "+token)
                .pathParam("owner",owner)
                .pathParam("repo",createdRepoName)
                .when().get("/repos/{owner}/{repo}")
                .then().statusCode(404).log().all();
    }
}
