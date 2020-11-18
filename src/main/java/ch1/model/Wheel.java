package ch1.model;

import java.util.Calendar;
import java.util.Date;

import static ch1.model.Season.WINTER;

public class Wheel {
    private final boolean winterTire;
    private final Date attached;


    public Wheel(boolean winterTire, Date attached) {
        this.winterTire = winterTire;
        Calendar cal = Calendar.getInstance();
        cal.setTime(attached);
        cal.clear(Calendar.MILLISECOND);
        this.attached= cal.getTime();
    }

    public boolean isWinterTire() {
        return winterTire;
    }


    public boolean isTooOld( ) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR,-3);
        cal.clear(Calendar.MILLISECOND);
        Date shouldBeReplacedAt = cal.getTime();
        return attached.before(shouldBeReplacedAt);
    }

    public boolean isSummerTire() {
        return !winterTire;
    }

    public boolean isInSeason(Season season) {
        return winterTire == (season == WINTER);
    }
}
