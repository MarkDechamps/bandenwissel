package ch1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Car {
    public static final int MAX_WHEELS = 4;

    private List<Wheel> wheels = new ArrayList<>();

    public List<Wheel> getWheels() {
        return wheels;
    }

    public void setWheels(List<Wheel> wheels) {
        this.wheels = wheels;
    }

    public boolean hasWheel(Wheel wheel) {
        return wheels.contains(wheel);
    }

    public void removeWheelsIf(Predicate<Wheel> condition) {
        wheels.removeIf(condition);
    }

    public int nrMissingWheels() {
        return Car.MAX_WHEELS - wheels.size();
    }

    public void addWheels(List<Wheel> wheelsToAdd) {
        wheels.addAll(wheelsToAdd);
    }
}
