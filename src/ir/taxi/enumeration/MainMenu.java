package ir.taxi.enumeration;

/**
 * @author Mahsa Alikhani m-58
 */
public enum MainMenu {
    ONE("1. Add a group of drivers"),
    TWO("2. Add a group of passengers"),
    THREE("3. Driver signup or login"),
    FOUR("4. Passenger signup or login"),
    FIVE("5. Show a list of drivers"),
    SIX("6. Show a list of passengers");

    private String expression;

    MainMenu(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public static void showMainMenu(){
        for (MainMenu item:MainMenu.values()) {
            System.out.println(item.getExpression());
        }
    }
}
