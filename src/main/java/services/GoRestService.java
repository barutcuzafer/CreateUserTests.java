package services;

import io.restassured.response.Response;
import models.CreateUserModel;
import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.Map;


public class GoRestService extends BaseService {

    public static Response createUser(final CreateUserModel createUserModel){
        return defaultRequestSpecification()
                .body(createUserModel)
                .when()
                .post("/public/v1/users");
    }







    public static Response get_User_Data(final int ID){
        return defaultRequestSpecification()
                .given().pathParam("id",ID)
                .when().get("/public/v1/users/{id}");
    }





    public static Response update_User_Data_With_Put(final int ID,String name,String gender,String status,String email){
        Map<String,Object> updateMap=new HashMap<>();
        updateMap.put("id",ID);
        updateMap.put("name",name);
        updateMap.put("gender",gender);
        updateMap.put("status",status);
        updateMap.put("email",email);


        return defaultRequestSpecification()
                .given().pathParam("id",ID)
                .and().body(updateMap)
                .when().put("/public/v1/users/{id}");
    }








    public static Response update_User_Data_With_Patch(final int ID){
        Map<String,Object>updateMap2=new HashMap<>();
        updateMap2.put("gender","female");

        return defaultRequestSpecification().given().pathParam("id",ID)
                .and().body(updateMap2)
                .when().patch("/public/v1/users/{id}");







    }
    public static Response delete_User_Data(final int ID){
        return defaultRequestSpecification().given()
                .pathParam("id",ID)
                .when().delete("/public/v1/users/{id}");


    }




}
