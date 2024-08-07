package api.test;

import api.endpoints.PetEndpoints;
import api.payload.Pet;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;


public class    PetTests {
    Faker faker;
    Pet petPayload;
    Pet.Category category;
    //    Pet.Tag tags;
    public Logger logger;
    public Integer petid;

    @BeforeClass
    public void setUpData() {
        faker = new Faker();
        petPayload = new Pet();
        Pet.Category category = new Pet.Category();
        category.setId(faker.number().numberBetween(1, 100));
        category.setName(faker.animal().name());
        List<Pet.Tag> tags = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
        Pet.Tag tag = new Pet.Tag();
        tag.setId(faker.number().numberBetween(1, 100));
        tag.setName(faker.lorem().word());
        tags.add(tag);
//        }
        petPayload.setId(faker.idNumber().hashCode());
        petPayload.setCategory(category);
        petPayload.setName(faker.animal().name());
        petPayload.setPhotoUrls(List.of(faker.avatar().image()));
        petPayload.setTags(tags);
        petPayload.setStatus(faker.options().option("available", "pending", "sold"));
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostPet() {
        logger.info("********* Creating Pet***********");
        Response response = PetEndpoints.createPet(petPayload);
        response.then().log().all();
        response.getBody().asString();
        Integer petid = response.jsonPath().getInt("id");
        System.out.println("ID from psit request" + petid);
        petPayload.setId(petid);
        Assert.assertEquals(response.statusCode(), 200);
        try {
            Thread.sleep(5000); // 2 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("********pet created***********");
    }

    @Test(priority = 2)
    public void testGetPetById() throws InterruptedException {
        logger.info("****GEt Pet*******");
        System.out.println("petid in testGetPet " + this.petPayload.getId());
        Response response = PetEndpoints.readPet(this.petPayload.getId());
        response.then().log().all();
        response.statusCode();
        Assert.assertEquals(response.getStatusCode(), 200);
        try {
            Thread.sleep(5000); // 2 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Thread.sleep(2000); // Delay for 2000 milliseconds (2 seconds)
        logger.info("********** Get Pet Complted ****");
    }

    @Test(priority = 3)
    public void testUpdatePetById() {
        logger.info("**** update pet  *********");
//        update data using payload
        try {
            Thread.sleep(5000); // 2 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        petPayload.setName(faker.animal().name());
        petPayload.setStatus(faker.options().option("available", "pending", "sold"));
        Response response = PetEndpoints.updatePet(this.petPayload.getId(), petPayload);
        response.then().log().all();
//        Integer petid = response.jsonPath().getInt("id");

        response.statusCode();
        Assert.assertEquals(response.getStatusCode(), 200);
//        petPayload.setId(petid);
        try {
            Thread.sleep(5000); // 2 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ///checkmng data after update
        Response responseAfterUpdate = PetEndpoints.readPet(this.petPayload.getId());
        response.then().log().all();
        response.statusCode();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
        try {
            Thread.sleep(5000); // 2 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("******** Update Pet complete*******");
    }

    @Test(priority = 4)
    public void testDeletePetById() {
        logger.info("***** delete pet*****");
        System.out.println("id in delete "+this.petPayload.getId());
        Response response = PetEndpoints.deletePet(this.petPayload.getId());
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("***8 pet deleted*******");
    }
}
