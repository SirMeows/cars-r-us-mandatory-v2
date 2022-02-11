package kea.sem3.jwtdemo.entity;

import kea.sem3.jwtdemo.dto.CarRequest;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.asm.IModelFilter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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

    public Car(){}

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
