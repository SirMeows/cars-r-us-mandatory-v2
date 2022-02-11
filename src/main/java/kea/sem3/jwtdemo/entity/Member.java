package kea.sem3.jwtdemo.entity;

import javax.persistence.*;

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


    public Member() {};

    public Member(String username, String email, String password, String firstName) {
        super(username, email, password);
        this.firstName = firstName;
    }
}
