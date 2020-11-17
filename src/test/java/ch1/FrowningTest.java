package ch1;

import ch1.infra.MyCarDatabase;
import ch1.model.Car;
import ch1.model.Wheel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


class FrowningTest {


    private Car testCar;
    private final Date THREE_YEARS_AGO = yearsAgo(3,1);
    private final Date LESS_THAN3_YEARS_AGO = yearsAgo(3,-1);

    @BeforeEach
    public void init(){
        testCar=new Car();
    }

    @Test
    public void shouldReplaceWhenTireIs3YearsOld() {
        MyCarDatabase testDB = Mockito.mock(MyCarDatabase.class);
        when(testDB.getCar()).thenReturn(testCar);

        Wheel wheel = new Wheel(true, THREE_YEARS_AGO);
        List<Wheel> wheels = new ArrayList<>();
        wheels.add(wheel);
        testCar.setWheels(wheels);
        Frowning.replacement(testDB, "winter");

        assertFalse(testCar.hasWheel(wheel));
    }
    @Test
    public void shouldNotReplaceWhenTireIsLessThan3YearsOld() {
        MyCarDatabase testDB = Mockito.mock(MyCarDatabase.class);
        when(testDB.getCar()).thenReturn(testCar);

        Wheel wheel = new Wheel(true, LESS_THAN3_YEARS_AGO);
        List<Wheel> wheels = new ArrayList<>();
        wheels.add(wheel);
        testCar.setWheels(wheels);
        Frowning.replacement(testDB, "winter");

        assertTrue(testCar.hasWheel(wheel));
    }

    @Test
    public void shouldHave4WheelsAfterReplacement(){
        MyCarDatabase testDB = Mockito.mock(MyCarDatabase.class);
        when(testDB.getCar()).thenReturn(testCar);
        testCar.setWheels(new ArrayList<>());
        String winter = "winter";
        Frowning.replacement(testDB, winter);
        assertEquals(4, testCar.getWheels().size());
        assertEquals(4, testCar.getWheels().stream().filter(Wheel::isWinterTire).count());
    }

    @Test
    public void shouldHaveWrongWheelReplaced(){
        MyCarDatabase testDB = Mockito.mock(MyCarDatabase.class);
        when(testDB.getCar()).thenReturn(testCar);
        ArrayList<Wheel> wheels = new ArrayList<>();
        Wheel wheel = new Wheel(true, LESS_THAN3_YEARS_AGO);
        wheels.add(wheel);
        testCar.setWheels(wheels);
        String summer = "summer";
        Frowning.replacement(testDB, summer);
        assertEquals(4, testCar.getWheels().size());
        assertEquals(0, testCar.getWheels().stream().filter(Wheel::isWinterTire).count());
    }

    @Test
    public void shouldHave3WrongWheelsReplaced(){
        MyCarDatabase testDB = Mockito.mock(MyCarDatabase.class);
        when(testDB.getCar()).thenReturn(testCar);
        Wheel wheel1 = new Wheel(true, LESS_THAN3_YEARS_AGO);
        Wheel wheel2 = new Wheel(false, THREE_YEARS_AGO);
        Wheel wheel3 = new Wheel(true, THREE_YEARS_AGO);
        List<Wheel> wheels = new ArrayList<>(List.of(wheel1, wheel2, wheel3));
        testCar.setWheels(wheels);
        String summer = "summer";
        Frowning.replacement(testDB, summer);
        assertEquals(4, testCar.getWheels().size());
        assertEquals(0, testCar.getWheels().stream().filter(Wheel::isWinterTire).count());
    }

    private Date yearsAgo(int years,int seconds) {
        Calendar c = Calendar.getInstance();
        c.add(YEAR,-years);
        c.add(SECOND,-seconds);
        return c.getTime();
    }


}
