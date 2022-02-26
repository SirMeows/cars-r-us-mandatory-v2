package kea.sem3.jwtdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kea.sem3.jwtdemo.dto.CarRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue
    private int id;

    private String brand;

    private String model;

    @Column(name="price_per_day")
    private double pricePrDay;

    private double discount;

    @Column(name="date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name = "date_edited")
    @UpdateTimestamp
    private LocalDateTime dateEdited;

    @JsonIgnore //TODO Make note - to prevent endless loop when serializing to json
    @OneToMany(mappedBy = "car")
    Set<Reservation> reservations = new HashSet<>();

    public Car(CarRequest body) {
        this.brand = body.getBrand();
        this.model = body.getModel();
        this.pricePrDay = body.getPricePrDay();
        this.discount = body.getBestDiscount();
    }

    public Car(String brand, String model, double pricePrDay, double discount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.discount = discount;
    }
}
