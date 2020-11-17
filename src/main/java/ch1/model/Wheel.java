package ch1.model;

import java.util.Calendar;
import java.util.Date;

public class Wheel {
    private final boolean winterTire;
    private final Date attached;


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
        return attached.before(shouldBeReplacedAt);
    }

    public boolean isSummerTire() {
        return !winterTire;
    }


}
