import com.github.javafaker.Faker;
import io.restassured.response.Response;
import models.CreateUserModel;
import org.junit.jupiter.api.Test;
import services.GoRestService;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
import static utils.Assertions.assertCommonResponse;

    public class CreateUserTests2 {

        //create scaffolding for upcoming fields
        Faker faker = new Faker();
        String fakeName;
        String fakeEmail;
        String updatedFakeName;
        String updatedFakeEmail;

        //define id without value -- it will be assigned automatically
        Integer ID;

        @Test
        public void userFlow(){

            //create fake data
            fakeName = faker.name().fullName();
            fakeEmail = faker.internet().emailAddress();

            //create an object via CreateUserModel class
            CreateUserModel createUserModel = new CreateUserModel(fakeName,"male",fakeEmail,"active");

            //post a random user, verify response body, extract real id from response body
            Response response = GoRestService.createUser(createUserModel);
            assertCommonResponse(response,SC_CREATED,fakeName,fakeEmail,"male","active");
            ID = response.path("id");


            //get a specific user and verify response body
            response = GoRestService.get_User_Data(ID);
            assertCommonResponse(response,SC_OK,fakeName,fakeEmail,"male","active");


            //update a specific user and verify response body
            updatedFakeName = faker.name().fullName();
            updatedFakeEmail = faker.internet().emailAddress();
            Map<Object,Object> map = new LinkedHashMap<>();
            map.put("name", updatedFakeName);
            map.put("email", updatedFakeEmail);
            response = GoRestService.update_User_Data_With_Patch(ID,map);
            assertCommonResponse(response,SC_OK,updatedFakeName,updatedFakeEmail,"male","active");


            //delete a specific user and verify response body and status code
            response = GoRestService.delete_User_Data(ID); //response body returns empty, therefore only status code is used as verification
            assertEquals(SC_NO_CONTENT,response.statusCode());

            //
            response = GoRestService.get_User_Data(ID);
            assertEquals(SC_NOT_FOUND,response.getStatusCode());



        }
    }


