package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

/**
 * Unit test for simple App.
 */
public class AppTest {



    App app;

    @Test
    @DisplayName("descriptionOfString")
    public void stringIsEmpty() {
        app = new App();
        Assertions.assertEquals("", app.removeAString(""));
        Assertions.assertEquals("B",app.removeAString("B"));
    }

    @Test
    public void charAtFirstPosition() {
        app = new App();
        Assertions.assertEquals("BCD", app.removeAString("ABCD"));
    }
    @Test
    public void stringOfTwo() {
        app = new App();
        Assertions.assertEquals("B", app.removeAString("BA"));
        Assertions.assertEquals("", app.removeAString("AA"));
    }

    @Test
    public void charAtSecondPosition() {
        app = new App();
        Assertions.assertEquals("BCD", app.removeAString("BACD"));
        Assertions.assertEquals(" CD", app.removeAString(" ACD"));
    }

    @Test
    public void spaceInString() {
        app = new App();
        Assertions.assertEquals(" CD", app.removeAString("A CD"));
        Assertions.assertEquals(" CD", app.removeAString(" ACD"));
    }
/*
    "AA"->""
    "A"->""
    */

    @Test
    public void charAtFirstAndSecondPosition() {
        app = new App();
        Assertions.assertEquals("", app.removeAString("A"));
        Assertions.assertEquals("CD", app.removeAString("AACD"));
        Assertions.assertEquals("BAA", app.removeAString("AABAA"));
        Assertions.assertEquals("AAA", app.removeAString("AAAAA"));
    }

    @Test
    public void charAtLastPosition() {
        app = new App();
        Assertions.assertEquals("BBAA", app.removeAString("BBAA"));
    }
}
