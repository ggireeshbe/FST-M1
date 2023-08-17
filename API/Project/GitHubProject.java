package activities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GitHubProject {
    String ssHKey="ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIBetPufAsYSVf4zQgUKc01cYhcil95vHDAOXBtdx5Uip";
    int sshKeyId;
    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    @BeforeClass
    public  void setup()
    {
        requestSpec= new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://api.github.com/user")
                .addHeader("Authorization","token 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")
                .addHeader("Content-Type","application/json")
                .build();
        responseSpec= new ResponseSpecBuilder()
                .expectResponseTime(lessThan(5000L))
                .expectBody("key",equalTo(ssHKey))
                .expectBody("title",equalTo("TestAPIKey"))
                .build();
    }


    @Test(priority=1)
    public void postsshkey(){
        String reqBody = "{\"title\": \"TestAPIKey\",\"key\": \""+ssHKey+"\"}";
        Response response = given().spec(requestSpec).body(reqBody).when().post("/keys");
        System.out.println(response.asPrettyString());
        sshKeyId=response.getBody().jsonPath().getInt("id");
        response.then().spec(responseSpec);
        response.then().statusCode(201);
    }

    @Test(priority=2)
    public void getsshkey(){
       Response response = given().spec(requestSpec).pathParam("keyId", sshKeyId).when().get("/keys/{keyId}");
        System.out.println(response.asPrettyString());
        response.then().statusCode(200);
    }

    @Test(priority=3)
    public void deletekey(){
        Response response = given().spec(requestSpec).pathParam("keyId", sshKeyId).when().delete("/keys/{keyId}");
        System.out.println(response.asPrettyString());
        response.then().statusCode(204);
    }


}
