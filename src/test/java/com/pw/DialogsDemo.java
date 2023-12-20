package com.pw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DialogsDemo extends ScriptBase{

    @Test
    public void alertTest(){
        page.navigate(home);
        page.fill("#donation","50");

        page.click("#submit-donation");
        Assertions.assertTrue(page.isVisible("text=Thank you!"));
    }
}
