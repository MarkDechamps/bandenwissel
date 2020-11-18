package ch1;

import ch1.infra.MyCarDatabase;
import ch1.model.Car;
import ch1.model.Season;
import ch1.model.Wheel;

import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static ch1.model.Season.WINTER;
import static java.util.stream.Collectors.toList;

public class Frowning {

    public static void main(String[] args) {
        MyCarDatabase db = new MyCarDatabase(); // assume this connects to a database..
        replacement(db, WINTER);
        replacement(db, Season.SUMMER);
    }

    public static void replacement(MyCarDatabase db, Season season) {
        Car car = db.getCar();
        car.removeWheelsIf(Wheel::isTooOld);
        car.removeWheelsIf(wheel -> !wheel.isInSeason(season));
        car.addWheels(createWheelsFor(season, car.nrMissingWheels()));
    }

    private static List<Wheel> createWheelsFor(Season season, int wheelsToAdd) {
        return IntStream.range(0, wheelsToAdd)
                .mapToObj(i -> createWheel(season))
                .collect(toList());
    }

    private static Wheel createWheel(Season season) {
        return Wheel.newBuilder()
                .inSeason(season)
                .withAttachedAt(new Date())
                .build();
    }

}

