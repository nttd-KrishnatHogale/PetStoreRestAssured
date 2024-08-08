package api.endpoints;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.awaitility.Awaitility;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
//import static io.restassured.RestAssured.when;

public class PetEndpoints {
    public  static Response createPet(Pet payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url_pet);
        return response;

    }
    public  static Response readPet(Integer id){
        // Define a variable to hold the response
        final Response[] responseHolder = new Response[1];
        Awaitility.await()
                .atMost(10, TimeUnit.SECONDS) // Maximum wait time
                .pollInterval(1, TimeUnit.SECONDS) // Poll interval
                .until(() -> {
                    Response response = given()
                            .pathParam("id", id)
                            .when()
                            .get(Routes.get_url_pet);
                    // Store the response in the holder
                    responseHolder[0] = response;
                    // Log the URL and ID
                    System.out.println(Routes.get_url_pet.toString() + id);
                    // Return true if the status code is 200, otherwise false
                    return response.getStatusCode() == 200;
                });

        // Return the final response after waiting
        return responseHolder[0];
    }

    public  static Response updatePet(Integer id, Pet payload){
        final Response[] responseHolder = new Response[1];
        Awaitility.await()
                .atMost(10, TimeUnit.SECONDS) // Maximum wait time
                .pollInterval(1, TimeUnit.SECONDS) // Poll interval
                .until(() -> {
        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .accept(ContentType.JSON)
//                .body(payload)
                .formParam("name",payload.getName())
                .formParam("status",payload.getStatus())
                .pathParam("id",id)
                .when()
                .post(Routes.update_url_pet);
                    responseHolder[0] = response;
                    return response.getStatusCode() == 200;
                });

        // Return the final response after waiting
        return responseHolder[0];
    }
    public  static Response deletePet(Integer id){
        final Response[] responseHolder = new Response[1];
        Awaitility.await()
                .atMost(10, TimeUnit.SECONDS) // Maximum wait time
                .pollInterval(1, TimeUnit.SECONDS) // Poll interval
                .until(() -> {
        Response response = given()
                .pathParam("id",id)
                .when()
                .delete(Routes.delete_url_pet);
        System.out.println(Routes.delete_url_pet+id);
        responseHolder[0] = response;
        return response.getStatusCode() == 200;
    });

    // Return the final response after waiting
        return responseHolder[0];
}


}
