package api.pw.PUT;

import api.pw.BaseScript;
import api.pw.User;
import api.pw.Users;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class UpdateUserPUTCallWithPojoLombokTest extends BaseScript {

    static String emailId;
    public  static String getRanmdomEmail(){
        emailId =  "test_mail" + System.currentTimeMillis() + "mail.com";
        return emailId;
    }

    @Test
    public void createUsersTest() throws IOException {
        //Create Users Object
        Users users =  Users.builder()
                .name("Juan Perez")
                .email(getRanmdomEmail())
                .gender("male")
                .status("active").build();

        //1: POST Call. create a user
        APIResponse apiResponse = apiRequestContext.post(baseURL,
                RequestOptions.create()
                        .setHeader("Content-Type","application/json")
                        //for the Authorization you will need to log to the api page and use your access token
                        .setHeader("Authorization","")
                        .setData(users));

        System.out.println(apiResponse.status());
        Assertions.assertEquals(apiResponse.status(), 201);
        Assertions.assertEquals(apiResponse.statusText(), "Created");


        String responseText = apiResponse.text();
        System.out.println(responseText);

        //Convert the response txt/json to POJO
        ObjectMapper objectMapper = new ObjectMapper();
        User actUser = objectMapper.readValue(responseText,User.class);


        Assertions.assertEquals(actUser.getName(),users.getName());
        Assertions.assertEquals(actUser.getEmail(),users.getEmail());
        Assertions.assertEquals(actUser.getStatus(),users.getStatus());
        Assertions.assertEquals(actUser.getGender(),users.getGender());
        Assertions.assertNotNull(actUser.getId());

        String userId = actUser.getId();
        System.out.println("new user ID is: " + userId);

        //update status active to inactive & update name
        users.setStatus("inactive");
        users.setName("Manual Perez");
        System.out.println("------------------------------");

        //2: PUT Call, Update User:
        APIResponse apiPuTResponse = apiRequestContext.put(baseURL + "/" + userId,
                RequestOptions.create()
                        .setHeader("Content-Type","application/json")
                        //for the Authorization you will need to log to the api page and use your access token
                        .setHeader("Authorization","")
                        .setData(users));

        System.out.println(apiPuTResponse.status() + " " + apiPuTResponse.statusText());
        Assertions.assertEquals(apiPuTResponse.status(), 200);

        String putResponseText = apiPuTResponse.text();
        System.out.println("Updated User");
        Users actPutUser = objectMapper.readValue(putResponseText, Users.class);
        Assertions.assertEquals(actPutUser.getId(),userId);
        Assertions.assertEquals(actPutUser.getStatus(),users.getStatus());
        Assertions.assertEquals(actPutUser.getName(),users.getName());

        System.out.println("------------------------------");

        //3. Get updated user with Get Call:
        APIResponse apiGETResponse = apiRequestContext.get(baseURL +"/"+userId,
                RequestOptions.create()
                        .setHeader("Authorization",""));
        int statusCode = apiGETResponse.status();
        System.out.println("response status is " + statusCode);
        Assertions.assertEquals(statusCode, 200);

        String statusGETText = apiGETResponse.statusText();
        System.out.println(statusGETText);

        String getResponseText = apiGETResponse.text();

        Users actGETUser = objectMapper.readValue(getResponseText, Users.class);
        Assertions.assertEquals(actGETUser.getId(),userId);
        Assertions.assertEquals(actGETUser.getStatus(),users.getStatus());
        Assertions.assertEquals(actGETUser.getName(),users.getName());


    }
}
