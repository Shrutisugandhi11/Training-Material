package com.epam.junit.demo;

/**
 * Hello world!
 */
public class App {
    public String removeAString(String string) {

        if (string.length() == 0)
            return string;
        char firstPosition = string.charAt(0);
        char secondPosition = string.charAt(1);
        if (firstPosition == 'A' && secondPosition == 'A')
            string = string.substring(2, string.length());
        else if (firstPosition == 'A') {
            string = string.substring(1, string.length());
        } else if (secondPosition == 'A') {
            string = firstPosition + "" + string.substring(2, string.length());
        }

        return string;
    }
}
