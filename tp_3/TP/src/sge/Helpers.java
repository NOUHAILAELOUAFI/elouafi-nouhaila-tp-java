package sge;

import java.util.regex.Pattern;

public class Helpers {
    private Helpers(){}

    public static String parseNull(String str){
        if(str.equalsIgnoreCase("null")) return null;
        return str;
    }

    public static boolean validateEmail(String email){
        Pattern emailPattern = Pattern.compile("[a-z0-9+_.-]+@[A-Za-z0-9.-]+[a-z]{2,3}", Pattern.CASE_INSENSITIVE);
        return emailPattern.matcher(email).matches();
    }

    public static boolean validateIdentityInfo(String idfield){
        
        return idfield.length() > 3;
    }
}
