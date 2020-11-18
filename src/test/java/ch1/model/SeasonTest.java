package ch1.model;

import org.junit.jupiter.api.Test;

import static ch1.model.Season.SUMMER;
import static ch1.model.Season.WINTER;
import static org.junit.jupiter.api.Assertions.*;

class SeasonTest {

    @Test
    public void forValue() {
        assertSame(WINTER, Season.forValue("winter"));
        assertSame(SUMMER, Season.forValue("summer"));
    }

}
