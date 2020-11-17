package ch1;


import ch1.model.Car;
import ch1.infra.MyCarDatabase;
import ch1.model.Wheel;

import java.util.ArrayList;
import java.util.Calendar;
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
        for( int i=0;i<w.size();i++){
            Wheel wl = w.get(i);

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR,-3);
            Date replDate = cal.getTime();
            if(replDate.compareTo(wl.getAttachedDate()) > 0){
                rm.add(wl);
            }
        }
        w.removeAll(rm);

        int add = 4-w.size();
        List<Wheel> aw = new ArrayList<>();
        for (int i=0;i<add;i++){
            Wheel w2 = new Wheel("winter".equals(season),new Date());
            aw.add(w2);
        }

        List<Wheel> torm = new ArrayList<>();
        for (int i=0;i<w.size();i++){
            if((w.get(i).isWinterTire() && !"winter".equals(season)) || (!w.get(i).isWinterTire()&& "winter".equals(season))){
                torm.add(w.get(i));
                Wheel w2 = new Wheel("winter".equals(season),new Date());
                aw.add(w2);
            }
        }

        w.removeAll(torm);
        w.addAll(aw);
    }
}


/**
 Bandenwissel.
 Gegeven een auto met 0 tot 4 wielen.
 Een wiel heeft een datum van in gebruikname en een type (winter of zomer)
 Vervang de banden.
 Na vervanging moet een auto altijd 4 banden hebben volgens het gegeven seizoen. Tekort moet worden aangevuld.
 Als de banden meer dan 3jaar oud zijn moeten ze vervangen worden.
 of het zijn winterbanden en we zijn zomer of het zijn zomerbanden en we zijn winter, dan wissel de banden ook.
 **/
