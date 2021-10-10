package ir.taxi.enumeration;

/**
 * @author Mahsa Alikhani m-58
 */
public enum PassengerLoginMenu {
    TRAVEL1("1. Travel request (pay by cash)"),
    TRAVEL2("2. Travel request (pay by account balance)"),
    INCREASE("3. Increase account balance"),
    EXIT("4. Exit");

    private String expression;

    PassengerLoginMenu(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public static void showPassengerLoginMenu(){
        for (PassengerLoginMenu item:PassengerLoginMenu.values()) {
            System.out.println(item.getExpression());
        }
    }
}
