package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.ReservationRequest;
import kea.sem3.jwtdemo.dto.ReservationResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Reservation;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceMockitoTest {

    @Mock
    ReservationRepository rRepo;
    @Mock
    CarRepository carRepo;
    @Mock
    MemberRepository mRepo;

    ReservationService rService;

    LocalDate localDate1, localDate2;

    Reservation reservation1, reservation2;

    @BeforeEach
    void setUp() {
        rService = new ReservationService(rRepo, carRepo, mRepo);
        localDate1 = LocalDate.of(2022, 11, 1);
        reservation1 = new Reservation(localDate1, new Member(), new Car());
        localDate2 = LocalDate.of(2022, 12, 2);
        reservation2 = new Reservation(localDate2, new Member(), new Car());
    }

    @Test
    void getReservations() {
        Mockito.when(rRepo.findAll()).thenReturn(List.of(reservation1, reservation2));
        List<ReservationResponse> rResponses = rService.getReservations();
        assertEquals(2, rResponses.size());
    }

    @Test
    void getReservationById() {
        // check that id of right type is returned
    }

    @Test
    void makeReservation() {
        // use mockito to fake all repositories
        // ReservationResponse rResponse = rService.makeReservation(new ReservationRequest());
        // check that rResponse is the right type
    }

    @Test
    void updateRentalDate() {
    }

    @Test
    void deleteReservation() {

        // delete one of reservations and check list length
    }
}