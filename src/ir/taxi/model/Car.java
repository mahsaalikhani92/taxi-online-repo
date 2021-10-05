package ir.taxi.model;

import ir.taxi.enumeration.CarColor;

/**
 * @author Mahsa Alikhani m-58
 */
public class Car {
    private int carId;
    private String model;
    //private CarColor carColor;
    private String carColor;

    public Car(String model, String carColor) {
        this.model = model;
        this.carColor = carColor;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }
}
