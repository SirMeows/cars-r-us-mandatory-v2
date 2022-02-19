package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.ReservationRequest;
import kea.sem3.jwtdemo.dto.ReservationResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*; // all assertion class methods can be called statically

@DataJpaTest
class ReservationServiceInMemoryTest {

    static String memberId;

    static int carId;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    MemberRepository memberRepository;

    ReservationService reservationService;

    @BeforeAll
    static void setUpDatabase(@Autowired CarRepository carRepository, @Autowired MemberRepository memberRepository, @Autowired ReservationRepository reservationRepository) {
        carRepository.deleteAll();
        memberRepository.deleteAll();
        reservationRepository.deleteAll();
        Car car = new Car("Volvo", "C40", 560,10);
        carId = carRepository.save(car).getId();
        Member member = new Member("usernælkjhg","jgldsghalgblad@a.dk","password123","sirKitty");
        memberId = memberRepository.save(member).getUsername();
    }

    @BeforeEach
    void setupService() {
        reservationService = new ReservationService(reservationRepository, carRepository, memberRepository);
    }

    @Test
    void makeReservation() {
        ReservationResponse rr = reservationService.makeReservation(new ReservationRequest(memberId, carId, LocalDate.of(2021, 02, 15)));
        assertTrue(reservationRepository.count() == 1);
        assertEquals(memberId, rr.getMemberId());
        assertEquals(carId, rr.getCarId());
        // test that some id has been assigned
        assertNotEquals(0, rr.getId());
    }
}
