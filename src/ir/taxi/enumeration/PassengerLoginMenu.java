package ir.taxi.enumeration;

/**
 * @author Mahsa Alikhani m-58
 */
public enum PassengerLoginMenu {
    INCREASE("1. Increase account balance"),
    EXIT("2. Exit");

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
