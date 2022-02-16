package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.dto.ReservationRequest;
import kea.sem3.jwtdemo.dto.ReservationResponse;
import kea.sem3.jwtdemo.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    ReservationService reservationService;

    @GetMapping
    List<ReservationResponse> getReservations() {
        return reservationService.getReservations();
    }

    @GetMapping("/{id}")
    public ReservationResponse getReservation(@PathVariable int id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping
    public ReservationResponse makeReservation(@RequestBody ReservationRequest body) {
        return reservationService.makeReservation(body);
    }

    @PatchMapping("{id}/{newrentaldate}")
    public ReservationResponse updateRentalDate(@PathVariable int id, @PathVariable LocalDate newrentaldate) {
        return reservationService.updateRentalDate(id, newrentaldate);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(int id) {
        reservationService.deleteReservation(id);
    }








    // TODO: Write ReservationController

    // TODO: Test ReservationController class
}
