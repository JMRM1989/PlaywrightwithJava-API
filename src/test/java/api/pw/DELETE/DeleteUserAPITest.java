package api.pw.DELETE;

import api.pw.BaseScript;
import api.pw.User;
import api.pw.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DeleteUserAPITest extends BaseScript {

    static String emailId;
    public  static String getRanmdomEmail(){
        emailId =  "test_mail" + System.currentTimeMillis() + "mail.com";
        return emailId;
    }

    @Test
    public void DeleteUsersTest() throws IOException {
        //1. Create User Object
        Users users =  Users.builder()
                .name("Juan Perez")
                .email(getRanmdomEmail())
                .gender("male")
                .status("active").build();

        //POST Call. create a user
        APIResponse apiResponse = apiRequestContext.post(baseURL,
                RequestOptions.create()
                        .setHeader("Content-Type","application/json")
                        //for the Authorization you will need to log to the api page and use your access token
                        .setHeader("Authorization","")
                        .setData(users));

        System.out.println(apiResponse.status());
        Assertions.assertEquals(apiResponse.status(), 201);


        String responseText = apiResponse.text();
        System.out.println(responseText);

        //Convert the response txt/json to POJO
        ObjectMapper objectMapper = new ObjectMapper();
        User actUser = objectMapper.readValue(responseText,User.class);

        Assertions.assertNotNull(actUser.getId());

        String userId = actUser.getId();
        System.out.println("new user ID is: " + userId);
        System.out.println("------------------------------");

        //2. Delete User
        APIResponse apiDeleteResponse =  apiRequestContext.delete(baseURL + "/"+ userId,
                RequestOptions.create()
                        //for the Authorization you will need to log to the api page and use your access token
                        .setHeader("Authorization",""));

        System.out.println(apiDeleteResponse.status());
        System.out.println(apiDeleteResponse.statusText());

        Assertions.assertEquals(apiDeleteResponse.status(),204);

        System.out.println("delete user response body ==== "+apiDeleteResponse.text());

        System.out.println("------------------------------");
        //3. Get User
        APIResponse apiGETResponse = apiRequestContext.get(baseURL+"/"+userId,
                RequestOptions.create()
                        //for the Authorization you will need to log to the api page and use your access token
                        .setHeader("Authorization",""));

        System.out.println(apiGETResponse.status() + " " + apiGETResponse.statusText());
        Assertions.assertEquals(apiGETResponse.status(), 404);
        Assertions.assertEquals(apiGETResponse.statusText(),"Not Found");

    }
}
