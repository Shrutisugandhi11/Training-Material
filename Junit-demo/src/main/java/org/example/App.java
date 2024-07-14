package org.example;


public class App {
    public String removeAString(String string) {
        String result = "";
        int length = string.length();
        if (length == 0) return string;
        char firstPosition = string.charAt(0);
        if (!(length == 1 && (firstPosition == 'A'))) result = string;
        if (length >= 2) {
            char secondPosition = string.charAt(1);
            if (length == 2 && (firstPosition == 'A' && secondPosition == 'A')) result = "";
            else if (firstPosition == 'A' && secondPosition == 'A') result = string.substring(2, length);
            else if (firstPosition == 'A') {
                result = string.substring(1, length);
            } else if (secondPosition == 'A') {
                result = firstPosition + "" + string.substring(2, length);
            }
        }
        return result;
    }

    public static void main(String[] args) {

        String str=new App().removeAString("ADVRSW") ;
        System.out.println(str);

    }

}
