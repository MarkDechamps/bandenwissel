package ch1.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Car {
    private List<Wheel> wheels;

    public Car() {
        wheels = new ArrayList<>();
        wheels.add(wheel());
        wheels.add(wheel());
        wheels.add(wheel());
        wheels.add(wheel());
    }

    private Wheel wheel() {
        long time = new Date().getTime();
        boolean winter = time % 2 == 1;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, (int) -(time % 4));
        return new Wheel(winter, calendar.getTime());
    }


    public List<Wheel> getWheels() {
        return wheels;
    }

    public void setWheels(List<Wheel> wheels) {
        this.wheels = wheels;
    }

    public boolean hasWheel(Wheel wheel) {
        return wheels.contains(wheel);
    }
}
