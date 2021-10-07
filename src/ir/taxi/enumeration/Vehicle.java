package ir.taxi.enumeration;

/**
 * @author Mahsa Alikhani m-58
 */
public enum Vehicle {
    CAR("1. Take a car"),
    MOTORCYCLE("2. Take a motor cycle"),
    VAN("3. Take a van"),
    RV("4. Take a RV");

    private String expression;

    Vehicle(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public static void showVehicleMenu(){
        for (Vehicle item:Vehicle.values()) {
            System.out.println(item.getExpression());
        }
    }
}
