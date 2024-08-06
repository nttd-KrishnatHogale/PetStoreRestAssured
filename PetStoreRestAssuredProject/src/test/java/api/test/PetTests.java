package api.test;

import api.endpoints.PetEndpoints;
import api.payload.Pet;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
public class PetTests {
    Faker faker;
    Pet petPayload;
    public Integer petid;
    @BeforeClass
   public void setUpData(){
       faker =new Faker();
       petPayload= new Pet();

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

    }
@Test(priority = 1)
    public  void  testPostPet(){
    Response response = PetEndpoints.createPet(petPayload);
    response.then().log().all();
//    System.out.println(response.body());
    response.getBody().asString();
    Integer petid = response.jsonPath().getInt("id");
    System.out.println("ID from psit request"+petid);
    petPayload.setId(petid);
    Assert.assertEquals(response.statusCode(),200);

    }

    @Test(priority = 2)
    public  void testGetPetById(){
        System.out.println("petid in testGetPet"+this.petPayload.getId());
        Response response=PetEndpoints.readPet(this.petPayload.getId());
        response.then().log().all();
        response.statusCode();
        Assert.assertEquals(response.getStatusCode(),200);

    }
}
