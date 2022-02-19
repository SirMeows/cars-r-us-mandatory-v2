package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.ReservationRequest;
import kea.sem3.jwtdemo.dto.ReservationResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Reservation;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {
    ReservationRepository reservationRepository;
    CarRepository carRepository;
    MemberRepository memberRepository;

    public List<ReservationResponse> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return ReservationResponse.getReservationsFromEntities(reservations);
    }

    public ReservationResponse getReservationById(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new Client4xxException("Reservation id:" + id + " not found"));
        return new ReservationResponse(reservation);
    }

    public ReservationResponse makeReservation(ReservationRequest reservationRequest) {
        Member member = memberRepository.findById(reservationRequest.getMemberId()).orElseThrow(() -> new Client4xxException("No Member with this id"));

        Car car = carRepository.findById(reservationRequest.getCarId()).orElseThrow(() -> new Client4xxException("No Car with this id"));

        // check from reservation list whether car already reserved for that day
        for(Reservation r : car.getReservations()) {
            if(r.getRentalDate().equals(reservationRequest.getRentalDate())) {
                throw new Client4xxException("Car " + car.getId() + " " + car.getBrand() + " " + car.getModel() + " not available on date" + r.getRentalDate());
            }
        }
        Reservation reservation = new Reservation(reservationRequest.getRentalDate(), member, car);
        return new ReservationResponse(reservationRepository.save(reservation));
    }

    public ReservationResponse updateRentalDate(int reservationId, LocalDate newRentalDate) {
        Reservation reservationToEdit = reservationRepository.getById(reservationId);
        reservationToEdit.setRentalDate(newRentalDate);
        return new ReservationResponse(reservationRepository.save(reservationToEdit));
    }

    public void deleteReservation(int id) {
        reservationRepository.deleteById(id);
    }
}
