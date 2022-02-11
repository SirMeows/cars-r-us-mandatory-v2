package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    static int carId1, carId2;

    @BeforeAll
    static void setUp(@Autowired CarRepository carRepository) {
        carRepository.deleteAll(); // alternatively put this in @AfterAll
        Car c1 = carRepository.save(new Car("xxx","x1",300,111));
        carId1 = c1.getId();
        Car c2 = carRepository.save(new Car("yyy","y2",400,111));
        carId2 = c2.getId();
    }

    @Test
    public void testCount(){
        assertEquals(2,carRepository.count());
    }

    @Test
    public void testAddCar() {
        Car carNew = carRepository.save(new Car("xxx","x1",300,111));
        assertNotEquals(0,carNew.getId());
    }

    @Test
    public void testFindById() {
        Car cFound = carRepository.findById(carId1).orElse(null);
        System.out.println(".------------------------------------------------dsadas-dsadsa--d-as-d-as-d-a-sd--");
        System.out.println(cFound.getBrand());
        assertEquals("xxx",cFound.getBrand());
    }
}