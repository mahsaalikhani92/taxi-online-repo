package ir.taxi;

import java.util.function.IntUnaryOperator;

/**
 * @author Mahsa Alikhani m-58
 */
public class ValidationUtil {

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^(\\+98|0)?9\\d{9}$");
    }

    public static boolean isLetter(String input) {
        return input.matches("[a-zA-Z]+");
    }

    public static boolean isNumeric(String input) {
        return input.matches("[1-9]+");
    }

    public static boolean isAlphabetic(String input) {
        return input.matches("^[a-zA-Z0-9]+$");
    }

    public static boolean isValidUsername(String input) {
        return input.matches("^[A-Za-z]\\w{3,29}$");
    }

    public static boolean isIranianNationalCode(String input) {
        if (input.length() == 10) {
           int p10 = Character.getNumericValue(input.charAt(0)) * 10;
           int p9 = Character.getNumericValue(input.charAt(1)) * 9;
           int p8 = Character.getNumericValue(input.charAt(2)) * 8;
           int p7 = Character.getNumericValue(input.charAt(3)) * 7;
           int p6 = Character.getNumericValue(input.charAt(4)) * 6;
           int p5 = Character.getNumericValue(input.charAt(5)) * 5;
           int p4 = Character.getNumericValue(input.charAt(6)) * 4;
           int p3 = Character.getNumericValue(input.charAt(7)) * 3;
           int p2 = Character.getNumericValue(input.charAt(8)) * 2;
           int sum = p10 + p9 + p8 + p7+ p6 + p5 + p4 + p3+ p2;
           int remaining = sum % 11;
           if(remaining < 2){
                if(Character.getNumericValue(input.charAt(9)) == 2){
                    return true;
                }
           }else{
               if(Character.getNumericValue(input.charAt(9)) == 11 - remaining);
               return true;
           }
        }else if(input.length() == 9){
            int p9 = Character.getNumericValue(input.charAt(0)) * 9;
            int p8 = Character.getNumericValue(input.charAt(1)) * 8;
            int p7 = Character.getNumericValue(input.charAt(2)) * 7;
            int p6 = Character.getNumericValue(input.charAt(3)) * 6;
            int p5 = Character.getNumericValue(input.charAt(4)) * 5;
            int p4 = Character.getNumericValue(input.charAt(5)) * 4;
            int p3 = Character.getNumericValue(input.charAt(6)) * 3;
            int p2 = Character.getNumericValue(input.charAt(7)) * 2;
            int sum = p9 + p8 + p7+ p6 + p5 + p4 + p3+ p2;
            int remaining = sum % 11;
            if(remaining < 2){
                if(Character.getNumericValue(input.charAt(8)) == 2){
                    return true;
                }
            }else{
                if(Character.getNumericValue(input.charAt(8)) == 11 - remaining);
                return true;
            }
        }else if(input.length() == 8){
            int p8 = Character.getNumericValue(input.charAt(0)) * 8;
            int p7 = Character.getNumericValue(input.charAt(1)) * 7;
            int p6 = Character.getNumericValue(input.charAt(2)) * 6;
            int p5 = Character.getNumericValue(input.charAt(3)) * 5;
            int p4 = Character.getNumericValue(input.charAt(4)) * 4;
            int p3 = Character.getNumericValue(input.charAt(5)) * 3;
            int p2 = Character.getNumericValue(input.charAt(6)) * 2;
            int sum = p8 + p7+ p6 + p5 + p4 + p3+ p2;
            int remaining = sum % 11;
            if(remaining < 2){
                if(Character.getNumericValue(input.charAt(7)) == 2){
                    return true;
                }
            }else{
                if(Character.getNumericValue(input.charAt(7)) == 11 - remaining);
                return true;
            }
        }
        return false;
    }

    public static boolean isPersianDate(String input) {
        return input.matches("[0-9]{1,2}(/|-)[0-9]{1,2}(/|-)[0-9]{4}");
    }

    public static boolean isIranianCarPlateNumber(String input) {
        return input.matches("\\d{2}[\\u0600-\\u06FF]\\d{3}\\-d{2}"); //? - ?
    }
}
