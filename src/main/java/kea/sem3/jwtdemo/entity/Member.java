package kea.sem3.jwtdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kea.sem3.jwtdemo.dto.MemberRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "member")
@DiscriminatorValue("MEMBER")
public class Member extends BaseUser {
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    private String street;
    private String city;
    private String zip;
    private boolean approved;
    private int ranking;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    Set<Reservation> reservations = new HashSet<>();

    public Member(String username, String email, String password, String firstName) {
        super(username, email, password);
        this.firstName = firstName;
    }

    public Member(MemberRequest body) {
        this.firstName = body.getFirstName();
        this.lastName = body.getLastName();
        this.street = body.getStreet();
        this.city = body.getCity();
        this.zip = body.getZip();
    }

    public Member(String firstName, String lastName, String street, String city, String zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }
}
