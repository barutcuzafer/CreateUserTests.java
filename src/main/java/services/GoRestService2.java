package services;

import io.restassured.response.Response;
import models.CreateUserModel;
import java.util.Map;
import static utils.TestConstants.*;


public class GoRestService2 extends BaseService {

    public static Response createUser(final CreateUserModel createUserModel){
        return defaultRequestSpecification()
                .body(createUserModel)
                .when()
                .post(v2_END_POiNT);
    }

    public static Response getAllUsers(){
        return defaultRequestSpecification()
                .when().get(v2_END_POiNT);
    }

    public static Response getSpecificUser(int id){
        return defaultRequestSpecification()
                .when().get(v2_END_POiNT + "/" + id);
    }

    public static Response updateSpecificUser(int id, Map map){
        return defaultRequestSpecification()
                .given()
                .body(map)
                .when().patch(v2_END_POiNT + "/" + id);
    }

    public static Response deleteSpecificUser(int id){
        return defaultRequestSpecification()
                .when().delete(v2_END_POiNT + "/" + id);
    }

}

