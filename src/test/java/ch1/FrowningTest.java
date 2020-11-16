package ch1;

import ch1.infra.MyCarDatabase;
import ch1.model.Car;
import ch1.model.Wheel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


class FrowningTest {


    private Car testCar;

    @BeforeEach
    public void init(){
        testCar=new Car();
    }

    @Test
    public void shouldReplaceWhenTireIs3YearsOld() {
        MyCarDatabase testDB = Mockito.mock(MyCarDatabase.class);
        when(testDB.getCar()).thenReturn(testCar);

        Date treeYearsAgo = yearsAgo(3,1);
        Wheel wheel = new Wheel(true,treeYearsAgo);
        List<Wheel> wheels = new ArrayList<>();
        wheels.add(wheel);
        testCar.setWheels(wheels);
        Frowning.replacement(testDB);

        assertFalse(testCar.hasWheel(wheel));
    }
    @Test
    public void shouldNotReplaceWhenTireIsLessThan3YearsOld() {
        MyCarDatabase testDB = Mockito.mock(MyCarDatabase.class);
        when(testDB.getCar()).thenReturn(testCar);

        Date treeYearsAgo = yearsAgo(3,-1);
        Wheel wheel = new Wheel(true,treeYearsAgo);
        List<Wheel> wheels = new ArrayList<>();
        wheels.add(wheel);
        testCar.setWheels(wheels);
        Frowning.replacement(testDB);

        assertTrue(testCar.hasWheel(wheel));

    }



    private Date yearsAgo(int years,int seconds) {
        Calendar c = Calendar.getInstance();
        c.add(YEAR,-years);
        c.add(SECOND,-seconds);
        return c.getTime();
    }

}
