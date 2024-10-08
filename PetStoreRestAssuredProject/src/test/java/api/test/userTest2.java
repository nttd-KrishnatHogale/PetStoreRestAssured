package api.test;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import java.util.logging.Logger;

public class userTest2 {
    Faker faker;
    User userPayload;

    public Logger logger;
    @BeforeClass
    public void setUp(){
        faker = new Faker();
        userPayload=new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    //logs
        logger = LogManager.getLogger(this.getClass());

        logger.debug("debugging");


    }

    @Test(priority = 1)
    public  void testPostUser(){
        logger.info("******************crarting user*******************");
        Response response= UserEndpoints2.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("************* User is Created ***********************");
    }

@Test(priority = 2)
    public void testGetUserByName(){
        logger.info("********************** Reading usr info **************");
    System.out.println(this.userPayload.getUsername());
       Response response = UserEndpoints2.readUser(this.userPayload.getUsername());
       response.then().log().all();
       Assert.assertEquals(response.getStatusCode(),200);
       logger.info("************** user info is displayed ******************");
    }

    @Test(priority = 3)
    public void testUpdateUserByName(){
        logger.info("*****************updatin user****************");

        //update data using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        Response response=UserEndpoints2.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().body().statusCode(200);
        Assert.assertEquals(response.getStatusCode(),200);
        //checking data after update
        Response responseAfterUpdate = UserEndpoints2.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
        logger.info("**********user is updated ****************");
    }

    @Test(priority = 4)
    public void testDeleteUserByName(){
        logger.info("************deleteing User *****************");
       Response response = UserEndpoints2.deleteUser(this.userPayload.getUsername());
       Assert.assertEquals(response.getStatusCode(),200);
       logger.info("*********** user deleted ***************");
    }
}
