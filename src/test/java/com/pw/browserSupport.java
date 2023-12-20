package com.pw;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

public class browserSupport {

    @Test
    public void browserSupport(){
        try(Playwright pw = Playwright.create()){
            List<BrowserType> browserTypes = List.of(pw.chromium(),pw.webkit());

            for(BrowserType type : browserTypes){
                Page page = type.launch().newPage();
                page.navigate("https://www.whatsmybrowser.org");
                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(type.name()+".png")));
            }
        }
    }
}
