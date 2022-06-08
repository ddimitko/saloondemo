package com.ddimitko.prototype.objects;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @OneToMany(mappedBy = "userId")
    private List<Booking> bookings;

}
