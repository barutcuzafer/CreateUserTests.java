package services;

import io.restassured.response.Response;
import models.CreateUserModel;

import java.util.HashMap;
import java.util.Map;

import static utils.TestConstants.*;


public class GoRestService extends BaseService {


    public static Response createUser(final CreateUserModel createUserModel){
        return defaultRequestSpecification()
                .body(createUserModel)
                .when()
                .post(v2_END_POiNT);
    }

    public static Response getAllUsers(){
        return defaultRequestSpecification()
                .when().get(v1_END_POiNT);
    }


    public static Response get_User_Data(final int ID){
        return defaultRequestSpecification()
                .when().get(v2_END_POiNT+"/"+ID);
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
                .when().put(v2_END_POiNT+"/{id}");
    }





    public static Response update_User_Data_With_Patch(final int ID,Map map){
        return defaultRequestSpecification().given().
                body(map)
                .when().patch(v2_END_POiNT + "/" + ID);




    }
    public static Response delete_User_Data(int ID){
        return defaultRequestSpecification().given()
                .pathParam("id",ID)
                .when().delete(v2_END_POiNT + "/{id}");


    }






}
