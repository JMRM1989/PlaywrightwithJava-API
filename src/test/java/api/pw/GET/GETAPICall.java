package api.pw.GET;

import api.pw.BaseScript;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GETAPICall extends BaseScript {

    @Test
    public void getUsersApiTest(){
        APIResponse apiResponse = apiRequestContext.get(baseURL);

        int statusCode = apiResponse.status();
        System.out.println("response status is " + statusCode);
        Assertions.assertEquals(statusCode, 200);
        String statusResText = apiResponse.statusText();
        System.out.println(statusResText);

        System.out.println("---print api response with plain text---");
        System.out.println(apiResponse.text());
    }

    @Test
    public void getSpecificUserApiTest(){
        APIResponse apiResponse = apiRequestContext.get(baseURL,
                RequestOptions.create()
                        .setQueryParam("id",856168)
                        .setQueryParam("status","inactive")
                );
        int statusCode = apiResponse.status();
        System.out.println("response status is " + statusCode);
        Assertions.assertEquals(statusCode, 200);
        String statusResText = apiResponse.statusText();
        System.out.println(statusResText);

        System.out.println("---print api response with plain text---");
        System.out.println(apiResponse.text());
    }
}
