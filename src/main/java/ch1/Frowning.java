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

    private static final String WINTER = "winter";
    private static final String SUMMER = "summer";

    public static void main(String[] args) {
        MyCarDatabase db = new MyCarDatabase(); // assume this connects to a database..
        replacement(db, WINTER);
        replacement(db, SUMMER);
    }

    public static void replacement(MyCarDatabase db, final String seasonParam) {
        Season season = Season.forValue(seasonParam);

        Car car = db.getCar();
        List<Wheel> allWheels = car.getWheels();

        allWheels.removeAll(oldWheelsFrom(allWheels));
        int numberOfWheelsToAdd = Car.MAX_WHEELS - allWheels.size();
        List<Wheel> wheelsToAdd = createWheelsFor(season, numberOfWheelsToAdd);

        var toReplace = extractWheelsToReplaceFrom(allWheels, season);
        allWheels.removeAll(toReplace);

        wheelsToAdd.addAll(createWheelsFor(season, toReplace.size()));
        allWheels.addAll(wheelsToAdd);
    }

    private static List<Wheel> extractWheelsToReplaceFrom(List<Wheel> allWheels, Season season) {
        return allWheels.stream()
                .filter(wheel -> !wheel.isInSeason(season))
                .collect(Collectors.toList());
    }

    private static List<Wheel> createWheelsFor(Season season, int wheelsToAdd) {
        return IntStream.range(0, wheelsToAdd).mapToObj(i -> createWheel(season)).collect(Collectors.toList());
    }

    private static Wheel createWheel(Season season) {
        return new Wheel(season==Season.WINTER, new Date());
    }

    private static List<Wheel> oldWheelsFrom(List<Wheel> w) {
        return w.stream().filter(Wheel::isTooOld).collect(Collectors.toList());
    }
}

