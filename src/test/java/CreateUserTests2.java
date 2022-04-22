import com.github.javafaker.Faker;
import io.restassured.response.Response;
import models.CreateUserModel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import services.GoRestService2;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.apache.http.HttpStatus.*;
import static utils.Assertions.assertCommonResponse;

    public class CreateUserTests2 {

        //create scaffolding for upcoming fields
        Faker faker = new Faker();
        String fakeName;
        String fakeEmail;
        String updatedFakeName;
        String updatedFakeEmail;

        //define id without value -- it will be assigned automatically
        Integer id;

        @Test
        public void userFlow(){

            //create fake data
            fakeName = faker.name().fullName();
            fakeEmail = faker.internet().emailAddress();

            //create an object via CreateUserModel class
            CreateUserModel createUserModel = new CreateUserModel(fakeName,"male",fakeEmail,"active");

            //post a random user, verify response body, extract real id from response body
            Response response = GoRestService2.createUser(createUserModel);
            assertCommonResponse(response,SC_CREATED,fakeName,fakeEmail,"male","active");
            id = response.path("id");

            //get a specific user and verify response body
            response = GoRestService2.getSpecificUser(id);
            assertCommonResponse(response,SC_OK,fakeName,fakeEmail,"male","active");

            //update a specific user and verify response body
            updatedFakeName = faker.name().fullName();
            updatedFakeEmail = faker.internet().emailAddress();
            Map<Object,Object> map = new LinkedHashMap<>();
            map.put("name", updatedFakeName);
            map.put("email", updatedFakeEmail);
            response = GoRestService2.updateSpecificUser(id,map);
            assertCommonResponse(response,SC_OK,updatedFakeName,updatedFakeEmail,"male","active");

            //delete a specific user and verify response body
            response = GoRestService2.deleteSpecificUser(id); //response body returns empty, therefore only status code is used as verification
            Assert.assertEquals(SC_NO_CONTENT,response.statusCode());

        }
    }


