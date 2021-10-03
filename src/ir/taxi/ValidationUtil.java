package ir.taxi;

import java.util.function.IntUnaryOperator;

/**
 * @author Mahsa Alikhani m-58
 */
public class ValidationUtil {

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^[0-9]+(.|//)?[0-9]?$");
    }

    public static boolean isAlphabetic(String input) {
        return input.matches("[a-zA-Z]+");
    }

    public static boolean isNumeric(String input) {
        return input.matches("[1-9]+");
    }

    public static boolean isValidUsername(String input){
        return input.matches("^[A-Za-z]\\w{5,29}$");
    }
    public static boolean isIranianNationalCode(String input){
        return input.matches("^[0-9]{10}$\n");

        /*// check if input has 10 digits that all of them are not equal
        if (!input.matches("^\\d{10}$"))
            return false;

        int check = Integer.parseInt(input.substring(9, 10));
        int sum = Streams.intRange(0, 9)
                .map((IntUnaryOperator) x ->
                        Integer.parseInt(input.substring(x, x + 1)) * (10 - x))
                .sum() % 11;

        return (sum < 2 && check == sum) || (sum >= 2 && check + sum == 11);*/
    }
    public static boolean isPersianDate(String input){
        return input.matches("/^[1-4]\\d{3}\\/((0[1-6]\\/((3[0-1])|([1-2][0-9])|(0[1-9])))|((1[0-2]|(0[7-9]))\\/(30|([1-2][0-9])|(0[1-9]))))$/");
    }

    public static boolean isIranianCarPlateNumber(String input){
        return input.matches("\\d{2}[\\u0600-\\u06FF]\\d{3}\\-d{2}"); //? - ?
    }
}
