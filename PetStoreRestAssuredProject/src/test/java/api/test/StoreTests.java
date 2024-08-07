package api.test;

import api.endpoints.PetEndpoints;
import api.endpoints.StoreEndpoints;
import com.github.javafaker.Faker;
import api.payload.Store;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.time.LocalDateTime;
import java.util.Random;

public class StoreTests {
Faker faker;
Store storePayload;
//    public Logger logger;

    void setUpData(){
        faker = new Faker();
        storePayload = new Store();
        storePayload.setId(faker.number().numberBetween(1,10));
        storePayload.setPetID(faker.number().numberBetween(1,10));
        storePayload.setQuantity(faker.number().numberBetween(1,10));
        storePayload.setMyObj(LocalDateTime.now().minusDays(30));
        storePayload.setStatus(faker.options().option("Pending", "Completed", "Cancelled"));
//        logger = LogManager.getLogger(this.getClass());

    }

    @Test(priority = 1)
    public void testPostOrder(){
//        logger.info("********* Creating Pet***********");
        System.out.println(storePayload.getId());
        System.out.println(storePayload.getPetID());
        System.out.println(storePayload.getQuantity());
        Response response = StoreEndpoints.createOrder(storePayload);
        response.then().log().all();
        response.getBody().asString();
        Integer id = response.jsonPath().getInt("id");
        System.out.println("ID from order request" + id);
        storePayload.setId(id);
        Assert.assertEquals(response.statusCode(), 200);
        try {
            Thread.sleep(5000); // 2 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        logger.info("********pet created***********");

    }
}
