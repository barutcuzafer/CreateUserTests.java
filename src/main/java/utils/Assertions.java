package utils;

import io.restassured.response.Response;
import static org.junit.Assert.assertEquals;


public class Assertions {

    public static void assertCommonResponse(Response response, int statusCode, String name, String email, String gender,String status){
        assertEquals(statusCode,response.statusCode());
        assertEquals(name,response.body().path("name"));
        assertEquals(email,response.body().path("email"));
        assertEquals(gender,response.body().path("gender"));
        assertEquals(status,response.body().path("status"));
    }




}
