package api.endpoints;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

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
        Response response = given()
                .pathParam("id",id)
                .when()
                .get(Routes.get_url_pet);
        return response;
    }
    public  static Response updatePet(Integer id, Pet payload){
        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .accept(ContentType.JSON)
//                .body(payload)
                .formParam("name",payload.getName())
                .formParam("status",payload.getStatus())
                .pathParam("id",id)
                .when()
                .post(Routes.update_url_pet);
        return response;
    }
    public  static Response deletePet(Integer id){
        Response response = given()
                .pathParam("id",id)
                .when()
                .delete(Routes.delete_url_pet);
        return response;

    }


}
