import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.CreateUserModel;
import org.junit.jupiter.api.Test;
import services.GoRestService;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateUserTests {
     String postBody="";
    @Test
    public void Users_CreateUsers_Success(){

        final CreateUserModel createUserModel = new CreateUserModel("Panda Dondurma", "Male", "panda@test.com", "Active");
        GoRestService.createUser(createUserModel)
                .then().statusCode(SC_CREATED)
               .body("data.id", notNullValue())
               .body("data.name", equalTo(createUserModel.getName()))
                .body("data.email",equalTo(createUserModel.getEmail()))
                .body("data.gender",equalTo(createUserModel.getGender()))
                .body("data.status",equalTo(createUserModel.getStatus()));

    }

    @Test
    public void eren(){
        final CreateUserModel createUserModel = new CreateUserModel("Gino Paloma", "Male", "eren3@test1mail.com", "Active");
        Response response=GoRestService.createUser(createUserModel);
        System.out.println(response.path("data.id").toString());
        //  JsonPath jsonPath=response.jsonPath();
       // System.out.println(jsonPath.getInt("data.id"));

    }


    @Test
    public void get_Users_Success(){
        System.out.println(GoRestService.get_User_Data(8005).statusCode());
        GoRestService.get_User_Data(8005).prettyPrint();

    }


    @Test
    public void put_Users_Update(){
        GoRestService.update_User_Data_With_Put(8005,"Gino Paloma","male","active","qwer@gmail.com").prettyPrint();
    }



    @Test
    public void patch_Users_Update(){
        GoRestService.update_User_Data_With_Patch(8005).prettyPrint();
    }


    @Test
    public void delete_Users_Success(){
        System.out.println(GoRestService.delete_User_Data(8005).statusCode());
    }


    @Test
    public void isDeleteOperationSuccessfull2(){
        System.out.println(GoRestService.get_User_Data(8005).statusCode());

    }

}
