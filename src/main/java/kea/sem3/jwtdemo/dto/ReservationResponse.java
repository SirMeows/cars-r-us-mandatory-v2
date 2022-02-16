package kea.sem3.jwtdemo.dto;

import kea.sem3.jwtdemo.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    int id;
    LocalDateTime reservationDate;
    LocalDate rentalDate;
    LocalDateTime dateCreated;
    LocalDateTime dateEdited;
    String memberId;
    int carId;

    public ReservationResponse(Reservation reservation){
        this.id = reservation.getId();
        this.reservationDate = reservation.getReservationDate();
        this.rentalDate = reservation.getRentalDate();
        this.dateCreated = reservation.getDateCreated();
        this.dateEdited = reservation.getDateEdited();
        this.memberId = reservation.getMember().getUsername();
        this.carId = reservation.getCar().getId();
    }

    public static List<ReservationResponse> getReservationsFromEntities(List<Reservation> reservations) {
        return reservations.stream().map(reservation -> new ReservationResponse(reservation)).collect(Collectors.toList());
    }
}
