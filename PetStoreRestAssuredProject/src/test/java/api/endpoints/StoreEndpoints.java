package api.endpoints;

import api.payload.Pet;
import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StoreEndpoints {
    public  static Response createOrder(Store payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url_store);
        return response;

    }
    public  static Response readOrder(Integer id){
        Response response = given()
                .pathParam("id",id)
                .when()
                .get(Routes.get_url_store);
        System.out.println(Routes.get_url_store.toString()+id);
        return response;
    }
    public  static Response getInventory(){
        Response response = given()
//                .contentType("application/x-www-form-urlencoded")
//                .accept(ContentType.JSON)
////                .body(payload)
//                .formParam("name",payload.getName())
//                .formParam("status",payload.getStatus())
//                .pathParam("id",id)
                .when()
                .get(Routes.get_url_store_inventory);
        return response;
    }
    public  static Response deleteOrder(Integer id){
        Response response = given()
                .pathParam("id",id)
                .when()
                .delete(Routes.delete_url_store);
        System.out.println(Routes.delete_url_store+id);
        return response;

    }

}
