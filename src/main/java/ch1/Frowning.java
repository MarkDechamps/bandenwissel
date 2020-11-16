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
        replacement(db);
    }

    public static void replacement(MyCarDatabase db) {
        Car c = db.getCar();
        List<Wheel> w = c.getWheels();
        List<Wheel> rm = new ArrayList<Wheel>();
        for( int i=0;i<w.size();i++){
            Wheel wl = w.get(i);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR,-3);
            Date replDate = cal.getTime();
            if(replDate.compareTo(wl.getAttachedDate())==1){
                System.out.println("replacing");
                rm.add(wl);
            }
        }
        w.removeAll(rm);
    }
}


/**
 Bandenwissel.
 Als de banden meer dan 3jaar oud zijn of het zijn winterbanden en we zijn zomer
 of het zijn zomerbanden en we zijn winter, dan wissel de banden

 **/
