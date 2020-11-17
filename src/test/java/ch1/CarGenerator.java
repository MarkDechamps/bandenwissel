package ch1;

import ch1.model.Car;
import ch1.model.Wheel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

public class CarGenerator {

    public Car generateCar() {
        List<Wheel> wheels = new ArrayList<>();
        int nrWheels = (int) (new Date().getTime()%4);
        IntStream.range(0,nrWheels).forEach(i->{
            wheels.add(wheel());
        });

        Car car = new Car();
        car.setWheels(wheels);
        return car;
    }

    private Wheel wheel() {
        long time = new Date().getTime();
        boolean winter = time % 2 == 1;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, (int) -(time % 4));
        calendar.add(Calendar.HOUR, -1);
        return new Wheel(winter, calendar.getTime());
    }
}
