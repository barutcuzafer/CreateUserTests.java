
import io.restassured.response.Response;
import models.CreateUserModel;
import org.junit.jupiter.api.Test;
import services.GoRestService;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

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
    //--------------------------CRUD OPERATIONS FLOW-------------------------//

    //Create a new user with POST method
    @Test
    public void post_NewUser_And_Get_Id(){

        final CreateUserModel createUserModel = new CreateUserModel("Gino Paloma", "male", "Gino.Paloma5@test.com", "active");

        Response response=GoRestService.createUser(createUserModel);//Initializing response object
        response.prettyPrint();

        int ID=response.jsonPath().getInt("data.id"); //retrieving new created user ID with jsonPath() method
       //assertions with junit
       assertEquals(SC_CREATED,response.getStatusCode());
       assertEquals(createUserModel.getName(),response.jsonPath().getString("data.name"));
       assertEquals(createUserModel.getGender(),response.jsonPath().getString("data.gender"));
       assertEquals(createUserModel.getEmail(),response.jsonPath().getString("data.email"));
       assertEquals(createUserModel.getStatus(),response.jsonPath().getString("data.status"));


    }

    //Get created user data with GET method
    @Test
    public void get_Users_Success(){
        //print user data
       GoRestService.get_User_Data(4529).prettyPrint();
       //validation with Hamcrest Matchers
        GoRestService.get_User_Data(4529)
               .then().statusCode(SC_OK)
                .and().body("data.id",equalTo(4529));

    }


    //Update e-mail with PUT method
    @Test
    public void put_Users_Update(){
        //update and print the response payload
        GoRestService.update_User_Data_With_Put(4529,"Gino Paloma","male","active","palomagina@gmail.com").prettyPrint();
        //validation with Hamcrest Matchers
        GoRestService.update_User_Data_With_Put(4529,"Gino Paloma","male","active","palomagina@gmail.com")
                .then().statusCode(SC_OK)//It should have returned 204 but 200
                .and().body("data.email",equalTo("palomagina@gmail.com"));
    }


    //Update status with PATCH method
    @Test
    public void patch_Users_Update(){
        //update and print the response payload
        GoRestService.update_User_Data_With_Patch(4529).prettyPrint();
        //validation with Hamcrest Matchers
        GoRestService.update_User_Data_With_Patch(4529).then()
              .statusCode(SC_OK) //It should have returned 204 but 200
                .body("data.gender",equalTo("female"));
    }

    //Delete user with DELETE method
    @Test
    public void delete_Users_Success(){
        GoRestService.delete_User_Data(4529)
                .then().statusCode(SC_NO_CONTENT)
                .and().body("data.id",nullValue());
        //Following Delete operation, should run either DELETE or GET method to make sure whether the user has been deleted or not.
        //Status Code should return 404
    }




}
