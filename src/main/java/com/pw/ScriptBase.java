package com.pw;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


public class ScriptBase {
    String home = "file:///" + System.getProperty("user.dir") + "\\src\\web\\home.html";
    String advantages = "file:///" + System.getProperty("user.dir") + "\\src\\web\\advantages.html";
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    static void launchBrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @BeforeEach
    void createContextAndPage(){
        context = browser.newContext();
        page = context.newPage();
    }
    @AfterEach
    void closeContext(){
        context.close();
    }

    @AfterAll
    static  void closeBrowser(){
        browser.close();
        playwright.close();
    }

}
