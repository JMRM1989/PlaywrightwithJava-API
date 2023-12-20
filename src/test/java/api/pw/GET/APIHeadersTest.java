package api.pw.GET;

import api.pw.BaseScript;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.HttpHeader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class APIHeadersTest extends BaseScript {

    @Test
    public void APIHeadersTest(){
        APIResponse apiResponse = apiRequestContext.get(baseURL);

        int statusCode = apiResponse.status();
        System.out.println("response status is " + statusCode);
        Assertions.assertEquals(statusCode, 200);

        //using map:
        Map<String, String> headersMap = apiResponse.headers();
        headersMap.forEach((k,v)->System.out.println("k"+" : "+"v"));
        System.out.println("total response headers: "+headersMap.size());
        Assertions.assertEquals(headersMap.get("server"),"cloudflare");
        Assertions.assertEquals(headersMap.get("content-type"),"application/json; charset=utf-8");

        System.out.println("======================");

        //using list:
        List<HttpHeader> headerList = apiResponse.headersArray();
        for(HttpHeader e : headerList){
            System.out.println(e.name + " : "+e.value);
        }
    }
}
