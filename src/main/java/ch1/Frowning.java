package ch1;

import ch1.infra.MyCarDatabase;
import ch1.model.Car;
import ch1.model.Season;
import ch1.model.Wheel;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frowning {

    public static void main(String[] args) {
        MyCarDatabase db = new MyCarDatabase(); // assume this connects to a database..
        replacement(db, Season.WINTER);
        replacement(db, Season.SUMMER);
    }

    public static void replacement(MyCarDatabase db, Season season) {
        Car car = db.getCar();
        List<Wheel> allWheels = car.getWheels();

        car.removeWheelsIf(Wheel::isTooOld);
        car.removeWheelsIf(wheel->!wheel.isInSeason(season));
        allWheels.addAll(createWheelsFor(season, car.nrMissingWheels()));
    }

    private static List<Wheel> createWheelsFor(Season season, int wheelsToAdd) {
        return IntStream.range(0, wheelsToAdd).mapToObj(i -> createWheel(season)).collect(Collectors.toList());
    }

    private static Wheel createWheel(Season season) {
        return new Wheel(season==Season.WINTER, new Date());
    }

}

