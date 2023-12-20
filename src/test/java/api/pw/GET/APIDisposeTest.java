package api.pw.GET;

import api.pw.BaseScript;
import com.microsoft.playwright.APIResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class APIDisposeTest extends BaseScript {

    @Test
    public void disposeResponse(){
        APIResponse apiResponse = apiRequestContext.get(baseURL);

        int statusCode = apiResponse.status();
        System.out.println("response status is " + statusCode);
        Assertions.assertEquals(statusCode, 200);
        String statusResText = apiResponse.statusText();
        System.out.println(statusResText);

        System.out.println("---print api response with plain text---");
        System.out.println(apiResponse.text());

        apiResponse.dispose();
        int statusCode1 = apiResponse.status();
        System.out.println("response status after dispose: " + statusCode1);


    }

}
