package ch1.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static utils.TestDateUtils.LESS_THAN3_YEARS_AGO;
import static utils.TestDateUtils.THREE_YEARS_AGO;

class WheelTest {

    @Test
    public void olderThan3IsTooOld() {
        Wheel tooOld = new Wheel(true,THREE_YEARS_AGO);
        assertTrue(tooOld.isTooOld(),"Older than 3 years is too old");
    }
    @Test
    public void youngerThan3IsTooOk() {
        Wheel tooOld = new Wheel(true,LESS_THAN3_YEARS_AGO);
        assertFalse(tooOld.isTooOld(),"Younger than 3 years is too old");
    }
    @Test
    public void winterTire() {
        Wheel winter = new Wheel(true,LESS_THAN3_YEARS_AGO);
        assertTrue(winter.isWinterTire());
        assertFalse(winter.isSummerTire());
    }
    @Test
    public void summerTire() {
        Wheel winter = new Wheel(false,LESS_THAN3_YEARS_AGO);
        assertFalse(winter.isWinterTire());
        assertTrue(winter.isSummerTire());
    }


}
