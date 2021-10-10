package ir.taxi.model;

import java.util.Objects;

/**
 * @author Mahsa Alikhani m-58
 */
public class Car {
    private int id;
    private String model;
    private String carColor;

    public Car(String model, String carColor) {
        this.model = model;
        this.carColor = carColor;
    }

    public int getCarId() {
        return id;
    }

    public void setCarId(int carId) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
