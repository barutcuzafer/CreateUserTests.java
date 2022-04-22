import io.restassured.response.Response;
import models.CreateUserModel;
import org.junit.jupiter.api.Test;
import services.GoRestService;
import java.util.HashMap;
import java.util.Map;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.*;
import static utils.Assertions.assertCommonResponse;

public class CreateUserTests {

    @Test
    public void Users_CreateUsers_Success(){
        final CreateUserModel createUserModel = new CreateUserModel("Panda Dondurma", "Male", "panda2@test.com", "Active");
        GoRestService.createUser(createUserModel)
                .then().statusCode(SC_CREATED)
                .body("data.id", notNullValue())
                .body("data.name", equalTo(createUserModel.getName()))
                .body("data.email",equalTo(createUserModel.getEmail()));
    }



    @Test
    //Get all users info
    public void get_All_Users_Info(){
        GoRestService.getAllUsers().prettyPrint();
    }
    //--------------------------CRUD OPERATIONS FLOW-------------------------//

    //Create a new user with POST method
    @Test
    public void create_NewUser_With_Post(){

        final CreateUserModel createUserModel = new CreateUserModel("Gino Paloma", "male", "Gino.Paloma7@test.com", "active");
        Response response=GoRestService.createUser(createUserModel);//Initializing response object
        assertCommonResponse(response,SC_CREATED, createUserModel.getName(), createUserModel.getEmail(), createUserModel.getGender(), createUserModel.getStatus());

    }

    //Get created user data with GET method
    @Test
    public void get_Users_Success(){
        //print user data
        GoRestService.get_User_Data(3300).prettyPrint();
        //validation with Hamcrest Matchers
        GoRestService.get_User_Data(3300)
               .then().statusCode(SC_OK)
                .and().body("id",equalTo(3300));

    }


    //Update e-mail with PUT method
    @Test
    public void put_Users_Update(){
        //validation with Hamcrest Matchers
        GoRestService.update_User_Data_With_Put(3300,"Gino Paloma","male","active","palomagina55@gmail.com")
                .then().statusCode(SC_OK)//It might have returned 204 but 200
                .and().body("email",equalTo("palomagina55@gmail.com"));

        //Run GET method one again to make sure
    }


    //Update status with PATCH method
    @Test
    public void patch_Users_Update(){
        //create a map for update_User_Data_With_Patch method
        Map<String,Object> patchMap=new HashMap<>();
        patchMap.put("email","patchmail2@gmail.com");

        GoRestService.update_User_Data_With_Patch(3300,patchMap)
                .then().statusCode(SC_OK)//It should have returned 204 but 200
                .and().body("email",equalTo("patchmail2@gmail.com"));


        //Run GET method one again to make sure
    }

    //Delete user with DELETE method
    @Test
    public void delete_Users_Success(){
        GoRestService.delete_User_Data(3300)
                .then().statusCode(SC_NO_CONTENT);

        //Following Delete operation, should run either DELETE or GET method to make sure whether the user has been deleted or not.
        //Status Code should return 404
    }




}
