package services;

import io.restassured.response.Response;
import models.CreateUserModel;

import java.util.HashMap;
import java.util.Map;


public class GoRestService extends BaseService {

    public static String endPoint="/public/v1/users";

    public static Response createUser(final CreateUserModel createUserModel){
        return defaultRequestSpecification()
                .body(createUserModel)
                .when()
                .post(endPoint);
    }





    public static Response get_User_Data(final int ID){
        return defaultRequestSpecification()
                .given().pathParam("id",ID)
                .when().get(endPoint+"/{id}");
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
                .when().put(endPoint+"/{id}");
    }





    public static Response update_User_Data_With_Patch(final int ID){
        Map<String,Object>updateGender=new HashMap<>();
        updateGender.put("gender","female");

        return defaultRequestSpecification().given().pathParam("id",ID)
                .and().body(updateGender)
                .when().patch(endPoint+"/{id}");




    }
    public static Response delete_User_Data(final int ID){
        return defaultRequestSpecification().given()
                .pathParam("id",ID)
                .when().delete(endPoint+"/{id}");


    }






}
