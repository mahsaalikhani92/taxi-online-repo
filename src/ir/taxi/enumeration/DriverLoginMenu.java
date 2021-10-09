package ir.taxi.enumeration;

/**
 * @author Mahsa Alikhani m-58
 */
public enum DriverLoginMenu {
    CONFIRM("1. Confirm cash receipt"),
    FINISHED("2. Travel finished"),
    EXIT("3 .Exit");

    private String expression;

    DriverLoginMenu(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
    public static void showDriverLoginMenu(){
        for (DriverLoginMenu item:DriverLoginMenu.values()) {
            System.out.println(item.getExpression());
        }
    }
}
