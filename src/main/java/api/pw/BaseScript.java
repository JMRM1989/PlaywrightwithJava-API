package api.pw;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseScript {
    public String baseURL = "https://gorest.co.in/public/v2/users";
    protected static Playwright playwright;
    protected static APIRequest request;
    protected static APIRequestContext apiRequestContext;
    @BeforeAll
    static void setup(){
        playwright = Playwright.create();
        request = playwright.request();
        apiRequestContext = request.newContext();
    }
}
