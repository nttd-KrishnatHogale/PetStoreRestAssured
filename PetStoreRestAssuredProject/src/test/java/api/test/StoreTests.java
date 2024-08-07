package api.test;

import com.github.javafaker.Faker;
import api.payload.Store;

import java.time.LocalDateTime;
import java.util.Random;

public class StoreTests {
Faker faker;
Store storePayload;
    void setUpData(){
        faker = new Faker();
        storePayload = new Store();
        storePayload.setId(faker.number().numberBetween(1,10));
        storePayload.setPetID(faker.number().numberBetween(1,10));
        storePayload.setQuantity(faker.number().numberBetween(1,10));
        storePayload.setMyObj(LocalDateTime.now().minusDays(30));
        storePayload.setStatus(faker.options().option("Pending", "Completed", "Cancelled"));


    }
}
