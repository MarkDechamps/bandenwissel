package ch1.model;

import java.util.Calendar;
import java.util.Date;

public class Wheel {
    private boolean winterTire;
    private Date attached;


    public Wheel(boolean winterTire, Date attached) {
        this.winterTire = winterTire;
        this.attached= attached;
    }

    public boolean isWinterTire() {
        return winterTire;
    }


    public boolean isTooOld( ) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR,-3);
        Date shouldBeReplacedAt = cal.getTime();
        return shouldBeReplacedAt.compareTo(attached) > 0;
    }

    public boolean isSummerTire() {
        return !winterTire;
    }
}
