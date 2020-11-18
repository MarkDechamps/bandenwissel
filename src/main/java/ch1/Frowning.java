package ch1;


import ch1.infra.MyCarDatabase;
import ch1.model.Car;
import ch1.model.Season;
import ch1.model.Wheel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frowning {

    private static final String WINTER = "winter";
    private static final String SUMMER = "summer";
    private static final int MAX_WHEELS = 4;

    public static void main(String[] args) {
        MyCarDatabase db = new MyCarDatabase(); // assume this connects to a database..
        replacement(db, WINTER);
        replacement(db, SUMMER);
    }

    public static void replacement(MyCarDatabase db, final String seasonParam) {
        boolean isWinter = WINTER.equals(seasonParam);
        Season season = Season.forValue(seasonParam);

        Car c = db.getCar();
        List<Wheel> allWheels = c.getWheels();

        allWheels.removeAll(oldWheelsFrom(allWheels));

        int numberOfWheelsToAdd = MAX_WHEELS - allWheels.size();
        List<Wheel> wheelsToAdd = createNumberOfWheels(isWinter, numberOfWheelsToAdd);

        List<Wheel> toReplace = new ArrayList<>();
        allWheels.stream()
                .filter(wheel -> !wheel.isInSeason(season))
                .forEach(wheel -> {
                    toReplace.add(wheel);
                    wheelsToAdd.add(createWheel(isWinter));
                });

        allWheels.removeAll(toReplace);
        allWheels.addAll(wheelsToAdd);
    }

    private static List<Wheel> createNumberOfWheels(boolean isWinter, int wheelsToAdd) {
        return IntStream.range(0, wheelsToAdd).mapToObj(i -> createWheel(isWinter)).collect(Collectors.toList());
    }

    private static Wheel createWheel(boolean isWinter) {
        return new Wheel(isWinter, new Date());
    }

    private static List<Wheel> oldWheelsFrom(List<Wheel> w) {
        List<Wheel> rm = new ArrayList<>();
        for (Wheel wheel : w) {
            if (wheel.isTooOld()) {
                rm.add(wheel);
            }
        }
        return rm;
    }
}

