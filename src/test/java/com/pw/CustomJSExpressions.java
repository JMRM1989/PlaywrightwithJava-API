package com.pw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomJSExpressions extends ScriptBase{

    @Test
    public void localStorage(){
        page.navigate(home);
        Object obj = page.evaluate("() => window.localStorage.getItem('clapped')");
        Assertions.assertNull(obj);

        page.click("#clap-image");
        String obj2 = (String) page.evaluate("() => window.localStorage.getItem('clapped')");
        Assertions.assertTrue(Boolean.parseBoolean(obj2));
    }

    @Test
    public void evalOnSelector(){
        page.navigate(home);
        page.evalOnSelector("#hero-banner","e => e.remove()");
        Assertions.assertFalse(page.isVisible("#hero-banner"));
    }

    @Test
    public void evalOnSelectorAll(){
        page.navigate(advantages);
        Object obj = page.evalOnSelectorAll(".feature","f => f.length");
        Assertions.assertEquals(3, obj);

    }
}
