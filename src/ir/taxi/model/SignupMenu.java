package ir.taxi.model;

/**
 * @author Mahsa Alikhani m-58
 */
public enum SignupMenu {
    REGISTER("1. Register"),
    EXIT("2. Exit");

    private String expression;

    SignupMenu(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public void showSignupMenu(){
        for (SignupMenu item:SignupMenu.values()) {
            System.out.println(item.getExpression());
        }
    }
}
