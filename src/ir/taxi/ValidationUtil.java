package ir.taxi;

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
}
