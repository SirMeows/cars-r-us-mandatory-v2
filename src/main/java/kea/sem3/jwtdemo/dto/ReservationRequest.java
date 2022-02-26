package kea.sem3.jwtdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    private int id;
    private String memberId;
    private int carId;
    private LocalDate rentalDate;

    public ReservationRequest(String memberId, int carId, LocalDate rentalDate) {
        this.memberId = memberId;
        this.carId = carId;
        this.rentalDate = rentalDate;
    }
}
