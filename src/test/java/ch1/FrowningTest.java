package ch1;

import ch1.infra.MyCarDatabase;
import ch1.model.Car;
import ch1.model.Season;
import ch1.model.Wheel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static ch1.model.Season.SUMMER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static utils.TestDateUtils.LESS_THAN3_YEARS_AGO;
import static utils.TestDateUtils.THREE_YEARS_AGO;


class FrowningTest {


    private Car testCar;
    private final MyCarDatabase testDB = Mockito.mock(MyCarDatabase.class);

    @BeforeEach
    public void init() {
        testCar = new Car();
    }

    @Test
    public void emptyCar() {
        MyCarDatabase db = new MyCarDatabase(); // assume this connects to a database..
        Frowning.replacement(db, Season.WINTER);
    }


    @Test
    public void shouldReplaceWhenTireIs3YearsOld() {
        when(testDB.getCar()).thenReturn(testCar);

        Wheel wheel = new Wheel(true, THREE_YEARS_AGO);
        List<Wheel> wheels = new ArrayList<>();
        wheels.add(wheel);
        testCar.setWheels(wheels);
        Frowning.replacement(testDB, Season.WINTER);

        assertFalse(testCar.hasWheel(wheel));
    }

    @Test
    public void shouldNotReplaceWhenTireIsLessThan3YearsOld() {
        when(testDB.getCar()).thenReturn(testCar);

        Wheel wheel = new Wheel(true, LESS_THAN3_YEARS_AGO);
        List<Wheel> wheels = new ArrayList<>();
        wheels.add(wheel);
        testCar.setWheels(wheels);
        Frowning.replacement(testDB, Season.WINTER);

        assertTrue(testCar.hasWheel(wheel));
    }

    @Test
    public void shouldHave4WheelsAfterReplacement() {
        when(testDB.getCar()).thenReturn(testCar);
        testCar.setWheels(new ArrayList<>());
        Frowning.replacement(testDB, Season.WINTER);
        assertEquals(4, testCar.getWheels().size());
        assertEquals(4, testCar.getWheels().stream().filter(Wheel::isWinterTire).count());
    }

    @Test
    public void shouldHaveWrongWheelReplaced() {
        when(testDB.getCar()).thenReturn(testCar);
        ArrayList<Wheel> wheels = new ArrayList<>();
        Wheel wheel = new Wheel(true, LESS_THAN3_YEARS_AGO);
        wheels.add(wheel);
        testCar.setWheels(wheels);
        Frowning.replacement(testDB, SUMMER);
        assertEquals(4, testCar.getWheels().size());
        assertEquals(0, testCar.getWheels().stream().filter(Wheel::isWinterTire).count());
    }

    @Test
    public void shouldHave3WrongWheelsReplaced() {
        when(testDB.getCar()).thenReturn(testCar);
        Wheel wheel1 = new Wheel(true, LESS_THAN3_YEARS_AGO);
        Wheel wheel2 = new Wheel(false, THREE_YEARS_AGO);
        Wheel wheel3 = new Wheel(true, THREE_YEARS_AGO);
        List<Wheel> wheels = new ArrayList<>(List.of(wheel1, wheel2, wheel3));
        testCar.setWheels(wheels);

        Frowning.replacement(testDB, SUMMER);
        assertSummer();
    }

    private void assertSummer() {
        List<Wheel> wheels = testCar.getWheels();
        assertEquals(4, wheels.size());
        assertEquals(0, wheels.stream().filter(Wheel::isWinterTire).count());
        assertEquals(0, wheels.stream().filter(Wheel::isTooOld).count());
    }

    private void assertWinter() {
        List<Wheel> wheels = testCar.getWheels();
        assertEquals(4, wheels.size());
        assertEquals(0, wheels.stream().filter(Wheel::isSummerTire).count());
        assertEquals(0, wheels.stream().filter(Wheel::isTooOld).count());
    }

    @Test
    public void randomSummerCarFixer() {
        IntStream.range(0, 100).forEach(i -> {
            var g = new CarGenerator();
            testCar = g.generateCar();
            when(testDB.getCar()).thenReturn(testCar);
            Frowning.replacement(testDB, SUMMER);
            assertSummer();
        });
    }

    @Test
    public void randomWinterCarFixer() {
        IntStream.range(0, 100).forEach(i -> {
            var g = new CarGenerator();
            testCar = g.generateCar();
            when(testDB.getCar()).thenReturn(testCar);
            Frowning.replacement(testDB, Season.WINTER);
            assertWinter();
        });
    }


}
