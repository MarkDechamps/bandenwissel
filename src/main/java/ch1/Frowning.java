package ch1;


import ch1.infra.MyCarDatabase;
import ch1.model.Car;
import ch1.model.Wheel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Frowning {

    public static void main(String[] args) {
        MyCarDatabase db = new MyCarDatabase(); // assume this connects to a database..
        replacement(db, "winter");
        replacement(db, "summer");
    }

    public static void replacement(MyCarDatabase db, String season) {
        Car c = db.getCar();
        List<Wheel> w = c.getWheels();
        List<Wheel> rm = new ArrayList<>();
        for (int i = 0; i < w.size(); i++) {
            Wheel wheel = w.get(i);
            if (wheel.isTooOld()) {
                rm.add(wheel);
            }
        }
        w.removeAll(rm);

        int add = 4 - w.size();
        List<Wheel> aw = new ArrayList<>();
        for (int i = 0; i < add; i++) {
            Wheel w2 = new Wheel("winter".equals(season), new Date());
            aw.add(w2);
        }

        List<Wheel> torm = new ArrayList<>();
        for (int i = 0; i < w.size(); i++) {
            if ((w.get(i).isWinterTire() && !"winter".equals(season)) ||
                    (!w.get(i).isWinterTire() && "winter".equals(season))) {
                torm.add(w.get(i));
                Wheel w2 = new Wheel("winter".equals(season), new Date());
                aw.add(w2);
            }
        }

        w.removeAll(torm);
        w.addAll(aw);
    }


}

