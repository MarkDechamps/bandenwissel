package ch1;


import ch1.infra.MyCarDatabase;
import ch1.model.Car;
import ch1.model.Wheel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Frowning {

    private static final String WINTER = "winter";
    private static final String SUMMER = "summer";

    public static void main(String[] args) {
        MyCarDatabase db = new MyCarDatabase(); // assume this connects to a database..
        replacement(db, WINTER);
        replacement(db, SUMMER);
    }

    public static void replacement(MyCarDatabase db, final String season) {
        boolean isWinter = WINTER.equals(season);

        Car c = db.getCar();
        List<Wheel> allWheels = c.getWheels();

        allWheels.removeAll(oldWheelsFrom(allWheels));

        int add = 4 - allWheels.size();

        List<Wheel> aw = new ArrayList<>();
        for (int i = 0; i < add; i++) {
            Wheel w2 = new Wheel(isWinter, new Date());
            aw.add(w2);
        }

        List<Wheel> torm = new ArrayList<>();
        for (int i = 0; i < allWheels.size(); i++) {
            if ((allWheels.get(i).isWinterTire() && !isWinter) ||
                    (!allWheels.get(i).isWinterTire() && isWinter)) {
                torm.add(allWheels.get(i));
                Wheel w2 = new Wheel(isWinter, new Date());
                aw.add(w2);
            }
        }

        allWheels.removeAll(torm);
        allWheels.addAll(aw);
    }

    private static List<Wheel> oldWheelsFrom(List<Wheel> w) {
        List<Wheel> rm = new ArrayList<>();
        for (int i = 0; i < w.size(); i++) {
            Wheel wheel = w.get(i);
            if (wheel.isTooOld()) {
                rm.add(wheel);
            }
        }
        return rm;
    }
}

