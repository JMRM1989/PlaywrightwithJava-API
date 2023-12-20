package com.pw;


import org.junit.jupiter.api.*;

public class _1FirstTestWithContext extends ScriptBase{

    @Test
    public void firstPlayWrightTestWithContext(){

        page.navigate(home);
        Assertions.assertEquals(page.title(), "Home Page");

    }

    @Test
    public void secondPlayWrightTestWithContext(){

        page.navigate(home);
        String content = page.content();
        Assertions.assertTrue(content.contains("Cat In The Bag"));

    }
}
