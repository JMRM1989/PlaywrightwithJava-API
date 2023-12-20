package api.pw.POST;


import api.pw.BaseScript;
import api.pw.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class createUserPostCallWithPojoTest extends BaseScript {

    static String emailId;
    public  static String getRanmdomEmail(){
        emailId =  "test_mail" + System.currentTimeMillis() + "mail.com";
        return emailId;
    }

    @Test
    public void createUsersTest() throws IOException {

        User user = new User("Juan Perez",getRanmdomEmail(),"male","active");

        //POST Call. create a user
        APIResponse apiResponse = apiRequestContext.post(baseURL,
                RequestOptions.create()
                        .setHeader("Content-Type","application/json")
                        //for the Authorization you will need to log to the api page and use your access token
                        .setHeader("Authorization","")
                        .setData(user));

        System.out.println(apiResponse.status());
        Assertions.assertEquals(apiResponse.status(), 201);
        Assertions.assertEquals(apiResponse.statusText(), "Created");


        String responseText = apiResponse.text();
        System.out.println(responseText);

        //Convert the response txt/json to POJO
        ObjectMapper objectMapper = new ObjectMapper();
        User actUser = objectMapper.readValue(responseText,User.class);
        Assertions.assertEquals(actUser.getName(),user.getName());
        Assertions.assertEquals(actUser.getEmail(),user.getEmail());
        Assertions.assertEquals(actUser.getStatus(),user.getStatus());
        Assertions.assertEquals(actUser.getGender(),user.getGender());
        Assertions.assertNotNull(actUser.getId());
    }
}
