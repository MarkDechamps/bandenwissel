package utils;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;

public class TestDateUtils {

    public final static Date THREE_YEARS_AGO = yearsAgo(3,1);
    public final static Date LESS_THAN3_YEARS_AGO = yearsAgo(3,-1);

    private static Date yearsAgo(int years,int seconds) {
        Calendar c = Calendar.getInstance();
        c.add(YEAR,-years);
        c.add(SECOND,-seconds);
        return c.getTime();
    }
}
