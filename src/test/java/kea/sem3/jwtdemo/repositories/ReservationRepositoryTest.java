package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Reservation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;

    static int reservationId1;
    @BeforeAll
    static void setUp(@Autowired ReservationRepository reservationRepository) {
        Reservation r1 = reservationRepository.save(new Reservation());
        reservationId1 = r1.getId();
    }
    @Test
    public void testCount(){
        assertEquals(1,reservationRepository.count());
    }

    @Test
    public void testAddReservation() {
        Reservation carNew = reservationRepository.save(new Reservation());
        assertNotEquals(0,carNew.getId());
    }

}