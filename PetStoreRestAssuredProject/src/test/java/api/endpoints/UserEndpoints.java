package api.endpoints;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

//import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;
//import static io.restassured.RestAssured.when;

public class UserEndpoints {
    public  static Response createUser(User payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);

        return response;

    }

    public  static Response readUser(String  username){
        Response response = given()
                .pathParam("username",username)
                .when()
                .get(Routes.get_url);
        return response;

    }

    public  static Response updateUser(String  username, User payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("username",username)
                .when()
                .put(Routes.update_url);
        return response;

    }
}
