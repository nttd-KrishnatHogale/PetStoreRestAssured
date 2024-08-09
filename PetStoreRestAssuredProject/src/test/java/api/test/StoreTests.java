package api.test;

import api.endpoints.StoreEndpoints;
import com.github.javafaker.Faker;
import api.payload.Store;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class StoreTests {
public  Faker faker;
public Store storePayload;
//    public Logger logger;

    @BeforeClass
    public void setUpData(){
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        faker = new Faker();
        storePayload = new Store();
        storePayload.setId(faker.number().numberBetween(1,10));
        storePayload.setPetID(faker.number().numberBetween(1,10));
        storePayload.setQuantity(faker.number().numberBetween(1,10));
        storePayload.setMyObj(localDate.atStartOfDay());
        storePayload.setStatus(faker.options().option("Pending", "Completed", "Cancelled"));
//        logger = LogManager.getLogger(this.getClass());

    }

    @Test(priority = 1)
    public void testPostOrder(){
//        logger.info("********* Creating Pet***********");
        Response response = StoreEndpoints.createOrder(storePayload);
        response.then().log().all();
        response.getBody().asString();
        Integer id = response.jsonPath().getInt("id");
        System.out.println("ID from order request" + id);
        storePayload.setId(id);
        Assert.assertEquals(response.statusCode(), 200);
//        logger.info("********pet created***********");

    }

    @Test(priority = 2)
    public void testGetOrderById() throws InterruptedException {
//        logger.info("****GEt Pet*******");
//        System.out.println("petid in testGetPet " + this.petPayload.getId());
        Response response = StoreEndpoints.readOrder(this.storePayload.getId());
        response.then().log().all();
        response.statusCode();
        Assert.assertEquals(response.getStatusCode(), 200);
        try {
            Thread.sleep(5000); // 2 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Thread.sleep(2000); // Delay for 2000 milliseconds (2 seconds)
//        logger.info("********** Get Pet Complted ****");
    }


    @Test(priority = 3)
    public void testGetInventory() throws InterruptedException {
//        logger.info("****GEt Pet*******");
//        System.out.println("petid in testGetPet " + this.petPayload.getId());
        Response response = StoreEndpoints.getInventory();
        response.then().log().all();
        response.statusCode();
        Assert.assertEquals(response.getStatusCode(), 200);
        //        logger.info("********** Get Pet Complted ****");
    }

    @Test(priority = 4)
    public void testDeletePetById() {
//        logger.info("***** delete pet*****");
        System.out.println("id in delete "+this.storePayload.getId());
        Response response = StoreEndpoints.deleteOrder(this.storePayload.getId());
        Assert.assertEquals(response.getStatusCode(), 200);

//        logger.info("***8 pet deleted*******");
    }
}
