package kea.sem3.jwtdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="reservation_date")
    @CreationTimestamp
    private LocalDateTime reservationDate;

    @Column(name="rental_date")
    private LocalDate rentalDate; //TODO check if markign this as modifiable non-auto generated is correct

    @Column(name="date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name = "date_edited")
    @UpdateTimestamp
    private LocalDateTime dateEdited;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    @JoinColumn(name = "car_id")
    @ManyToOne
    private Car car;

    public Reservation(LocalDate rentalDate, Member member, Car car) {
        this.rentalDate = rentalDate;
        this.member = member;
        this.car = car;
    }
}


//TODO: Update DataSetup method to contain a couple hardcoded Reservations
