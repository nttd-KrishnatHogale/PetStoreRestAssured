package api.endpoints;

import api.payload.User;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.awaitility.Awaitility;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
//import static io.restassured.RestAssured.when;

public class UserEndpoints2 {

    //method created for getting urls from properties file
    static  ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes"); //load properties file
        return routes;
    }
    public  static Response createUser(User payload){
        String post_url = getURL().getString("post_url");
        Response response = given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);
        return response;
    }

    public  static Response readUser(String  username){
        final Response[] responseHolder = new Response[1];

        Awaitility.await()
                .atMost(10, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until(() ->
        {
        String get_url = getURL().getString("get_url");
        Response response = given()
                .filter(new AllureRestAssured())
                .pathParam("username",username)
                .when()
                .get(get_url);
            responseHolder[0] = response;

            System.out.println(get_url+username);
            return response.getStatusCode() == 200;
        });
        return responseHolder[0];

    }

    public  static Response updateUser(String  username, User payload){
        final Response[] responseHolder = new Response[1];
        Awaitility.await()
                .atMost(10, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until(() ->
                {
        String update_url = getURL().getString("update_url");
        Response response = given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("username",username)
                .when()
                .put(update_url);
                    responseHolder[0] = response;

                    return response.getStatusCode() == 200;
                });
        return responseHolder[0];

    }

    public  static Response deleteUser(String  username) {
        final Response[] responseHolder = new Response[1];
        Awaitility.await()
                .atMost(10, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until(() ->
                {
                    String delete_url = getURL().getString("delete_url");
                    Response response = given()
                            .filter(new AllureRestAssured())
                            .pathParam("username", username)
                            .when()
                            .delete(delete_url);
                    responseHolder[0] = response;

                    return response.getStatusCode() == 200;
                });
        return responseHolder[0];
    }
}
